package org.springblade.core.groovy.compiler.impl;

import org.springblade.core.groovy.compiler.DynamicCodeCompiler;
import org.springblade.core.groovy.entity.ScriptEntry;
import groovy.lang.GroovyClassLoader;
import groovy.transform.TimedInterrupt;
import org.codehaus.groovy.ast.ClassCodeVisitorSupport;
import org.codehaus.groovy.ast.ClassNode;
import org.codehaus.groovy.ast.expr.ClassExpression;
import org.codehaus.groovy.ast.expr.ConstructorCallExpression;
import org.codehaus.groovy.ast.expr.MethodCallExpression;
import org.codehaus.groovy.ast.expr.MethodPointerExpression;
import org.codehaus.groovy.ast.expr.StaticMethodCallExpression;
import org.codehaus.groovy.classgen.GeneratorContext;
import org.codehaus.groovy.control.CompilePhase;
import org.codehaus.groovy.control.CompilerConfiguration;
import org.codehaus.groovy.control.SourceUnit;
import org.codehaus.groovy.control.customizers.ASTTransformationCustomizer;
import org.codehaus.groovy.control.customizers.CompilationCustomizer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;

/**
 * <p>
 *      groovy编译器
 * </p>
 *
 * @author mqttsnet 2024/9/18 11:40 上午
 */
public class GroovyCompiler implements DynamicCodeCompiler {

    private static final Logger LOG = LoggerFactory.getLogger(GroovyCompiler.class);

    /**
     * 脚本中禁止调用的方法名：进程执行、反射、类加载、JVM 控制等 RCE 入口。
     */
    private static final Set<String> BLOCKED_METHODS = new HashSet<>(Arrays.asList(
            "execute", "exec", "getRuntime", "forName", "newInstance", "loadClass", "defineClass",
            "getClassLoader", "getMetaClass", "setMetaClass", "invokeMethod", "getMethod", "getMethods",
            "getDeclaredMethod", "getDeclaredMethods", "getConstructor", "getConstructors",
            "getDeclaredConstructor", "getDeclaredConstructors", "getField", "getDeclaredField",
            "getDeclaredFields", "setAccessible", "exit", "halt", "loadLibrary", "getenv",
            "getProperties", "setSecurityManager", "evaluate", "parseClass", "newInstances",
            // Spring 容器后门：脚本不应通过注入的 applicationContext 拿任意 bean / 改环境 / 发事件
            "getBean", "getBeansOfType", "getBeanNamesForType", "getBeanFactory",
            "getAutowireCapableBeanFactory", "getEnvironment", "publishEvent"));

    /**
     * 脚本中禁止引用/构造的类型（简单名比对）：进程、反射、文件、类加载、脚本引擎等。
     */
    private static final Set<String> BLOCKED_TYPES = new HashSet<>(Arrays.asList(
            "Runtime", "ProcessBuilder", "Process", "Class", "ClassLoader",
            "Thread", "ThreadGroup", "File", "Files", "Paths", "FileInputStream",
            "FileOutputStream", "FileReader", "FileWriter", "RandomAccessFile",
            "Method", "Field", "Constructor", "GroovyShell", "GroovyClassLoader",
            "Eval", "ScriptEngine", "ScriptEngineManager", "Unsafe", "Class[]",
            "ProcessGroovyMethods", "DefaultGroovyMethods", "DefaultGroovyStaticMethods"));

    /**
     * 源码级禁用的编译期 AST 转换：{@code @ASTTest} / {@code @Grab} 系列的 closure/动作在编译期(parseClass)
     * 即执行，早于 AST 安全访问器(CANONICALIZATION)，且不在其遍历范围，必须在编译前拦截。
     * 同时匹配 import 别名规避与全限定名写法。
     */
    private static final Pattern FORBIDDEN_SOURCE = Pattern.compile(
            "@\\s*(?:groovy\\.transform\\.)?ASTTest"
            + "|@\\s*Grab(?:Config|Resolver|Exclude)?\\b"
            + "|groovy\\.transform\\.ASTTest"
            + "|groovy\\.grape");

    @Override
    public Class<?> compile(String code, String name) {
        assertSourceAllowed(code);
        GroovyClassLoader loader = getGroovyClassLoader();
        LOG.warn("Compiling filter: " + name);
        return (Class<?>) loader.parseClass(code, name);
    }

    @Override
    public Class<?> compile(ScriptEntry scriptEntry) {
        assertSourceAllowed(scriptEntry == null ? null : scriptEntry.getScriptContext());
        GroovyClassLoader loader = getGroovyClassLoader();
        // 以 GroovyCompiler + 脚本的名称作为类名称
        return loader.parseClass(scriptEntry.getScriptContext(),
                GroovyCompiler.class.getSimpleName() + "_" + scriptEntry.getUniqueKey());
    }

    /**
     * 编译前源码预扫描：拒绝编译期 AST 转换注解（@ASTTest/@Grab 等），堵住"编译即 RCE"。
     */
    private static void assertSourceAllowed(String code) {
        if (code != null && FORBIDDEN_SOURCE.matcher(code).find()) {
            throw new SecurityException("脚本安全校验未通过：禁止使用编译期 AST 转换注解（如 @ASTTest / @Grab）");
        }
    }

    /**
     * <p>
     * 为什么要New 一个class loader呢？这个就要从Class对象垃圾回收说起，一个Class要被回收必须满足以下条件：
     *  <ol>
     *     <li>该Class 的所有实例都已经被回收</li>
     *     <li>加载该类的classLoader已经被回收</li>
     *     <li>该Class 没有被引用</li>
     *  </ol>
     * </p>
     * <p>
     * 通过使用 new 一个classLoader 来加载动态脚本就是为了解决动态类回收问题。
     * </p>
     * <p>
     * 同时挂载安全沙箱（{@link #secureConfiguration()}），在编译期遍历全量 AST（含 static 初始化块、
     * 字段初始化、构造器、闭包），拒绝危险脚本，防止动态脚本执行 OS 命令、反射、文件读写等 RCE。
     * </p>
     *
     * @return a new GroovyClassLoader
     */
    public GroovyClassLoader getGroovyClassLoader() {
        return new GroovyClassLoader(Thread.currentThread().getContextClassLoader(), secureConfiguration());
    }

    /**
     * 构建带安全沙箱的编译配置。
     * <p>用自定义 {@link CompilationCustomizer} 在编译期对整个 ClassNode 做全量 AST 遍历
     * （{@link ClassCodeVisitorSupport} 覆盖方法体、static 初始化块、实例字段初始化、构造器、闭包），
     * 拦截危险方法调用（{@link #BLOCKED_METHODS}）、动态方法名调用、危险类型引用/构造（{@link #BLOCKED_TYPES}）
     * 与方法指针，阻断 Groovy 脚本执行 OS 命令、反射、文件、类加载等 RCE；
     * 正常的数据转换脚本（取 binding 参数、JSON 解析、Map/List 与字符串/数学运算）不受影响。
     * <p>说明：编译期静态分析无法覆盖 100% 的运行期动态分发，故此为纵深防御之一，需配合入口收口与脚本来源管控。
     */
    /** 脚本执行超时上限（秒）：安全保险，防 while(true) 等 CPU 死循环拖死线程。 */
    private static final long SCRIPT_TIMEOUT_SECONDS = 5L;

    private static CompilerConfiguration secureConfiguration() {
        CompilerConfiguration config = new CompilerConfiguration();
        config.addCompilationCustomizers(new SecurityCustomizer());
        // 执行超时保险：@TimedInterrupt 编译期给所有循环/方法插入超时检查，纯 CPU 死循环也能超时抛
        // TimeoutException（由编译器注入，不受"脚本源码 AST 注解预扫描"限制）。
        config.addCompilationCustomizers(new ASTTransformationCustomizer(
                Collections.singletonMap("value", SCRIPT_TIMEOUT_SECONDS), TimedInterrupt.class));
        return config;
    }

    /**
     * 自定义编译期定制器：对每个类做全量 AST 安全遍历。
     */
    private static final class SecurityCustomizer extends CompilationCustomizer {
        SecurityCustomizer() {
            super(CompilePhase.CANONICALIZATION);
        }

        @Override
        public void call(SourceUnit source, GeneratorContext context, ClassNode classNode) {
            classNode.visitContents(new SecurityVisitor(source));
        }
    }

    /**
     * 安全 AST 访问器：命中危险节点即抛 {@link SecurityException}，中断编译。
     */
    private static final class SecurityVisitor extends ClassCodeVisitorSupport {
        private final SourceUnit sourceUnit;

        SecurityVisitor(SourceUnit sourceUnit) {
            this.sourceUnit = sourceUnit;
        }

        @Override
        protected SourceUnit getSourceUnit() {
            return sourceUnit;
        }

        private static void deny(String what) {
            throw new SecurityException("脚本安全校验未通过，禁止使用：" + what);
        }

        @Override
        public void visitMethodCallExpression(MethodCallExpression call) {
            String method = call.getMethodAsString();
            if (method == null) {
                // 动态方法名（如 obj."$x"()）无法静态判定安全性，一律拒绝
                deny("动态方法名调用");
            } else if (BLOCKED_METHODS.contains(method)) {
                deny("方法 " + method + "()");
            }
            super.visitMethodCallExpression(call);
        }

        @Override
        public void visitStaticMethodCallExpression(StaticMethodCallExpression call) {
            if (BLOCKED_METHODS.contains(call.getMethod())
                    || BLOCKED_TYPES.contains(call.getOwnerType().getNameWithoutPackage())) {
                deny("静态调用 " + call.getOwnerType().getNameWithoutPackage() + "." + call.getMethod() + "()");
            }
            super.visitStaticMethodCallExpression(call);
        }

        @Override
        public void visitConstructorCallExpression(ConstructorCallExpression call) {
            if (BLOCKED_TYPES.contains(call.getType().getNameWithoutPackage())) {
                deny("构造 " + call.getType().getNameWithoutPackage());
            }
            super.visitConstructorCallExpression(call);
        }

        @Override
        public void visitClassExpression(ClassExpression expression) {
            if (BLOCKED_TYPES.contains(expression.getType().getNameWithoutPackage())) {
                deny("类型 " + expression.getType().getNameWithoutPackage());
            }
            super.visitClassExpression(expression);
        }

        @Override
        public void visitMethodPointerExpression(MethodPointerExpression expression) {
            String method = expression.getMethodName() == null ? null : expression.getMethodName().getText();
            if (method == null || BLOCKED_METHODS.contains(method)) {
                deny("方法指针 &" + method);
            }
            super.visitMethodPointerExpression(expression);
        }
    }

}

package org.springblade.modules.iot.core.engine.parsing.ast.statement;

import org.springblade.modules.iot.core.engine.MagicResourceLoader;
import org.springblade.modules.iot.core.engine.MagicScriptContext;
import org.springblade.modules.iot.core.engine.asm.Label;
import org.springblade.modules.iot.core.engine.compile.MagicScriptCompiler;
import org.springblade.modules.iot.core.engine.functions.DynamicModuleImport;
import org.springblade.modules.iot.core.engine.parsing.Span;
import org.springblade.modules.iot.core.engine.parsing.VarIndex;
import org.springblade.modules.iot.core.engine.parsing.ast.Node;

public class Import extends Node {

  private final VarIndex varIndex;
  private final boolean module;
  private String packageName;
  private boolean function;

  public Import(Span span, String packageName, VarIndex varIndex, boolean module) {
    super(span);
    this.packageName = packageName;
    this.varIndex = varIndex;
    this.module = module;
    if (!module && packageName.startsWith("@")) {
      function = true;
      this.packageName = packageName.substring(1);
    }
  }

  public boolean isImportPackage() {
    /** 不允许批量导入包 */
    return false;
    //		return packageName.endsWith(".*");
  }

  @Override
  public void compile(MagicScriptCompiler compiler) {
    if (isImportPackage()) {
      compiler
          .loadContext()
          .ldc(packageName.substring(0, packageName.length() - 1))
          .invoke(INVOKEVIRTUAL, MagicScriptContext.class, "addImport", void.class, String.class);
    } else {
      String methodName = "loadClass";
      if (this.module) {
        methodName = "loadModule";
      } else if (this.function) {
        methodName = "loadFunction";
      }
      compiler
          .pre_store(varIndex) // 保存变量前的准备
          .loadContext()
          .ldc(packageName) // 包名&函数名
          .invoke(
              INVOKESTATIC,
              MagicResourceLoader.class,
              methodName,
              Object.class,
              MagicScriptContext.class,
              String.class); // 加载资源
      if (this.module) {
        // if(module instanceof DynamicModuleImport){ module =
        // ((DynamicModuleImport)module).getDynamicModule
        // (context); }
        Label end = new Label();
        compiler
            .insn(DUP)
            .typeInsn(INSTANCEOF, DynamicModuleImport.class)
            .jump(IFEQ, end)
            .typeInsn(CHECKCAST, DynamicModuleImport.class)
            .loadContext()
            .invoke(
                INVOKEVIRTUAL,
                DynamicModuleImport.class,
                "getDynamicModule",
                Object.class,
                MagicScriptContext.class)
            .label(end);
      }
      compiler.store(varIndex); // 保存变量
    }
  }
}

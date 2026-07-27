package org.springblade.common.utils;

import cn.hutool.crypto.digest.DigestUtil;
import freemarker.cache.MruCacheStorage;
import freemarker.cache.MultiTemplateLoader;
import freemarker.cache.StringTemplateLoader;
import freemarker.cache.TemplateLoader;
import freemarker.core.TemplateClassResolver;
import freemarker.template.Configuration;
import freemarker.template.Template;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.io.StringWriter;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * FreeMarker 模板工具类
 *
 * @author mqttsnet
 */
@Slf4j
public class FreeMarkerUtil {

    private static final Configuration FREEMARKER_CFG;
    private static final StringTemplateLoader SL;
    private static final ConcurrentHashMap<String, Template> TEMPLATE_CACHE = new ConcurrentHashMap<>(512);
    private static final int MAX_CACHE_SIZE = 10000;

    static {
        FREEMARKER_CFG = new Configuration(Configuration.DEFAULT_INCOMPATIBLE_IMPROVEMENTS);
        SL = new StringTemplateLoader();
        FREEMARKER_CFG.setBooleanFormat("c");
        FREEMARKER_CFG.setNumberFormat("0.##");
        FREEMARKER_CFG.setCacheStorage(new MruCacheStorage(10000, Integer.MAX_VALUE));
        FREEMARKER_CFG.setTemplateUpdateDelayMilliseconds(6000000L);
        TemplateLoader[] loaders = new TemplateLoader[]{SL};
        MultiTemplateLoader mt = new MultiTemplateLoader(loaders);
        FREEMARKER_CFG.setTemplateLoader(mt);
        FREEMARKER_CFG.setNewBuiltinClassResolver(TemplateClassResolver.SAFER_RESOLVER);
        FREEMARKER_CFG.setAPIBuiltinEnabled(false);
    }

    @SneakyThrows
    public static String generateString(String strTemplate, Map<String, Object> parameters) {
        String templateName = DigestUtil.md5Hex(strTemplate);

        if (SL.findTemplateSource(templateName) == null) {
            SL.putTemplate(templateName, strTemplate);
        }

        Template template = TEMPLATE_CACHE.computeIfAbsent(templateName, k -> {
            try {
                if (TEMPLATE_CACHE.size() >= MAX_CACHE_SIZE) {
                    TEMPLATE_CACHE.clear();
                }
                return FREEMARKER_CFG.getTemplate(templateName, StrPool.UTF8);
            } catch (Exception e) {
                throw new RuntimeException("Template init failed", e);
            }
        });

        StringWriter writer = new StringWriter();
        template.process(parameters, writer);
        return writer.toString();
    }
}

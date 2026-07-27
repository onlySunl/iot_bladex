package org.springblade.common.groovy.loader;
import org.springblade.common.groovy.entity.ScriptEntry;
public interface ScriptLoader {
    ScriptEntry load(String id);
}

package org.springblade.common.groovy.registry;
import org.springblade.common.groovy.entity.ScriptEntry;
public interface ScriptRegistry {
    void register(ScriptEntry entry);
    ScriptEntry get(String id);
}

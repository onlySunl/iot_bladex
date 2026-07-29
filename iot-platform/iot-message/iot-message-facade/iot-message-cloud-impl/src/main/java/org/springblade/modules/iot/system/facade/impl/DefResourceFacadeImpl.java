package org.springblade.modules.iot.system.facade.impl;

import com.mqttsnet.basic.cache.redis2.CacheResult;
import com.mqttsnet.basic.cache.repository.CacheOps;
import com.mqttsnet.basic.model.cache.CacheKey;
import org.springblade.modules.iot.common.cache.tenant.application.AllResourceApiCacheKeyBuilder;
import org.springblade.modules.iot.model.vo.result.ResourceApiVO;
import org.springblade.modules.iot.system.facade.DefResourceFacade;
import org.springblade.modules.iot.system.service.application.DefResourceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 *
 * @author tangyh
 * @since 2024/9/21 22:21
 */
@Service
@RequiredArgsConstructor
public class DefResourceFacadeImpl implements DefResourceFacade {
    private final DefResourceService defResourceService;
    private final CacheOps cacheOps;

    @Override
    public Map<String, Set<String>> listAllApi() {
        CacheKey cacheKey = AllResourceApiCacheKeyBuilder.builder();
        CacheResult<Map<String, Set<String>>> result = cacheOps.get(cacheKey, (k) -> findAllApi());
        return result.getValue();
    }

    private Map<String, Set<String>> findAllApi() {
        // 查询系统中配置的URI和权限关系
        List<ResourceApiVO> list = defResourceService.findAllApi();
        return list.stream()
                .collect(Collectors.toMap(
                        item -> item.getUri() + "###" + item.getRequestMethod(),
                        resourceApiVO -> {
                            Set<String> codes = new HashSet<>();
                            codes.add(resourceApiVO.getCode());
                            return codes;
                        },
                        (existingCodes, newCodes) -> {
                            existingCodes.addAll(newCodes);
                            return existingCodes;
                        },
                        LinkedHashMap::new
                ));
    }
}

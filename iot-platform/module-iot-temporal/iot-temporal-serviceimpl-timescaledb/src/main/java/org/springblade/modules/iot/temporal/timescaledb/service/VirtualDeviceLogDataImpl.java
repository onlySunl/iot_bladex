/*
 *
 *  * | Licensed 未经许可不能去掉「Enjoy-iot」相关版权
 *  * +----------------------------------------------------------------------
 *  * | Author: xw2sy@163.com
 *  * +----------------------------------------------------------------------
 *
 *  Copyright [2025] [Enjoy-iot]
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 * /
 */
package org.springblade.modules.iot.temporal.timescaledb.service;


import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springblade.modules.iot.IVirtualDeviceLogData;
import org.springblade.modules.iot.common.entity.PageParam;
import org.springblade.modules.iot.common.entity.PageResult;
import org.springblade.modules.iot.common.utils.BeanUtils;
import org.springblade.modules.iot.temporal.timescaledb.dao.PgVirtualDeviceLogMapper;
import org.springblade.modules.iot.temporal.timescaledb.model.PgVirtualDeviceLog;
import org.springblade.modules.iot.api.virtualdevice.dto.VirtualDeviceLog;
import org.postgresql.util.PGTimestamp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class VirtualDeviceLogDataImpl implements IVirtualDeviceLogData {

    @Autowired
    private PgVirtualDeviceLogMapper virtualDeviceLogMapper;

    @Override
    public PageResult<VirtualDeviceLog> findByVirtualDeviceId(Long virtualDeviceId, int page, int size) {
        PageParam pageParam = new PageParam();
        pageParam.setPageNo(page);
        pageParam.setPageSize(size);
        IPage<PgVirtualDeviceLog> iPage = virtualDeviceLogMapper.selectPage(new Page<>(pageParam.getPageNo(), pageParam.getPageSize()),
                Wrappers.lambdaQuery(PgVirtualDeviceLog.class)
                        .eq(PgVirtualDeviceLog::getVirtualDeviceId, virtualDeviceId)
                        .orderByDesc(PgVirtualDeviceLog::getTime)
        );

        return new PageResult<>(iPage.getRecords().stream().map(r ->
                        new VirtualDeviceLog(r.getTime().getTime(), virtualDeviceId,
                                r.getVirtualDeviceName(),
                                r.getDeviceTotal(), r.getResult(), r.getTime().getTime()))
                .collect(Collectors.toList()), iPage.getTotal());
    }

    @Override
    public void add(VirtualDeviceLog log) {
        PgVirtualDeviceLog deviceLog = BeanUtils.toBean(log, PgVirtualDeviceLog.class);
        deviceLog.setTime(new PGTimestamp(System.currentTimeMillis()));
        virtualDeviceLogMapper.insert(deviceLog);
    }
}

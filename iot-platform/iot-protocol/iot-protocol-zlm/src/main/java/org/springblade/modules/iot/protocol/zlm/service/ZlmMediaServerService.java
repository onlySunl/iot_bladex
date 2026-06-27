package org.springblade.modules.iot.protocol.zlm.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springblade.modules.iot.domain.ZlmMediaServer;
import org.springblade.modules.iot.protocol.zlm.mapper.ZlmMediaServerMapper;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * ZLM流媒体服务器Service
 *
 * @author BladeX
 */
@Service
public class ZlmMediaServerService extends ServiceImpl<ZlmMediaServerMapper, ZlmMediaServer>
    implements IService<ZlmMediaServer> {

    /**
     * 根据IP查询
     */
    public ZlmMediaServer getByIp(String ip) {
        return getOne(new LambdaQueryWrapper<ZlmMediaServer>()
            .eq(ZlmMediaServer::getIp, ip));
    }

    /**
     * 根据端口查询
     */
    public ZlmMediaServer getByHttpPort(Integer httpPort) {
        return getOne(new LambdaQueryWrapper<ZlmMediaServer>()
            .eq(ZlmMediaServer::getHttpPort, httpPort));
    }

    /**
     * 获取在线服务器
     */
    public List<ZlmMediaServer> listOnline() {
        return list(new LambdaQueryWrapper<ZlmMediaServer>()
            .eq(ZlmMediaServer::getKeepAliveTime, 1));
    }

    /**
     * 分页查询
     */
    public IPage<ZlmMediaServer> pageServers(Page<ZlmMediaServer> page, ZlmMediaServer server) {
        LambdaQueryWrapper<ZlmMediaServer> wrapper = new LambdaQueryWrapper<>();
        if (server.getIp() != null) {
            wrapper.like(ZlmMediaServer::getIp, server.getIp());
        }
        if (server.getKeepAliveTime() != null) {
            wrapper.eq(ZlmMediaServer::getKeepAliveTime, server.getKeepAliveTime());
        }
        wrapper.orderByDesc(ZlmMediaServer::getCreateTime);
        return page(page, wrapper);
    }

}

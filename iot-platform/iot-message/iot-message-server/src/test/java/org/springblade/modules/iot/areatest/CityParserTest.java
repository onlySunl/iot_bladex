package org.springblade.modules.iot.areatest;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.date.TimeInterval;
import cn.hutool.log.StaticLog;
import com.mqttsnet.basic.context.ContextUtil;
import org.springblade.modules.iot.system.entity.system.DefArea;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@Slf4j
public class CityParserTest {

    @Resource
    CityParser cityParser;
    @Resource
    SqlCityParserDecorator sqlCityParserDecorator;

    @BeforeEach
    public void setTenant() {
        ContextUtil.setDefTenantId();
    }


    /**
     * 实时爬取最新的地区数据，请执行该方法
     */
    @Test
    public void pullArea() {

        TimeInterval timer = DateUtil.timer();
        // 传递3、4时，数据量很大，抓取数据非常慢！
        // 抓取的层级 从0开始，0-省 1-市 2-区 3-镇 4-乡 （需要到乡级别直接传4即可）
        List<DefArea> list = cityParser.parseProvinces(4);
        long interval = timer.interval();// 花费毫秒数
        long intervalMinute = timer.intervalMinute();// 花费分钟数
        StaticLog.error("爬取数据 花费毫秒数: {} ,   花费分钟数:{} . ", interval, intervalMinute);

        TimeInterval timer2 = DateUtil.timer();
        // 持久化
        sqlCityParserDecorator.batchSave(list);

        // ---------------------------------
        long interval2 = timer2.interval();// 花费毫秒数
        long intervalMinute2 = timer2.intervalMinute();// 花费分钟数
        StaticLog.error("保存数据 花费毫秒数: {} ,   花费分钟数:{} . ", interval2, intervalMinute2);
    }

}

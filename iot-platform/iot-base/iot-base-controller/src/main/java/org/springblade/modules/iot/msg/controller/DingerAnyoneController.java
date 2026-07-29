package org.springblade.modules.iot.msg.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: blade-cloud
 * @description: 消息发送Controller
 * @packagename: org.springblade.modules.iot.dinger.controller
 * @author: ShiHuan Sun
 * @e-mainl: 13733918655@163.com
 * @date: 2023-09-11 17:33
 **/
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/anyone/dinger")
@Tag(name = "测试消息推送")
public class DingerAnyoneController {

}

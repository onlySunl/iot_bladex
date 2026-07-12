/*
 *
 * Copyright (c) 2025, NexIoT. All Rights Reserved.
 *
 * @Description: 本文件由 gitee.com/NexIoT 开发并拥有版权，未经授权严禁擅自商用、复制或传播。
 * @Author: gitee.com/NexIoT
 * @Email: wo8335224@gmail.com
 * @Wechat: outlookFil
 *
 *
 */

package org.springblade.modules.iot.rule.parser;

import org.springblade.modules.iot.rule.enums.ParserFormat;
import org.springblade.modules.iot.rule.model.RuleParserResult;

/**
 * 模型解析器策略 @Author gitee.com/NexIoT
 *
 * @since 2025/12/3 9:07
 */
public interface RuleModelParserStrategy {

  ParserFormat getFormat();

  RuleParserResult parse(String modelDefineString);
}

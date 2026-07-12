-- ============================================================
-- NexIoT 迁移到 BladeX - 数据库表结构
-- 产品管理、设备管理、规则引擎
-- ============================================================

-- 1. 产品表
CREATE TABLE IF NOT EXISTS `iot_product` (
  `id` bigint NOT NULL COMMENT '主键ID',
  `product_key` varchar(64) DEFAULT NULL COMMENT '产品Key（唯一标识）',
  `product_secret` varchar(128) DEFAULT NULL COMMENT '产品密钥（一型一密）',
  `name` varchar(128) DEFAULT NULL COMMENT '产品名称',
  `device_node` varchar(32) DEFAULT 'device' COMMENT '设备类型: gateway-网关, device-设备',
  `gw_product_key` varchar(64) DEFAULT NULL COMMENT '所属网关产品的ProductKey',
  `classified_id` varchar(64) DEFAULT NULL COMMENT '分类ID',
  `classified_name` varchar(128) DEFAULT NULL COMMENT '分类名称',
  `network_union_id` varchar(64) DEFAULT NULL COMMENT '网络组件ID',
  `transport_protocol` varchar(32) DEFAULT 'MQTT' COMMENT '传输协议: MQTT,COAP,UDP',
  `message_protocol` varchar(64) DEFAULT NULL COMMENT '消息协议',
  `third_platform` varchar(64) DEFAULT NULL COMMENT '接入方式/第三方平台',
  `third_configuration` text COMMENT '第三方平台配置信息',
  `configuration` text COMMENT '协议配置',
  `metadata` text COMMENT '物模型（JSON）',
  `store_policy` varchar(32) DEFAULT NULL COMMENT '数据存储策略',
  `store_policy_configuration` text COMMENT '数据存储策略配置',
  `photo_url` varchar(512) DEFAULT NULL COMMENT '图片地址',
  `tags` varchar(256) DEFAULT NULL COMMENT '产品标签',
  `describe_info` varchar(512) DEFAULT NULL COMMENT '说明',
  `state` tinyint DEFAULT 0 COMMENT '产品状态: 0-开发中, 1-已发布',
  `creator_id` bigint DEFAULT NULL COMMENT '创建者ID',
  `tenant_id` varchar(12) DEFAULT '000000' COMMENT '租户编号',
  `is_deleted` int DEFAULT 0 COMMENT '是否已删除',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_product_key` (`product_key`, `tenant_id`),
  KEY `idx_classified_id` (`classified_id`),
  KEY `idx_tenant_id` (`tenant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='IoT产品表';

-- 2. 设备表
CREATE TABLE IF NOT EXISTS `iot_device` (
  `id` bigint NOT NULL COMMENT '主键ID',
  `iot_id` varchar(64) DEFAULT NULL COMMENT '设备唯一标识符',
  `device_id` varchar(64) DEFAULT NULL COMMENT '设备序列号',
  `device_name` varchar(128) DEFAULT NULL COMMENT '设备名称',
  `device_secret` varchar(128) DEFAULT NULL COMMENT '设备密钥',
  `product_key` varchar(64) DEFAULT NULL COMMENT '产品Key',
  `product_name` varchar(128) DEFAULT NULL COMMENT '产品名称',
  `gw_product_key` varchar(64) DEFAULT NULL COMMENT '网关产品ProductKey',
  `device_node` varchar(32) DEFAULT NULL COMMENT '设备节点类型',
  `third_platform` varchar(64) DEFAULT NULL COMMENT '第三方平台',
  `state` tinyint DEFAULT 0 COMMENT '在线状态: 0-离线, 1-在线',
  `registry_time` bigint DEFAULT NULL COMMENT '激活时间',
  `online_time` bigint DEFAULT NULL COMMENT '最后上线时间',
  `ext_device_id` varchar(128) DEFAULT NULL COMMENT '第三方设备ID',
  `device_tag` varchar(256) DEFAULT NULL COMMENT '设备标签',
  `device_address` varchar(256) DEFAULT NULL COMMENT '设备地址',
  `signal_strength` varchar(32) DEFAULT NULL COMMENT 'CSQ信号强度',
  `coordinate` varchar(128) DEFAULT NULL COMMENT '坐标',
  `areas_id` varchar(64) DEFAULT NULL COMMENT '区域ID',
  `derive_metadata` text COMMENT '派生元数据',
  `configuration` text COMMENT '其他配置',
  `detail` varchar(512) DEFAULT NULL COMMENT '备注',
  `ext1` varchar(256) DEFAULT NULL COMMENT '扩展字段1',
  `ext2` varchar(256) DEFAULT NULL COMMENT '扩展字段2',
  `ext3` varchar(256) DEFAULT NULL COMMENT '扩展字段3',
  `ext4` varchar(256) DEFAULT NULL COMMENT '扩展字段4',
  `creator_id` bigint DEFAULT NULL COMMENT '创建者ID',
  `tenant_id` varchar(12) DEFAULT '000000' COMMENT '租户编号',
  `is_deleted` int DEFAULT 0 COMMENT '是否已删除',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_iot_id` (`iot_id`),
  KEY `idx_product_key` (`product_key`),
  KEY `idx_device_id` (`device_id`),
  KEY `idx_tenant_id` (`tenant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='IoT设备表';

-- 3. 设备分组表
CREATE TABLE IF NOT EXISTS `iot_device_group` (
  `id` bigint NOT NULL COMMENT '主键ID',
  `name` varchar(128) DEFAULT NULL COMMENT '分组名称',
  `describe_info` varchar(512) DEFAULT NULL COMMENT '分组描述',
  `parent_id` bigint DEFAULT 0 COMMENT '父分组ID',
  `creator_id` bigint DEFAULT NULL COMMENT '创建者ID',
  `tenant_id` varchar(12) DEFAULT '000000' COMMENT '租户编号',
  `is_deleted` int DEFAULT 0 COMMENT '是否已删除',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `idx_parent_id` (`parent_id`),
  KEY `idx_tenant_id` (`tenant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='IoT设备分组表';

-- 4. 设备-分组关联表
CREATE TABLE IF NOT EXISTS `iot_device_group_union` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `device_id` bigint DEFAULT NULL COMMENT '设备ID',
  `group_id` bigint DEFAULT NULL COMMENT '分组ID',
  PRIMARY KEY (`id`),
  KEY `idx_device_id` (`device_id`),
  KEY `idx_group_id` (`group_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='设备-分组关联表';

-- 5. 产品物模型-功能定义表
CREATE TABLE IF NOT EXISTS `iot_product_function` (
  `id` bigint NOT NULL COMMENT '主键ID',
  `product_key` varchar(64) DEFAULT NULL COMMENT '产品Key',
  `tag` varchar(32) DEFAULT NULL COMMENT '标签: property-属性, event-事件, function-功能',
  `name` varchar(128) DEFAULT NULL COMMENT '功能名称',
  `alias` varchar(128) DEFAULT NULL COMMENT '别名',
  `access_mode` varchar(16) DEFAULT NULL COMMENT '访问模式: r-读, rw-读写',
  `description` varchar(512) DEFAULT NULL COMMENT '描述',
  `input` text COMMENT '输入参数（JSON）',
  `output` text COMMENT '输出参数（JSON）',
  `creator_id` bigint DEFAULT NULL COMMENT '创建者ID',
  `tenant_id` varchar(12) DEFAULT '000000' COMMENT '租户编号',
  `is_deleted` int DEFAULT 0 COMMENT '是否已删除',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `idx_product_key` (`product_key`),
  KEY `idx_tenant_id` (`tenant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='IoT产品物模型功能定义表';

-- 6. 规则模型表
CREATE TABLE IF NOT EXISTS `iot_rule_model` (
  `id` bigint NOT NULL COMMENT '主键ID',
  `rule_name` varchar(128) DEFAULT NULL COMMENT '规则名称',
  `data_level` varchar(32) DEFAULT 'PRODUCT' COMMENT '数据级别: PRODUCT-产品, DEVICE-设备, GROUP-分组',
  `description` varchar(512) DEFAULT NULL COMMENT '描述',
  `status` varchar(16) DEFAULT 'stop' COMMENT '状态: start-启用, stop-停用',
  `product_key` varchar(64) DEFAULT NULL COMMENT '产品KEY',
  `config` text COMMENT '规则配置（JSON）',
  `creator_id` bigint DEFAULT NULL COMMENT '创建者ID',
  `tenant_id` varchar(12) DEFAULT '000000' COMMENT '租户编号',
  `is_deleted` int DEFAULT 0 COMMENT '是否已删除',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `idx_product_key` (`product_key`),
  KEY `idx_tenant_id` (`tenant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='IoT规则模型表';

-- 7. 规则模型实例表（规则与设备/分组的绑定关系）
CREATE TABLE IF NOT EXISTS `iot_rule_model_instance` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `model_id` bigint DEFAULT NULL COMMENT '规则模型ID',
  `device_id` varchar(64) DEFAULT NULL COMMENT '设备ID',
  `group_id` varchar(64) DEFAULT NULL COMMENT '分组ID',
  PRIMARY KEY (`id`),
  KEY `idx_model_id` (`model_id`),
  KEY `idx_device_id` (`device_id`),
  KEY `idx_group_id` (`group_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='IoT规则模型实例表';

-- 8. 产品分类表
CREATE TABLE IF NOT EXISTS `iot_product_sort` (
  `id` bigint NOT NULL COMMENT '主键ID',
  `name` varchar(128) DEFAULT NULL COMMENT '分类名称',
  `parent_id` bigint DEFAULT 0 COMMENT '父分类ID',
  `sort` int DEFAULT 0 COMMENT '排序',
  `remark` varchar(512) DEFAULT NULL COMMENT '备注',
  `creator_id` bigint DEFAULT NULL COMMENT '创建者ID',
  `tenant_id` varchar(12) DEFAULT '000000' COMMENT '租户编号',
  `is_deleted` int DEFAULT 0 COMMENT '是否已删除',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `idx_parent_id` (`parent_id`),
  KEY `idx_tenant_id` (`tenant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='IoT产品分类表';

-- 9. 设备证书表
CREATE TABLE IF NOT EXISTS `iot_certificate` (
  `id` bigint NOT NULL COMMENT '主键ID',
  `name` varchar(128) DEFAULT NULL COMMENT '证书名称',
  `type` varchar(32) DEFAULT NULL COMMENT '证书类型: CA-CA证书, DEVICE-设备证书',
  `cert_data` text COMMENT '证书内容',
  `private_key` text COMMENT '私钥',
  `public_key` text COMMENT '公钥',
  `signature` varchar(512) DEFAULT NULL COMMENT '签名',
  `expire_time` bigint DEFAULT NULL COMMENT '过期时间',
  `creator_id` bigint DEFAULT NULL COMMENT '创建者ID',
  `tenant_id` varchar(12) DEFAULT '000000' COMMENT '租户编号',
  `is_deleted` int DEFAULT 0 COMMENT '是否已删除',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `idx_tenant_id` (`tenant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='IoT设备证书表';

-- 10. 设备日志表
CREATE TABLE IF NOT EXISTS `iot_device_log` (
  `id` bigint NOT NULL COMMENT '主键ID',
  `product_key` varchar(64) DEFAULT NULL COMMENT '产品Key',
  `device_id` varchar(64) DEFAULT NULL COMMENT '设备ID',
  `msg_id` varchar(128) DEFAULT NULL COMMENT '消息ID',
  `type` varchar(32) DEFAULT NULL COMMENT '日志类型: PROPERTY-属性, EVENT-事件, FUNCTION-功能, OTA-升级',
  `action` varchar(128) DEFAULT NULL COMMENT '操作',
  `option` varchar(32) DEFAULT NULL COMMENT '操作类型: READ-读, WRITE-写, REPORT-上报',
  `data` text COMMENT '数据内容（JSON）',
  `creator_id` bigint DEFAULT NULL COMMENT '创建者ID',
  `tenant_id` varchar(12) DEFAULT '000000' COMMENT '租户编号',
  `is_deleted` int DEFAULT 0 COMMENT '是否已删除',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `idx_product_key` (`product_key`),
  KEY `idx_device_id` (`device_id`),
  KEY `idx_tenant_id` (`tenant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='IoT设备日志表';

-- 11. 设备标签表
CREATE TABLE IF NOT EXISTS `iot_device_tags` (
  `id` bigint NOT NULL COMMENT '主键ID',
  `device_id` bigint DEFAULT NULL COMMENT '设备ID',
  `tag_key` varchar(128) DEFAULT NULL COMMENT '标签Key',
  `tag_value` varchar(256) DEFAULT NULL COMMENT '标签Value',
  `tag_name` varchar(128) DEFAULT NULL COMMENT '标签名称',
  `tag_remark` varchar(512) DEFAULT NULL COMMENT '标签备注',
  `creator_id` bigint DEFAULT NULL COMMENT '创建者ID',
  `tenant_id` varchar(12) DEFAULT '000000' COMMENT '租户编号',
  `is_deleted` int DEFAULT 0 COMMENT '是否已删除',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `idx_device_id` (`device_id`),
  KEY `idx_tenant_id` (`tenant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='IoT设备标签表';

-- 12. 设备影子表
CREATE TABLE IF NOT EXISTS `iot_device_shadow` (
  `id` bigint NOT NULL COMMENT '主键ID',
  `device_id` bigint DEFAULT NULL COMMENT '设备ID',
  `state` text COMMENT '设备状态（JSON）',
  `metadata` text COMMENT '元数据（JSON）',
  `last_update_time` bigint DEFAULT NULL COMMENT '最后更新时间',
  `creator_id` bigint DEFAULT NULL COMMENT '创建者ID',
  `tenant_id` varchar(12) DEFAULT '000000' COMMENT '租户编号',
  `is_deleted` int DEFAULT 0 COMMENT '是否已删除',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_device_id` (`device_id`),
  KEY `idx_tenant_id` (`tenant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='IoT设备影子表';

-- 13. 设备订阅表
CREATE TABLE IF NOT EXISTS `iot_device_subscribe` (
  `id` bigint NOT NULL COMMENT '主键ID',
  `device_id` bigint DEFAULT NULL COMMENT '设备ID',
  `type` varchar(32) DEFAULT NULL COMMENT '订阅类型: PRODUCT-产品, DEVICE-设备, PART-部分',
  `topic_id` varchar(128) DEFAULT NULL COMMENT '主题ID',
  `creator_id` bigint DEFAULT NULL COMMENT '创建者ID',
  `tenant_id` varchar(12) DEFAULT '000000' COMMENT '租户编号',
  `is_deleted` int DEFAULT 0 COMMENT '是否已删除',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `idx_device_id` (`device_id`),
  KEY `idx_tenant_id` (`tenant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='IoT设备订阅表';

-- 14. 场景联动表
CREATE TABLE IF NOT EXISTS `iot_scene_linkage` (
  `id` bigint NOT NULL COMMENT '主键ID',
  `scene_name` varchar(128) DEFAULT NULL COMMENT '场景名称',
  `scene_description` varchar(512) DEFAULT NULL COMMENT '场景描述',
  `trigger_type` varchar(32) DEFAULT NULL COMMENT '触发类型: DEVICE-设备触发, TIMER-定时触发, MANUAL-手动触发',
  `trigger_config` text COMMENT '触发配置（JSON）',
  `action_config` text COMMENT '执行动作配置（JSON）',
  `status` tinyint DEFAULT 1 COMMENT '状态: 0-停用, 1-启用',
  `creator_id` bigint DEFAULT NULL COMMENT '创建者ID',
  `tenant_id` varchar(12) DEFAULT '000000' COMMENT '租户编号',
  `is_deleted` int DEFAULT 0 COMMENT '是否已删除',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `idx_tenant_id` (`tenant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='IoT场景联动表';

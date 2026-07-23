# BLADE_IOT 物联网平台

## 项目概述
基于 Spring BladeX 框架的 IoT 物联网后端平台，提供设备管理、数据采集、租户管理等核心功能。

## 技术栈
- **语言**: Java 17
- **框架**: Spring Boot 3.2.10
- **构建工具**: Maven
- **核心框架**: BladeX 4.9.0.RELEASE
- **数据库**: MySQL (支持 Druid 连接池)
- **缓存**: Redis (Spring Session)
- **ORM**: MyBatis-Plus

## 目录结构
```
/workspace/projects/
├── BLADE_IOT.iml              # 项目 IDEA 配置
├── pom.xml                    # 父 POM (多模块)
├── Dockerfile                 # Docker 构建文件
├── blade-server/              # 服务端模块 (主启动入口)
│   ├── pom.xml
│   ├── src/main/java/
│   │   └── org/springblade/Application.java  # 启动类
│   └── src/main/resources/
│       └── application.yml    # 主配置文件
├── blade-common/             # 公共模块
├── blade-api/                # BladeX 系统 API 模块
├── iot-platform/             # IoT 管理平台模块 (迁移自 enjoy-iot)
│   ├── iot-entity/           # IoT 实体类模块 (按域拆分, 29 个实体)
│   │   ├── iot-entity-product/       # 产品实体 (ProductDO, CategoryDO)
│   │   ├── iot-entity-device/        # 设备实体 (DeviceInfoDO, DeviceConfigDO, DeviceGroupDO 等)
│   │   ├── iot-entity-alert/         # 告警实体 (AlertConfigDO, AlertRecordDO, ChannelDO 等)
│   │   ├── iot-entity-rule/          # 规则实体 (EiotRuleInfoDO)
│   │   ├── iot-entity-component/     # 组件实体 (ComponentDO, ModbusInfoDO 等)
│   │   ├── iot-entity-sip/           # SIP 实体 (SipConfigDO, SipDeviceDO, MediaServerDO 等)
│   │   ├── iot-entity-ota/           # OTA 实体 (OtaPackageDO, OtaDetailDO)
│   │   ├── iot-entity-thingmodel/    # 物模型实体 (ThingModelDO)
│   │   ├── iot-entity-virtual/       # 虚拟设备实体 (VirtualDeviceDO, VirtualDeviceMappingDO)
│   │   └── iot-entity-common/        # 通用实体 (GroupDO, ShowModelDO, TaskInfoDO 等)
│   ├── module-iot-api/      # IoT API 层 (VO/DTO/枚举, 54 Java 文件)
│   ├── module-iot-biz/      # IoT 业务层 (Controller/Service/Mapper, 255 Java 文件)
│   ├── module-iot-core/     # IoT 核心模块
│   │   ├── iot-common-core/      # 公共核心 (框架适配器/BaseDO/工具类)
│   │   ├── iot-common-thing/     # 物模型 (ThingModel/属性/事件/服务)
│   │   ├── iot-message-bus/      # 消息总线
│   │   │   ├── iot-message-core/     # 消息核心抽象
│   │   │   ├── iot-message-spring/   # Spring Event 实现
│   │   │   ├── iot-message-kafka/    # Kafka 实现
│   │   │   ├── iot-message-rocketmq/ # RocketMQ 实现
│   │   │   ├── iot-message-actor/    # Akka Actor 实现
│   │   │   └── iot-message-vertx/    # Vert.x 实现
│   │   ├── iot-message-notify/   # 消息通知 (邮件/短信/语音)
│   │   ├── iot-rule-engine/      # 规则引擎
│   │   ├── iot-script-engine/    # 脚本引擎 (JavaScript/Groovy)
│   │   └── iot-virtual-device/   # 虚拟设备
│   ├── iot-components/       # 协议组件
│   │   ├── iot-component-core/       # 组件核心抽象
│   │   ├── iot-component-mqtt/       # MQTT 组件
│   │   ├── iot-component-emqx/       # EMQX 组件
│   │   ├── iot-component-http/       # HTTP 组件
│   │   ├── iot-component-tcp/        # TCP 组件
│   │   ├── iot-component-udp/        # UDP 组件
│   │   ├── iot-component-coap/       # CoAP 组件
│   │   └── iot-component-modbus-custom/ # Modbus 自定义组件
│   ├── module-iot-task/     # 定时任务 (设备超时/告警检查, 9 Java 文件)
│   └── module-iot-temporal/ # 时序数据存储
│       ├── iot-temporal-service/         # 时序服务抽象
│       ├── iot-temporal-serviceimpl-td/  # TDengine 实现
│       ├── iot-temporal-serviceimpl-iotdb/   # IoTDB 实现
│       ├── iot-temporal-serviceimpl-timescaledb/ # TimescaleDB 实现
│       └── iot-temporal-serviceimpl-kw/  # Kingbase 实现
├── nvr-platform/             # NVR 平台业务模块
│   ├── nvr-common/           # NVR 公共模块 (实体基类, 工具类)
│   ├── nvr-api/              # NVR API 接口模块
│   │   ├── nvr-api-dahua/    # 大华设备 API
│   │   ├── nvr-api-zlm/      # ZLM 流媒体 API
│   │   ├── nvr-api-haikang/  # 海康设备 API
│   │   ├── nvr-api-onvif/    # ONVIF 设备 API
│   │   ├── nvr-api-qs/       # QS 设备 API
│   │   ├── nvr-api-haikang-isup/  # 海康 ISUP API
│   │   ├── nvr-api-gb28181/  # GB28181 国标 API
│   │   └── nvr-api-jt1078/   # JT1078 部标 API
│   ├── nvr-biz/              # NVR 业务模块
│   ├── nvr-protocol/         # NVR 协议模块
│   │   ├── nvr-protocol-common/
│   │   ├── nvr-protocol-haikang-isup/
│   │   ├── nvr-protocol-onvif/
│   │   ├── nvr-protocol-qs/
│   │   └── nvr-protocol-zlm/
│   └── nvr-service/          # NVR 服务模块
└── research-webchart/        # 数据可视化模块
```

## 关键入口 / 核心模块
- **启动入口**: `blade-server/src/main/java/org/springblade/Application.java`
- **服务端口**: `8093` (Undertow)
- **主配置**: `blade-server/src/main/resources/application.yml`
- **多环境配置**: application-dev.yml, application-test.yml, application-prod.yml, application-ys.yml

## 运行与构建
```bash
# 编译打包
mvn clean package -DskipTests

# 启动服务 (开发环境)
mvn spring-boot:run -pl blade-server -Dspring-boot.run.profiles=dev

# Docker 构建
docker build -t blade-iot .
```

## 用户偏好与长期约束
- Java 版本锁定为 17
- MySQL 数据库，使用 Druid 连接池
- 多租户支持（字段隔离模式）
- 依赖 Spring BladeX 商业版组件

## 常见问题和预防
- 启动前需确保 MySQL 和 Redis 服务可用
- 多环境配置需根据实际环境修改 application-{profile}.yml
- Docker 部署端口映射: 8093:8093
- **SPI 接口默认实现**: `ChannelSmsStrategy`（短信）和 `ChannelVmsStrategy`（语音）是 SPI 接口，项目提供阿里云和腾讯云的实现。通过 `config.alert.sms-provider` 和 `config.alert.vms-provider` 配置项切换服务商，默认使用阿里云（`matchIfMissing = true`）。

## Elasticsearch API 迁移 (Spring Boot 3.x 适配)
- **Spring Data Elasticsearch 版本**: 项目使用 Spring Boot 3.2.10，对应 Spring Data Elasticsearch 5.x
- **旧 API 替换**:
  - `ElasticsearchRestTemplate` → `ElasticsearchOperations` (接口) 或 `ElasticsearchTemplate` (实现)
  - `NativeSearchQueryBuilder` → `NativeQueryBuilder`
  - `NativeSearchQuery` → `NativeQuery`
  - `org.elasticsearch.index.query.QueryBuilders` → `co.elastic.clients.elasticsearch._types.query_dsl.Query`
  - `org.elasticsearch.search.aggregations.AggregationBuilders` → `co.elastic.clients.elasticsearch._types.aggregations.Aggregation`
- **依赖变更**: 移除 `spring-data-elasticsearch:4.2.3` (Spring Boot 2.x)，使用 `spring-boot-starter-data-elasticsearch` (Spring Boot 3.x 自动管理版本)
- **Easy-ES**: 项目同时使用 Easy-ES 2.1.0 作为 ES ORM 框架，配置见 `application.yml` 的 `easy-es` 段

## ISUP 流媒体处理关键经验
- **PSStreamDemuxer 数据完整性**：`processPSData` 必须等待至少两个 Pack Header 才处理数据，确保只解析完整的 PS 包序列。单 Pack Header 时继续累积，避免 PES 跨回调截断导致花屏。仅在缓冲区达到 `BUFFER_MAX_CAPACITY` (1MB) 时强制处理防止 OOM。
- **fragmentBuffer 跨 PES 拼接**：NAL 单元可能跨越多个 PES 包，`extractNalFromPayload` 使用 `fragmentBuffer` 累积不完整 NAL 片段，等待下一个 PES 的起始码确认边界后再输出。
- **回放 vs 预览**：回放流（PlaybackStreamHandler）和预览流（PreviewStreamHandler）共用 PSStreamDemuxer，但回放流的 PES 包更大（IDR帧可达数KB），更容易出现跨回调截断。
- **SPS/PPS 与 IDR 时间戳对齐**：fragmentBuffer 机制导致 SPS/PPS 和 IDR 可能被分到不同的 `processPSData` 调用。PlaybackStreamHandler 使用 `pendingNalUnits` 缓冲 non-VCL NAL（SPS/PPS/SEI），等 VCL NAL（IDR/Non-IDR）到达后一起发送，确保同一 Access Unit 的所有 NAL 使用相同 RTP 时间戳。
- **RTP Marker bit 规范**：Marker bit 仅在 Access Unit 的最后一个 RTP 包设为 1。SPS/PPS 等 non-VCL NAL 不设 M=1，VCL NAL（IDR/Non-IDR）设为 M=1。FU-A 分片仅在最后一个分片设 M=1。

## IoT 管理模块（迁移自 enjoy-iot）

### 概述
从 enjoy-iot 项目（https://gitee.com/open-enjoy/enjoy-iot.git）完整迁移的 IoT 物联网管理平台，包含设备管理、产品管理、告警管理、规则引擎、消息总线、协议组件、时序数据存储等功能。全部按 BladeX 4.2.0 标准重构，共 662 个 Java 文件。

### 代码位置
- **实体类/VO/DTO**: `iot-platform/module-iot-api/src/main/java/org/springblade/modules/iot/`
- **Controller/Service/Mapper**: `iot-platform/module-iot-biz/src/main/java/org/springblade/modules/iot/`
- **框架适配器**: `iot-platform/module-iot-core/iot-common-core/src/main/java/org/springblade/modules/iot/framework/`
- **物模型**: `iot-platform/module-iot-core/iot-common-thing/`
- **消息总线**: `iot-platform/module-iot-core/iot-message-bus/`
- **规则引擎**: `iot-platform/module-iot-core/iot-rule-engine/`
- **协议组件**: `iot-platform/iot-components/`
- **定时任务**: `iot-platform/module-iot-task/`
- **时序存储**: `iot-platform/module-iot-temporal/`

### 包名映射
| 原包名 | 新包名 |
|---|---|
| `com.enjoyiot.module.eiot` | `org.springblade.modules.iot` |
| `com.enjoyiot.eiot` | `org.springblade.modules.iot` |
| `com.enjoyiot.framework.*` | `org.springblade.modules.iot.framework.*` (适配器) |

### 框架适配层
迁移代码使用适配器模式桥接 enjoy-iot 框架到 BladeX：
- `BaseDO` → 独立基类（id/createTime/updateTime/creator/updater/deleted/tenantId）
- `BaseMapperX` → 扩展 `BladeMapper`
- `CommonResult` → 兼容响应封装
- `PageParam/PageResult` → 分页参数/结果封装
- `ServiceException/ErrorCode` → 异常封装
- `LambdaQueryWrapperX/MPJLambdaWrapperX` → MyBatis-Plus 查询封装
- `JsonUtils/BeanUtils/CollectionUtils` → 工具类封装
- `TenantBaseDO/TenantIgnore/TenantContextHolder` → 租户适配

### iot-entity 实体类模块
从 `module-iot-biz/dal/dataobject/` 拆分为独立的 `iot-entity` 模块，按业务域拆分为 10 个子模块，共 29 个实体类。

**改造规则：**
- 实体类继承 `CustomBaseEntity`（替代 `TenantBaseDO`/`BaseDO`）
- 删除公共字段（id、status、remark、createUser、updateUser、createTime、updateTime、tenantId、deleted）
- 所有字段添加 `@TableField("snake_case")` 和 `@AutoColumn` 注解
- 注释掉 `@KeySequence` 注解
- 包名统一为 `org.springblade.modules.iot.entity`

**子模块映射：**
| 子模块 | 实体类 | 说明 |
|---|---|---|
| `iot-entity-product` | ProductDO, CategoryDO | 产品管理 |
| `iot-entity-device` | EiotDeviceInfoDO, DeviceConfigDO, DeviceGroupDO, DeviceChannelDO, DeviceOtaInfoDO | 设备管理 |
| `iot-entity-alert` | AlertConfigDO, AlertRecordDO, ChannelDO, ChannelConfigDO, ChannelTemplateDO | 告警管理 |
| `iot-entity-rule` | EiotRuleInfoDO | 规则引擎 |
| `iot-entity-component` | ComponentDO, ModbusInfoDO, ModbusThingModelDO | 协议组件 |
| `iot-entity-sip` | SipConfigDO, SipDeviceDO, SipRelationDO, MediaServerDO | SIP/流媒体 |
| `iot-entity-ota` | OtaPackageDO, OtaDetailDO | OTA 升级 |
| `iot-entity-thingmodel` | ThingModelDO | 物模型 |
| `iot-entity-virtual` | VirtualDeviceDO, VirtualDeviceMappingDO | 虚拟设备 |
| `iot-entity-common` | GroupDO, ShowModelDO, TaskInfoDO, IotNotifyMessageDO | 通用实体 |

### 迁移统计
- **module-iot-api**: 54 Java 文件（实体/VO/DTO/枚举）
- **module-iot-biz**: 255 Java 文件（Controller/Service/Mapper）
- **module-iot-core**: 149 Java 文件（核心/消息/规则/脚本/物模型）
- **iot-components**: 88 Java 文件（协议组件）
- **module-iot-task**: 9 Java 文件（定时任务）
- **module-iot-temporal**: 107 Java 文件（时序数据存储）
- **总计**: 662 Java 文件

## enjoy-iot 源码迁移（enjoy-iot → BladeX）

### 迁移映射表
| 源模块 (enjoy-iot) | 目标模块 | 说明 |
|---|---|---|
| `module-iot/module-iot-api` | `iot-platform/module-iot-api` | API 层（实体/VO/DTO） |
| `module-iot/module-iot-biz` | `iot-platform/module-iot-biz` | 业务层（Controller/Service/Mapper） |
| `module-iot/module-iot-core` | `iot-platform/module-iot-core` | 核心模块（物模型/消息总线/规则引擎） |
| `module-iot/iot-components` | `iot-platform/iot-components` | 协议组件（MQTT/HTTP/CoAP/Modbus/TCP/UDP） |
| `module-iot/module-iot-task` | `iot-platform/module-iot-task` | 定时任务 |
| `module-iot/module-iot-temporal` | `iot-platform/module-iot-temporal` | 时序数据存储 |
- **DDL**: `iot-platform/iot-common/src/main/resources/sql/nexiot-migration.sql`

### 数据表
| 表名 | 说明 |
|---|---|
| `iot_product` | 产品表 |
| `iot_device` | 设备表 |
| `iot_product_function` | 产品功能定义（物模型） |
| `iot_product_sort` | 产品分类（树形结构） |
| `iot_device_group` | 设备分组 |
| `iot_device_group_union` | 设备-分组关联 |
| `iot_device_log` | 设备日志 |
| `iot_device_tags` | 设备标签 |
| `iot_device_shadow` | 设备影子（期望/上报状态） |
| `iot_device_subscribe` | 设备订阅（MQTT Topic） |
| `iot_certificate` | 设备证书（SSL/TLS） |
| `iot_rule_model` | 规则模型 |
| `iot_scene_linkage` | 场景联动 |
| `iot_protocol` | 协议定义（MQTT/HTTP/TCP/UDP/CoAP） |

### 协议层架构
迁移自 NexIoT 协议框架，按 BladeX 标准重构，独立 `iot-protocol` 模块：

**`iot-protocol/iot-protocol-common`** — 协议核心抽象：
- `ProtocolType` - 协议类型枚举（MQTT/HTTP/TCP/UDP/CoAP）
- `ProtocolDefinition` - 协议定义（名称/类型/配置/状态）
- `ProtocolCodec` - 协议编解码接口（encode/decode）
- `ProtocolRegistry` - 协议注册中心（Spring Bean 自动发现）
- `DeviceMessage` / `PropertyMessage` / `EventMessage` / `ServiceCallMessage` - 设备消息模型

**`iot-protocol/iot-protocol-mqtt`** — MQTT 协议：
- `MqttConnectionConfig` - MQTT 连接配置
- `MqttClientService` - MQTT 客户端（Eclipse Paho，异步连接/发布/订阅/自动重连）
- `MqttJsonCodec` - JSON 协议编解码器

**`iot-protocol/iot-protocol-http`** — HTTP 协议：
- `HttpConnectionConfig` - HTTP 连接配置
- `HttpClientService` - HTTP 客户端（RestTemplate，GET/POST/异步）

**`iot-protocol/iot-protocol-codec`** — 通用编解码：
- `JsonProtocolCodec` - JSON 协议编解码器
- `ServiceCallMessage` - 服务调用消息（serviceId/params/timeout）

**MQTT 协议**（`iot-service`）：
- `MqttConnectionConfig` - MQTT 连接配置（broker/topic/auth/QoS/SSL）
- `MqttClientService` - MQTT 客户端服务（Eclipse Paho，连接/发布/订阅/重连）
- `MqttJsonCodec` - JSON 协议编解码器（DeviceMessage ↔ JSON bytes）

**HTTP 协议**（`iot-service`）：
- `HttpClientService` - HTTP 客户端服务（RestTemplate，GET/POST/异步）

**设备通信服务**（`iot-service`）：
- `DeviceMessageService` - 设备消息处理（属性/事件/上线/下线/日志）
- `DeviceDownlinkService` - 设备下行服务（服务调用/属性设置/命令下发）

**推送策略**（`iot-rule`）：
- `PushStrategy` - 推送策略接口（push/pushAsync）
- `HttpPushStrategy` - HTTP 推送（POST JSON）
- `MqttPushStrategy` - MQTT 推送（发布到 Topic）
- `PushStrategyManager` - 推送策略管理器（注册/执行/异步执行）

### API 接口
| 模块 | 路径前缀 | 说明 |
|---|---|---|
| 产品管理 | `/product/` | CRUD + 发布 |
| 产品分类 | `/product-sort/` | 树形列表/CRUD |
| 设备管理 | `/device/` | CRUD + 在线状态 |
| 设备日志 | `/device-log/` | 分页查询/详情 |
| 设备标签 | `/device-tags/` | CRUD |
| 设备影子 | `/device-shadow/` | 获取/更新影子 |
| 设备订阅 | `/device-subscribe/` | CRUD |
| 设备证书 | `/certificate/` | CRUD |
| 规则引擎 | `/rule/` | CRUD + 启停 |
| 场景联动 | `/scene-linkage/` | CRUD + 启停 |
|---|---|---|
| 产品管理 | `/product/` | CRUD + 发布 |
| 设备管理 | `/device/` | CRUD + 在线状态 |
| 规则引擎 | `/rule/` | CRUD + 启停 |

### 规则引擎
- **RuleEngine**: 执行 SQL 规则语句，过滤和转换设备上报数据
- **RuleSqlParser**: 解析类 SQL 语法规则（SELECT ... FROM ... WHERE ...）
- **RuleTransmitTemplate**: 数据转发模板（HTTP/MQTT/KAFKA/LOG）
- **RuleExecutionService**: 规则执行服务，异步匹配规则并转发

### BladeX 规范适配
- 实体类继承 `CustomBaseEntity`（含 id/create_time/update_time/tenant_id/is_deleted）
- Controller 继承 `BladeController`，使用 `R<T>` 统一响应
- Wrapper 继承 `BaseEntityWrapper`，使用 `BeanUtil.copyProperties` 转换
- Service 继承 `IService<T>` / `ServiceImpl<M, T>`
- Mapper 继承 `BaseMapper<T>`

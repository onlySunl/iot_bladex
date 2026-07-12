# BLADE_IOT 物联网平台

## 项目概述
基于 Spring BladeX 框架的 IoT 物联网后端平台，提供设备管理、数据采集、租户管理等核心功能。

## 技术栈
- **语言**: Java 17
- **框架**: Spring Boot 3.2.10
- **构建工具**: Maven
- **核心框架**: BladeX 4.2.0.RELEASE
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
├── iot-platform/             # IoT 管理平台模块 (NexIoT 迁移)
│   ├── iot-common/           # IoT 公共模块 (实体基类, 工具类, SQL DDL)
│   ├── iot-api/              # IoT API 模块 (实体/VO)
│   ├── iot-service/          # IoT 服务模块 (Mapper/Service/Wrapper/Controller)
│   ├── iot-rule/             # IoT 规则引擎模块 (规则引擎 + 推送策略 + 地理围栏 + Rulego)
│   ├── iot-persistence/      # IoT 持久化模块 (NexIoT 数据层迁移)
│   ├── iot-bridge/           # IoT 数据桥接模块
│   │   ├── iot-bridge-core/      # 桥接核心 (引擎/模板/插件抽象)
│   │   ├── iot-bridge-plugin-jdbc/   # JDBC 插件
│   │   ├── iot-bridge-plugin-kafka/  # Kafka 插件
│   │   ├── iot-bridge-plugin-mqtt/   # MQTT 插件
│   │   ├── iot-bridge-plugin-http/   # HTTP 插件
│   │   ├── iot-bridge-plugin-iotdb/  # IoTDB 插件
│   │   ├── iot-bridge-plugin-influxdb/ # InfluxDB 插件
│   │   ├── iot-bridge-starter/   # 桥接自动配置
│   │   └── iot-bridge-web/       # 桥接 Web 接口
│   └── iot-protocol/         # IoT 协议模块 (独立子模块)
│       ├── iot-protocol-common/  # 协议核心抽象 (ProtocolCodec/DeviceMessage)
│       ├── iot-protocol-mqtt/    # MQTT 协议 (完整实现: 处理器/Topic/OTA/第三方)
│       ├── iot-protocol-http/    # HTTP 协议 (完整实现: 处理器/编解码/服务)
│       ├── iot-protocol-codec/   # 通用编解码 (JSON)
│       └── iot-protocol-websocket/ # WebSocket 协议 (完整实现: 处理器/会话管理)
├── iot-monitor/              # IoT 监控模块 (NexIoT Web 层迁移)
│   ├── monitor/              # 缓存/线程监控
│   ├── listener/             # 应用启动监听
│   ├── web/config/           # Web 配置 (XSS/编解码/日志/拦截器)
│   ├── web/controller/       # 通用/OpenAPI 控制器
│   └── web/service/          # Web 服务层
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

## ISUP 流媒体处理关键经验
- **PSStreamDemuxer 数据完整性**：`processPSData` 必须等待至少两个 Pack Header 才处理数据，确保只解析完整的 PS 包序列。单 Pack Header 时继续累积，避免 PES 跨回调截断导致花屏。仅在缓冲区达到 `BUFFER_MAX_CAPACITY` (1MB) 时强制处理防止 OOM。
- **fragmentBuffer 跨 PES 拼接**：NAL 单元可能跨越多个 PES 包，`extractNalFromPayload` 使用 `fragmentBuffer` 累积不完整 NAL 片段，等待下一个 PES 的起始码确认边界后再输出。
- **回放 vs 预览**：回放流（PlaybackStreamHandler）和预览流（PreviewStreamHandler）共用 PSStreamDemuxer，但回放流的 PES 包更大（IDR帧可达数KB），更容易出现跨回调截断。
- **SPS/PPS 与 IDR 时间戳对齐**：fragmentBuffer 机制导致 SPS/PPS 和 IDR 可能被分到不同的 `processPSData` 调用。PlaybackStreamHandler 使用 `pendingNalUnits` 缓冲 non-VCL NAL（SPS/PPS/SEI），等 VCL NAL（IDR/Non-IDR）到达后一起发送，确保同一 Access Unit 的所有 NAL 使用相同 RTP 时间戳。
- **RTP Marker bit 规范**：Marker bit 仅在 Access Unit 的最后一个 RTP 包设为 1。SPS/PPS 等 non-VCL NAL 不设 M=1，VCL NAL（IDR/Non-IDR）设为 M=1。FU-A 分片仅在最后一个分片设 M=1。

## IoT 管理模块（迁移自 NexIoT）

### 概述
从 NexIoT 项目完整迁移的 IoT 物联网管理平台，包含产品管理、设备管理、规则引擎、场景联动、设备影子、设备日志、设备标签、设备证书、协议管理等功能，以及完整的协议层（MQTT/HTTP/编解码/推送策略），全部按 BladeX 4.2.0 标准重构。共 13 个实体、13 个 VO、13 个 Mapper、13 个 Service、11 个 Controller、7 个规则引擎类、15 个协议层类。

### 代码位置
- **实体类/VO**: `iot-platform/iot-api/src/main/java/org/springblade/modules/iot/pojo/`
- **Mapper**: `iot-platform/iot-service/src/main/java/org/springblade/modules/iot/mapper/`
- **Service**: `iot-platform/iot-service/src/main/java/org/springblade/modules/iot/service/`
- **Controller**: `iot-platform/iot-service/src/main/java/org/springblade/modules/iot/controller/`
- **Wrapper**: `iot-platform/iot-service/src/main/java/org/springblade/modules/iot/wrapper/`
- **规则引擎**: `iot-platform/iot-rule/src/main/java/org/springblade/modules/iot/rule/`
- **协议核心抽象**: `iot-platform/iot-common/src/main/java/org/springblade/modules/iot/common/protocol/`
- **设备消息模型**: `iot-platform/iot-common/src/main/java/org/springblade/modules/iot/common/message/`
- **MQTT 协议**: `iot-platform/iot-service/src/main/java/org/springblade/modules/iot/protocol/mqtt/`
- **HTTP 协议**: `iot-platform/iot-service/src/main/java/org/springblade/modules/iot/protocol/http/`
- **设备通信服务**: `iot-platform/iot-service/src/main/java/org/springblade/modules/iot/device/`
- **推送策略**: `iot-platform/iot-rule/src/main/java/org/springblade/modules/iot/push/`
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

## NexIoT 源码迁移（cn-universal → BladeX）

### 迁移映射表
| 源模块 (nexiot-source) | 目标模块 | 说明 |
|---|---|---|
| `cn-universal-rule` | `iot-platform/iot-rule` | 规则引擎 + 推送策略 + 地理围栏 + Rulego + 场景联动 |
| `cn-universal-protocol/cn-universal-mqtt-protocol` | `iot-protocol/iot-protocol-mqtt` | MQTT 完整协议实现（处理器/Topic/OTA） |
| `cn-universal-protocol/cn-universal-http-protocol` | `iot-protocol/iot-protocol-http` | HTTP 完整协议实现（处理器/编解码） |
| `cn-universal-protocol/cn-universal-websocket-protocol` | `iot-protocol/iot-protocol-websocket` | WebSocket 协议（新建模块） |
| `cn-universal-persistence` | `iot-persistence` | 数据持久化层（实体/Mapper/DTO/MyBatis XML） |
| `cn-universal-data-bridge` | `iot-bridge` | 数据桥接（core + 6 个插件 + starter + web） |
| `cn-universal-web` | `iot-monitor` | Web 层（监控/配置/控制器/服务） |

### 包名映射
| 原包名 | 新包名 |
|---|---|
| `cn.universal.rule` | `org.springblade.modules.iot.rule` |
| `cn.universal.mqtt.protocol` | `org.springblade.modules.iot.protocol.mqtt` |
| `cn.universal.http.protocol` | `org.springblade.modules.iot.protocol.http` |
| `cn.universal.websocket.protocol` | `org.springblade.modules.iot.protocol.websocket` |
| `cn.universal.persistence` | `org.springblade.modules.iot.persistence` |
| `cn.universal.databridge` | `org.springblade.modules.iot.databridge` |
| `cn.universal.web` | `org.springblade.modules.iot.monitor.web` |
| `cn.universal.monitor` | `org.springblade.modules.iot.monitor.monitor` |
| `cn.universal.common` | `org.springblade.modules.iot.common` |
| `cn.universal.dm.*` | `org.springblade.modules.iot.dm.*` |
| `cn.universal.manager` | `org.springblade.modules.iot.manager` |

### 迁移统计
- **iot-rule**: 62 Java 文件（规则引擎/推送策略/地理围栏/Rulego/场景联动）
- **iot-protocol**: 164 Java 文件（common 8 + mqtt 64 + http 36 + codec 1 + websocket 55）
- **iot-persistence**: 203 Java 文件 + 46 资源文件（实体/Mapper/DTO/MyBatis XML）
- **iot-bridge**: 54 Java 文件（core 40 + 6 插件 13 + starter 1）
- **iot-monitor**: 68 Java 文件（监控/配置/控制器/服务）
- **总计**: 551 Java 文件

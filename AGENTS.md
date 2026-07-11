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
├── nvr-platform/             # NVR 平台业务模块
│   ├── nvr-common/           # NVR 公共模块 (实体基类, 工具类, SQL DDL)
│   ├── nvr-api/              # NVR API 接口模块
│   │   ├── pojo/entity/      # IoT管理实体类 (Product, Device, RuleModel等)
│   │   ├── pojo/vo/          # IoT管理 VO类
│   │   ├── nvr-api-dahua/    # 大华设备 API
│   │   ├── nvr-api-zlm/      # ZLM 流媒体 API
│   │   ├── nvr-api-haikang/  # 海康设备 API
│   │   ├── nvr-api-onvif/    # ONVIF 设备 API
│   │   ├── nvr-api-qs/       # QS 设备 API
│   │   ├── nvr-api-haikang-isup/  # 海康 ISUP API
│   │   ├── nvr-api-gb28181/  # GB28181 国标 API
│   │   └── nvr-api-jt1078/   # JT1078 部标 API
│   ├── nvr-biz/              # NVR 业务模块 (Controller + 规则引擎)
│   │   └── controller/       # IoT管理 Controller (Product, Device, Rule)
│   │   └── rule/             # 规则引擎核心
│   ├── nvr-protocol/         # NVR 协议模块
│   │   ├── nvr-protocol-common/
│   │   ├── nvr-protocol-haikang-isup/
│   │   ├── nvr-protocol-onvif/
│   │   ├── nvr-protocol-qs/
│   │   └── nvr-protocol-zlm/
│   └── nvr-service/          # NVR 服务模块 (Mapper/Service/Wrapper)
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
从 NexIoT 项目迁移的产品管理、设备管理、规则引擎功能，已按 BladeX 标准重构。

### 代码位置
- **实体类/VO**: `nvr-platform/nvr-api/src/main/java/org/springblade/modules/nvr/pojo/`
- **Mapper**: `nvr-platform/nvr-service/src/main/java/org/springblade/modules/nvr/mapper/`
- **Service**: `nvr-platform/nvr-service/src/main/java/org/springblade/modules/nvr/service/`
- **Controller**: `nvr-platform/nvr-biz/src/main/java/org/springblade/modules/nvr/controller/`
- **Wrapper**: `nvr-platform/nvr-service/src/main/java/org/springblade/modules/nvr/wrapper/`
- **规则引擎**: `nvr-platform/nvr-biz/src/main/java/org/springblade/modules/nvr/rule/`
- **DDL**: `nvr-platform/nvr-common/src/main/resources/sql/nexiot-migration.sql`

### 数据表
| 表名 | 说明 |
|---|---|
| `iot_product` | 产品表 |
| `iot_device` | 设备表 |
| `iot_product_function` | 产品功能定义（物模型） |
| `iot_device_group` | 设备分组 |
| `iot_device_group_union` | 设备-分组关联 |
| `iot_rule_model` | 规则模型 |

### API 接口
| 模块 | 路径前缀 | 说明 |
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

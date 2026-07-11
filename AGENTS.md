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
├── blade-api/                # API 模块
├── iot-platform/             # IoT 平台业务模块
│   ├── iot-common/           # IoT 公共模块
│   ├── iot-api/              # IoT API 接口模块
│   │   ├── iot-api-dahua/    # 大华设备 API
│   │   ├── iot-api-zlm/      # ZLM 流媒体 API
│   │   ├── iot-api-haikang/  # 海康设备 API
│   │   ├── iot-api-onvif/    # ONVIF 设备 API
│   │   ├── iot-api-qs/       # QS 设备 API
│   │   ├── iot-api-haikang-isup/  # 海康 ISUP API
│   │   ├── iot-api-gb28181/  # GB28181 国标 API
│   │   └── iot-api-jt1078/   # JT1078 部标 API
│   ├── iot-biz/              # IoT 业务模块
│   ├── iot-protocol/         # IoT 协议模块
│   └── iot-service/          # IoT 服务模块
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

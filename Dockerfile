# 第一阶段：使用 maven 镜像完成编译打包
FROM maven:3.8.7-eclipse-temurin-17 as builder
WORKDIR /build

# 复制 pom.xml 并下载依赖（利用缓存）
COPY pom.xml .
RUN mvn dependency:go-offline -B
#
# 再复制源码
COPY src ./src

# 打包生成 jar，跳过测试
RUN mvn package -DskipTests

# 第二阶段：使用更小的 openjdk 运行环境镜像
FROM eclipse-temurin:17-jdk-jammy
WORKDIR /app

# 拷贝上一步生成的 jar 到当前镜像
COPY --from=builder /build/target/*.jar /app/app.jar

# 暴露容器端口（Spring Boot 默认 8080）
EXPOSE 8080

# 容器启动时运行
ENTRYPOINT ["java","-jar","/app/app.jar"]
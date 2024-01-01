# 1. Build GraalVM
FROM eclipse-temurin:17-jdk AS deps

ENV GRAALVM_VERSION 22.3.3
ENV GRAALVM_PKG=graalvm-ce-java17-linux-amd64-${GRAALVM_VERSION}.tar.gz
ENV JAVA_HOME /opt/graalvm-ce-java17-${GRAALVM_VERSION}
ENV PATH $JAVA_HOME/bin:$PATH

RUN apt-get update && apt-get install -y gcc zlib1g-dev libz-dev

# Download and install GraalVM
ADD https://github.com/graalvm/graalvm-ce-builds/releases/download/vm-${GRAALVM_VERSION}/${GRAALVM_PKG} /tmp
RUN tar -xzvf /tmp/${GRAALVM_PKG} -C /opt && rm /tmp/${GRAALVM_PKG}
RUN gu install native-image


# 2. Build Spring Boot app from source code
FROM deps AS builder
WORKDIR /workspace/app
COPY . .
RUN ./gradlew build nativeCompile


# 3. Run app
FROM ubuntu:jammy AS runner

WORKDIR /app
COPY --from=builder /workspace/app/build/native/nativeCompile/${APPLICATION_NAME} /app/${APPLICATION_NAME}
EXPOSE 8080

CMD ["/app/${APPLICATION_NAME}"]

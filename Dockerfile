FROM java:8-jdk-alpine
LABEL maintainer=https://github.com/dezhiShen
RUN apk add --repository  https://mirrors.aliyun.com/alpine/v3.4/main/ --update curl && rm -rf /var/cache/apk/*
VOLUME /tmp
ENV TZ=Asia/Shanghai \
    LANG=zh_CN.UTF-8 \
    REDIS_HOST=redis \
    REDIS_PORT=6379
ADD target/listen-music-0.0.1-SNAPSHOT.jar /app.jar
RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone && touch /app.jar
ENTRYPOINT [ "sh", "-c", "exec java ${JAVA_OPTS} -Djava.security.egd=file:/dev/.urandom -jar /app.jar"]

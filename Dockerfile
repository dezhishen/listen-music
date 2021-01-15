FROM java:8-jdk-alpine
LABEL maintainer=https://github.com/dezhishen
ENV TZ=Asia/Shanghai \
    LANG=zh_CN.UTF-8 \
    MUSIC_DATA_PATH=/listen_music_data
ADD target/listen-music-0.0.1-SNAPSHOT.jar /app.jar
RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone && touch /app.jar
EXPOSE 8080
ENTRYPOINT [ "sh", "-c", "exec java ${JAVA_OPTS} -Djava.security.egd=file:/dev/.urandom -jar /app.jar"]


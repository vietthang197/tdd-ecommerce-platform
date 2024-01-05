FROM levietthang1997/oracle-jdk:21
VOLUME /app
COPY target/discovery-server-*.jar discovery-serve=prod
ENV FILE_ENCODING=utf-8
ENV JAVA_OPTS="-Xmx4096m"
EXPOSE 8888
ENTRYPOINT exec java  -jar discovery-server.jar
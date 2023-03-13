FROM  openjdk:11
VOLUME /tmp
EXPOSE 8070
COPY target/SenderService.jar app.jar
ENV JAVA_OPTS=""
ENTRYPOINT [ "sh", "-c", "java $JxAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /app.jar" ]

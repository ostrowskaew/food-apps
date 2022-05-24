FROM adoptopenjdk/openjdk16:ubi
VOLUME /tmp
ARG JAR_FILE
COPY ${JAR_FILE} food-pg.jar
ENTRYPOINT ["java","-jar","/food-pg.jar"]

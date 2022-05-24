FROM adoptopenjdk/openjdk16:ubi
VOLUME /tmp
ARG JAR_FILE
COPY ${JAR_FILE} food-backend.jar
ENTRYPOINT ["java","-jar","/food-backend.jar"]

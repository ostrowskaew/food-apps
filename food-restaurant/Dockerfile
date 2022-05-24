FROM adoptopenjdk/openjdk16:ubi
VOLUME /tmp
ARG JAR_FILE
COPY ${JAR_FILE} food-restaurant.jar
ENTRYPOINT ["java","-jar","/food-restaurant.jar"]


FROM openjdk:11 as builder

RUN chmod +x gradlew
RUN gradlew clean bootWar

FROM openjdk:11
COPY --from=builder build/libs/*.war app.war
ENTRYPOINT ["java","-jar","/app.war"]
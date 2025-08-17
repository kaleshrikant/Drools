FROM openjdk:21-slim

EXPOSE 8080

ADD build/libs/mortgage-rule-engine.jar mortgage-rule-engine.jar

ENTRYPOINT ["java", "-jar","mortgage-rule-engine.jar"]
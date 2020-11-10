FROM openjdk:11-jre-slim
WORKDIR /
COPY /build/libs/aws-workshop*.jar aws-workshop.jar
CMD ["java","-jar","aws-workshop.jar"]
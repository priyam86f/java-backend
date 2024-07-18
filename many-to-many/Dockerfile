FROM openjdk:17
EXPOSE 8080
# WORKDIR /app
COPY build/libs/kms-0.0.1-SNAPSHOT-plain.jar kms.jar
ADD build/libs/kms-0.0.1-SNAPSHOT-plain.jar kms.jar
ENTRYPOINT [ "java","-jar","/kms.jar" ]

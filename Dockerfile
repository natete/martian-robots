FROM maven:3.6.2-jdk-12

COPY src /project/src
COPY pom.xml /project
COPY *.txt /project

RUN mkdir /project/spock-reports

WORKDIR /project

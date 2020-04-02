FROM tomcat:8.0.51-jre8-alpine
EXPOSE 8080
ARG BUILD_VERSION
ENV BUILD_VERSION=${BUILD_VERSION}
RUN rm -rf /usr/local/tomcat/webapps/*
#COPY build/libs/arpkz-0.0.1.jar /usr/local/tomcat/webapps/arpkz-0.0.1.jar
COPY build/libs/stream-box-security-0.0.1.jar /usr/local/tomcat/webapps/arkpz.jar
ADD build/libs/stream-box-security-0.0.1.jar arkpz.jar
ENTRYPOINT ["java","-jar","arkpz.jar"]

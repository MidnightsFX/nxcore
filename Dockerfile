#############
# Dockerfile
#############

FROM java:8
#VOLUME /tmp

#LABEL 'nxcore'
# TODO, make this not need to be changed
COPY /build/libs/spring-boot-docker-nxcore-0.1.0.jar /usr/src/nxcore/app.jar
WORKDIR /usr/src/nxcore
CMD ["java", "Main"]
ENTRYPOINT ["java","-jar","app.jar"]
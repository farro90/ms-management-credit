FROM openjdk:11
VOLUME /tmp
EXPOSE 8108
ADD ./target/ms-management-credit-0.0.1-SNAPSHOT.jar ms-management-credit.jar
ENTRYPOINT ["java","-jar","ms-management-credit.jar"]
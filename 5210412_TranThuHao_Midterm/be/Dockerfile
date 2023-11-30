FROM openjdk:11

WORKDIR /opt/spring_ecommerce
COPY target/spring_ecommerce-0.0.1-SNAPSHOT.jar /opt/spring_ecommerce/spring_ecommerce-0.0.1-SNAPSHOT.jar
RUN chmod +x /opt/spring_ecommerce/spring_ecommerce-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar", "/opt/spring_ecommerce/spring_ecommerce-0.0.1-SNAPSHOT.jar"]
FROM eclipse-temurin:latest AS Builder
WORKDIR /IpService
COPY . .
RUN chmod +x ./gradlew
RUN ./gradlew bootJar

FROM eclipse-temurin:latest
ENV Forwarded=
COPY --from=Builder /IpService/build/libs/*.jar IpService.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "-Dconfig.Forwarded=${Forwarded}", "/IpService.jar"]

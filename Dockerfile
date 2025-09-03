FROM eclipse-temurin:17 AS build
ENV RELEASE=17
ENV JAVA_HOME=/opt/java/openjdk

WORKDIR /opt/build
COPY ./target/eco-map-*.jar ./app.jar

RUN java -Djarmode=layertools -jar app.jar extract
RUN $JAVA_HOME/bin/jlink \
         --add-modules `jdeps --ignore-missing-deps -q -recursive --multi-release ${RELEASE} --print-module-deps -cp 'dependencies/BOOT-INF/lib/*' app.jar` \
         --strip-debug \
         --no-man-pages \
         --no-header-files \
         --compress=2 \
         --output jdk
RUN ls -R
FROM debian:buster-slim

ARG BUILD_PATH=/opt/build
ENV JAVA_HOME=/opt/jdk
ENV PATH "${JAVA_HOME}/bin:${PATH}"

RUN groupadd --gid 1000 eco-map-app \
  && useradd --uid 1000 --gid eco-map-app --shell /bin/bash --create-home eco-map-app

USER eco-map-app:eco-map-app

WORKDIR /opt/workspace

COPY --from=build $BUILD_PATH/jdk $JAVA_HOME
COPY --from=build $BUILD_PATH/spring-boot-loader/ ./
COPY --from=build $BUILD_PATH/dependencies/ ./
COPY --from=build $BUILD_PATH/application/ ./


ENTRYPOINT ["java", "org.springframework.boot.loader.launch.JarLauncher"]
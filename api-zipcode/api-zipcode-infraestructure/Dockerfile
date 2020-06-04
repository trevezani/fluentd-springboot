FROM adoptopenjdk/openjdk11-openj9:alpine-slim as build
WORKDIR /workspace/app

RUN mkdir -p target

COPY target/*.jar target

RUN mkdir -p target/dependency && (cd target/dependency; jar -xf ../*.jar)


FROM adoptopenjdk/openjdk11-openj9:alpine-slim
VOLUME /tmp

ARG DEPENDENCY=/workspace/app/target/dependency
COPY --from=build ${DEPENDENCY}/BOOT-INF/lib /app/lib
COPY --from=build ${DEPENDENCY}/META-INF /app/META-INF
COPY --from=build ${DEPENDENCY}/BOOT-INF/classes /app

RUN apk add --no-cache curl

RUN addgroup bootapp && \
    adduser -D -S -h /var/cache/bootapp -s /sbin/nologin -G bootapp bootapp

ENV JAVA_OPTS="-XX:+UnlockExperimentalVMOptions -XX:+UseCGroupMemoryLimitForHeap -Xms16m -Xmx48m -XX:MaxMetaspaceSize=64m -XX:CompressedClassSpaceSize=8m -Xss256k -Xmn8m -XX:InitialCodeCacheSize=4m -XX:ReservedCodeCacheSize=8m -XX:MaxDirectMemorySize=16m"
ENV PARAMS=""

USER bootapp
EXPOSE 1400-2000

ENV PORT_EXTERNAL=1401

HEALTHCHECK --interval=2s --timeout=12s --retries=5 --start-period=30s CMD curl --fail http://localhost:$PORT_EXTERNAL/actuator/health || exit 1

ENTRYPOINT java $PARAMS $JAVA_OPTS -Xshareclasses -Xquickstart -cp app:app/lib/* br.com.trevezani.zipcode.infraestructure.Application
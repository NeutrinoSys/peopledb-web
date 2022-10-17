FROM amazoncorretto:19.0.0-al2
  COPY build/libs/peopledb-web-0.0.1-SNAPSHOT.jar /peopledb-web.jar
  ENV STORAGE_FOLDER=/files
  EXPOSE 8080
  CMD ./peopledb-web.jar

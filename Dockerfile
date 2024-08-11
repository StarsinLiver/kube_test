# 베이스 이미지를 지정합니다. 이 예에서는 OpenJDK 11 이미지를 사용합니다.
FROM openjdk:17

# 애플리케이션을 실행할 디렉터리를 생성하고 설정합니다.
WORKDIR /app

# JAR 파일을 컨테이너의 /app 디렉터리에 복사합니다.
COPY k82-0.0.1-SNAPSHOT.jar /app/app.jar

# JAR 파일을 실행하는 명령어를 지정합니다.
ENTRYPOINT ["java", "-jar", "app.jar"]
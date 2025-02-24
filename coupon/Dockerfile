# 베이스 이미지 설정
FROM openjdk:17-jdk

# ARG 설정
ARG JAR_PATH=./build/libs/

# JAR 파일을 컨테이너의 지정된 경로에 복사
COPY ${JAR_PATH}coupon-0.0.1-SNAPSHOT.jar /app/coupon-0.0.1-SNAPSHOT.jar

# .env 파일을 컨테이너의 지정된 경로에 복사
COPY .env /app/.env

# WORKDIR 설정
WORKDIR /app

# Docker run 시 실행할 명령어 설정
ENTRYPOINT ["java", "-jar", "-Dspring.profiles.active=dev", "-Duser.timezone=Asia/Seoul", "/app/coupon-0.0.1-SNAPSHOT.jar"]

# ENV 파일을 환경 변수로 설정
ENV JAVA_OPTS="-Dspring.config.location=file:/app/.env"
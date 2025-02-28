# 1. 베이스 이미지 설정
FROM openjdk:17-jdk

# 2. 작업 디렉토리 설정
WORKDIR /app

# 3. JAR 파일 복사 (변수 없이 명확한 경로 사용)
COPY build/libs/member-0.0.1-SNAPSHOT.jar /app/member-0.0.1-SNAPSHOT.jar

# 4. 환경 변수 파일 복사 (.env가 있을 경우)
# COPY .env /app/.env

# 5. 실행 포트 노출
EXPOSE 8080

# 6. 환경 변수 설정
ENV JAVA_OPTS="-Dspring.config.location=file:/app/.env"

# 7. 실행 명령어 설정 (JAVA_OPTS 적용)
ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar /app/member-0.0.1-SNAPSHOT.jar"]

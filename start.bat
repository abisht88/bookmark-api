@echo off
setlocal

set dc_infra=docker-compose-db.yml
set dc_app=docker-compose-app.yml

call mvnw clean package -DskipTests
echo Starting all docker containers....
docker-compose -f %dc_infra% -f %dc_app% up --build -d
docker-compose -f %dc_infra% -f %dc_app% logs -f
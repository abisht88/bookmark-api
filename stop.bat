@echo off
setlocal

set dc_infra=docker-compose-db.yml
set dc_app=docker-compose-app.yml

echo Stopping all docker containers....
docker-compose -f %dc_infra% -f %dc_app% stop
docker-compose -f %dc_infra% -f %dc_app% rm -f

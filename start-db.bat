@echo off
setlocal

set dc_infra=docker-compose-db.yml

echo Starting Postgres docker container....
docker-compose -f %dc_infra%  up --build -d
docker-compose -f %dc_infra%  logs -f
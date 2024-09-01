#!/bin/bash

docker run --name shareit-postgres \
           -e POSTGRES_DB=shareit \
           -e POSTGRES_USER=test \
           -e POSTGRES_PASSWORD=test \
           -p 5432:5432 \
           -d postgres:latest
#!/usr/bin/env bash
docker build -t gameoflife .
docker run -d -p 6102:80 -v "app:/usr/src/app/app" --name gameoflife gameoflife
#!/bin/sh

set -e
export APP_NAME=app
export COMPOSE_FILE=docker-compose.yml

if  [ "$1" == "stop" ] || [ "$1" == "kill" ]
then
  echo "Stopping and removing all apps from compose file"
  docker-compose -f docker-compose.yml down
  exit 0
fi

./gradlew  clean build

docker-compose -f $COMPOSE_FILE build $APP_NAME

if [ "$1" == "restart" ]
then
  echo "Stopping $APP_NAME"
  docker-compose -f $COMPOSE_FILE kill $APP_NAME
  echo "Removing $APP_NAME"
  docker-compose -f $COMPOSE_FILE rm -f $APP_NAME
  echo "Launching $APP_NAME in detached mode"
  docker-compose -f $COMPOSE_FILE up -d $APP_NAME
else
  echo "Stopping and removing all apps from compose file"
  docker-compose -f $COMPOSE_FILE down
fi

if [ "$1" == "all" ]
then
  echo "Launching containers attached to all"
  docker-compose -f $COMPOSE_FILE up
elif [ "$1" == "app" ]
then
  echo "Launching containers detached"
  docker-compose -f $COMPOSE_FILE up -d
elif [ "$1" == "detach" ] || [ "$1" == "none" ]
then
  echo "Launching containers detached"
  docker-compose -f $COMPOSE_FILE up -d
elif [ "$1" != "restart" ]
then
  echo "Launching containers detached"
  docker-compose -f $COMPOSE_FILE up -d
fi

exit 0


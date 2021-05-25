#!/bin/bash
set -e

if [ "$1" = 'postgres' ]; then
    chown -R postgres "$PGDATA"

    if [ -z "$(ls -A "$PGDATA")" ]; then
        gosu postgres initdb
    fi

    exec gosu postgres "$@"
fi

exec "$@"
##!/bin/bash
#while ! exec 6<>/dev/tcp/"${DATABASE_HOST}"/"${DATABASE_PORT}"; do
#
#    echo "Trying to connect to MySql at ${DATABASE_HOST}:${DATABASE_PORT}..."
#    sleep 10
#
#done
#echo "<<< connect to MySql database >>>"
#java -Djava.security.egd=file:/dev/./urandom -Dspring.profiles.active=container -jar /opt/docker-jenkins-integration.jar

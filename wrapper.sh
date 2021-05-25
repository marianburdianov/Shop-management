#!/bin/bash
while ! exec 6<>/dev/tcp/"${DATABASE_HOST}"/"${DATABASE_PORT}"; do
    echo "Trying to connect to MySql at ${DATABASE_HOST}:${DATABASE_PORT}..."
    sleep 10

echo "<<< connect to MySql database >>>"
java -Djava.security.egd=file:/dev/./urandom -Dspring.profiles.active=container -jar /opt/docker-jenkins-integration.jar
done

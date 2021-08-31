#!/bin/bash

echo 'Looking for DB and region in application.yml'
dbid=`grep -F "database-id" ./stargate-demo/src/main/resources/application.yml`
dbid="${dbid/database-id:/}"
dbid=$(echo "$dbid" | sed 's/^[ ]*//')

region=`grep -F "cloud-region" ./stargate-demo/src/main/resources/application.yml`
region="${region/cloud-region:/}"
region=$(echo "$region" | sed 's/^[ ]*//')

swagger="https://${dbid}-${region}.apps.astra.datastax.com/api/rest/swagger-ui/"
echo "${swagger}"

#gp preview "https://${dbid}-${region}.apps.astra.datastax.com/api/rest/swagger-ui/"


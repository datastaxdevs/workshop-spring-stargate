#!/bin/bash

clear
echo 'Parsing application.yml to retrieve Db Id and Db Region..'
echo " "
dbid=`grep -F "database-id" ./stargate-demo/src/main/resources/application.yml`
dbid="${dbid/database-id:/}"
dbid=$(echo "$dbid" | sed 's/^[ ]*//')
echo "DB-ID:${dbid} "

region=`grep -F "cloud-region" ./stargate-demo/src/main/resources/application.yml`
region="${region/cloud-region:/}"
region=$(echo "$region" | sed 's/^[ ]*//')
echo "DB-REGION:${region} "

token=`grep -F "application-token" ./stargate-demo/src/main/resources/application.yml`
token="${token/application-token:/}"
token=$(echo "$token" | sed 's/^[ ]*//')
echo " "
echo "Token;" 
echo "${token}" 
echo " "

swagger="https://${dbid}-${region}.apps.astra.datastax.com/api/rest/swagger-ui/"
echo "Opening ${swagger}"

gp preview "https://${dbid}-${region}.apps.astra.datastax.com/api/rest/swagger-ui/"

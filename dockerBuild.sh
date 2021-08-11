#!/bin/bash

version=v2
array=($@)

length=${#array[*]}
index=0
while(( $index<$length )); do
    moduleName="${array[$index]}"
    mvn clean install -DskipTests -U
    echo "docker build -t velkoz/${moduleName}-provider:$version ${moduleName}-provider/"
    docker build -t velkoz/${moduleName}-provider:$version ${moduleName}-provider/

    let "index++"
done

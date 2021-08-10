#!/bin/bash

array=($@)

length=${#array[*]}
index=0
while(( $index<$length )); do
    moduleName="${array[$index]}"

    echo "docker build -t velkoz/${moduleName}-provider:v1 ${moduleName}-provider/"
    docker build -t velkoz/${moduleName}-provider:v1 ${moduleName}-provider/

    let "index++"
done

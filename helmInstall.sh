#!/bin/bash


array=($@)

length=${#array[*]}
index=0
while(( $index<$length )); do
    moduleName="${array[$index]}"
    echo "--------- install ${moduleName}-provider ---------"
    echo "helm upgrade --install ${moduleName}-provider ./${moduleName}-provider/k8s-chart/${moduleName}-provider/"
    helm upgrade --install ${moduleName}-provider ./${moduleName}-provider/k8s-chart/${moduleName}-provider/

    let "index++"
done

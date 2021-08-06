#!/bin/bash
#appVersion的值可以通过deployment中container的env参数传入到springboot的应用程序中，替换掉配置文件中的appVersion
helm upgrade --install k8s-demo ../k8s-demo -n default --set replicaCount=3,appVersion=v2

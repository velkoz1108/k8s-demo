#!/bin/bash

helm upgrade --install k8s-demo ../k8s-demo -n default --set replicaCount=3

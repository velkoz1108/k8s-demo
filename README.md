## 如何在k8s中实现服务之间的调用
1. feign如何配置
2. mysql如何访问

### user-provider服务通过feign client调用book-provider服务
feign client的地址在本地的配置：`book-provider.url=http://localhost:8082`
在k8s中改成`http://book-provider`即可，`book-provider`对应的就是book-provider在k8s中的svc的名字



### jpa-provider如何访问k8s中mysql
1. 安装mysql
```shell
# helm install mysql bitnami/mysql

```

2. 获取mysql的用户名密码
用户名默认为`root`
```shell
# echo Password : $(kubectl get secret --namespace default mysql -o jsonpath="{.data.mysql-root-password}" | base64 --decode)

Password : mmmGxaBrfI

```

3. 配置jpa-provider的mysql地址
```shell
# spring.datasource.url=jdbc:mysql://mysql/test?createDatabaseIfNotExist=true
```
`mysql`就是k8s中mysql的svc名字

或者
```shell
# spring.datasource.url=jdbc:mysql://mysql-headless/test?createDatabaseIfNotExist=true
```
或者
```shell
# spring.datasource.url=jdbc:mysql://mysql.default.svc.cluster.local/test?createDatabaseIfNotExist=true
```

```
$ kubectl get svc
NAME             TYPE        CLUSTER-IP      EXTERNAL-IP   PORT(S)        AGE
mysql            ClusterIP   10.96.196.252   <none>        3306/TCP       19h
mysql-headless   ClusterIP   None            <none>        3306/TCP       19h
```

`mysql-headless`的svc的会有一个内网域名，如：`mysql.default.svc.cluster.local`
进入集群中一个含有mysql客户端的容器内部，使用命令行方式访问mysql.
也可以创建一个临时的容器:
```shell
# kubectl run mysql-client --rm --tty -i --restart='Never' --image  docker.io/bitnami/mysql:8.0.26-debian-10-r10 --namespace default --command -- bash
```
登录mysql
```shell
# mysql -h mysql.default.svc.cluster.local -uroot -p test
```

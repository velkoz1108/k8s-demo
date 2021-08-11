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

4. 通过secret来配置mysql的密码
    查看mysql的`secret`:
```shell
$ kubectl get secret mysql
NAME    TYPE     DATA   AGE
mysql   Opaque   2      40h
```
获取密码的key
```shell
$ kubectl describe secret mysql
Name:         mysql
Namespace:    default
Labels:       app.kubernetes.io/instance=mysql
              app.kubernetes.io/managed-by=Helm
              app.kubernetes.io/name=mysql
              helm.sh/chart=mysql-8.8.2
Annotations:  meta.helm.sh/release-name: mysql
              meta.helm.sh/release-namespace: default

Type:  Opaque

Data
====
mysql-root-password:  10 bytes
mysql-password:       10 bytes

```
`mysql-root-password`的值就是mysql的root用户的密码

修改原来的账号密码配置：
```yaml
appEnv:
#  - name: spring.datasource.password
#    value: mmmGxaBrfI
  - name: spring.datasource.password
    valueFrom:
      secretKeyRef:
        name: mysql
        key: mysql-root-password
```
查看pod的env参数:
```shell
...
    Restart Count:  0
    Liveness:       http-get http://:http/ delay=0s timeout=1s period=10s #success=1 #failure=3
    Readiness:      http-get http://:http/ delay=0s timeout=1s period=10s #success=1 #failure=3
    Startup:        http-get http://:http/ delay=0s timeout=2s period=30s #success=1 #failure=50
    Environment:
      spring.datasource.password:  <set to the key 'mysql-root-password' in secret 'mysql'>  Optional: false
      spring.datasource.url:       jdbc:mysql://mysql.default.svc.cluster.local/test?createDatabaseIfNotExist=true
    Mounts:
      /var/run/secrets/kubernetes.io/serviceaccount from kube-api-access-ptbkh (ro)

...
```
密码显示为：`<set to the key 'mysql-root-password' in secret 'mysql'>  Optional: false`
但是可以进入容器内通过`env`命令查看原始密码：
```shell
# env | grep spring
spring.datasource.password=mmmGxaBrfI
spring.datasource.url=jdbc:mysql://mysql.default.svc.cluster.local/test?createDatabaseIfNotExist=true
```


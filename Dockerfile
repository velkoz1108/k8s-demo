FROM nimmis/java-centos:oracle-8-jre
MAINTAINER twang@telenav.cn

ADD ./target/k8s-demo-*.jar /opt/k8s-demo.jar

EXPOSE 8080

ENTRYPOINT ["java","-jar","/opt/k8s-demo.jar"]
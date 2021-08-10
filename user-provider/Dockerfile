FROM nimmis/java-centos:oracle-8-jre
MAINTAINER twang@telenav.cn

ADD ./target/user-provider-*.jar /opt/user-provider.jar

EXPOSE 8081

ENTRYPOINT ["java","-jar","/opt/user-provider.jar"]
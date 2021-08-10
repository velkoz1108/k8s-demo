FROM nimmis/java-centos:oracle-8-jre
MAINTAINER twang@telenav.cn

ADD ./target/jpa-provider-*.jar /opt/jpa-provider.jar

EXPOSE 8083

ENTRYPOINT ["java","-jar","/opt/jpa-provider.jar"]
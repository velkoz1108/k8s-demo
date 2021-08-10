FROM nimmis/java-centos:oracle-8-jre
MAINTAINER twang@telenav.cn

ADD ./target/book-provider-*.jar /opt/book-provider.jar

EXPOSE 8082

ENTRYPOINT ["java","-jar","/opt/book-provider.jar"]
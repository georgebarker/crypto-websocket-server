FROM tomcat:8.0
ADD target/crypto-websocket-server-1.0-SNAPSHOT.war /usr/local/tomcat/webapps/
EXPOSE 80
CMD ["catalina.sh", "run"]
FROM tomcat:10.0.8
ADD target/crypto-websocket-server.war /usr/local/tomcat/webapps/
CMD ["catalina.sh", "run"]
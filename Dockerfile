FROM tomcat:10.1.1
ADD target/crypto-websocket-server.war /usr/local/tomcat/webapps/
CMD ["catalina.sh", "run"]
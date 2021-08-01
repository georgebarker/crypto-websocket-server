FROM tomcat:10.0.8
ADD target/crypto-websocket-server.war /usr/local/tomcat/webapps/
EXPOSE 80
EXPOSE 8080
EXPOSE 8081
CMD ["catalina.sh", "run"]
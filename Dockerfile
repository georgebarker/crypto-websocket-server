FROM tomcat:10.0.8
ADD target/crypto-websocket-server-1.war /usr/local/tomcat/webapps/
EXPOSE 80
CMD ["catalina.sh", "run"]
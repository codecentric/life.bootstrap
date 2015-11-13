FROM jetty:9-jre8

ADD life.war /var/lib/jetty/webapps/life.war

RUN chown 1000:1000 /var/lib/jetty/webapps/life.war
RUN chmod 777 /var/lib/jetty/webapps/life.war


EXPOSE 9080

ENV JAVA_OPTIONS -agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=9080 -Duser.timezone=GMT+2 -Dcom.sun.xml.internal.ws.transport.http.client.HttpTransportPipe.dump=true

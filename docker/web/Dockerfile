FROM jetty:9.4-jre8
MAINTAINER Johan

USER root
RUN apt-get clean && apt-get update
RUN apt-get -y install locales
RUN chmod -R +r /var

USER jetty
COPY . /var/lib/jetty/

USER root
RUN chown -R jetty /var/lib/jetty
RUN chmod -R 775 /var

USER jetty
CMD java -Djava.aw.headless=true $JAVA_OPTS -jar /usr/local/jetty/start.jar

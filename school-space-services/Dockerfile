FROM ubuntu:22.04

# Installer OpenJDK 21 + mysql-client + utilitaires
RUN apt-get update && \
    apt-get install -y wget gnupg2 curl mysql-client && \
    wget https://download.oracle.com/java/21/latest/jdk-21_linux-x64_bin.deb && \
    dpkg -i jdk-21_linux-x64_bin.deb && \
    rm jdk-21_linux-x64_bin.deb && \
    rm -rf /var/lib/apt/lists/*

# Définir JAVA_HOME
ENV JAVA_HOME=/usr/lib/jvm/jdk-21
ENV PATH=$JAVA_HOME/bin:$PATH

WORKDIR /app

COPY target/school-space-services-0.0.1-SNAPSHOT.jar app.jar
COPY entrypoint.sh entrypoint.sh
RUN chmod +x entrypoint.sh

EXPOSE 8080

ENTRYPOINT ["./entrypoint.sh"]

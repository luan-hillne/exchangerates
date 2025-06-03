FROM jenkins/jenkins:lts-jdk21
USER root

RUN apt-get update && apt-get install -y \
    wget \
    unzip \
    && wget https://services.gradle.org/distributions/gradle-7.5.1-bin.zip -P /tmp \
    && unzip /tmp/gradle-7.5.1-bin.zip -d /opt \
    && ln -s /opt/gradle-7.5.1/bin/gradle /usr/bin/gradle

RUN apt-get update && \
    apt-get install -y \
    ca-certificates \
    curl \
    sudo && \
    curl -fsSL https://get.docker.com -o install-docker.sh && \
    sudo sh install-docker.sh && \
    sudo usermod -a -G docker jenkins

EXPOSE 8080
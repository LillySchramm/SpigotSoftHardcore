FROM ubuntu:22.04

WORKDIR /app

RUN apt update
RUN apt install openjdk-17-jre -y
RUN apt install wget -y
RUN wget -O server.jar https://api.papermc.io/v2/projects/paper/versions/1.19.2/builds/215/downloads/paper-1.19.2-215.jar

RUN echo 'eula=true' > eula.txt
RUN echo '[{"uuid": "6b128967-9f61-486c-b4b5-a429c2da66f0","name": "eps_dev","level": 4,"bypassesPlayerLimit": false}]' > /app/ops.json

COPY --chmod=0777 ./paper.yml /app/paper.yml
COPY --chmod=0777 ./spigot.yml /app/spigot.yml
COPY --chmod=0777 ./server.sh /app/server.sh
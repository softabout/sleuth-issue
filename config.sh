#!/usr/bin/env bash


setenforce 0

sudo yum install -y yum-utils device-mapper-persistent-data lvm2

sudo yum-config-manager --add-repo https://download.docker.com/linux/centos/docker-ce.repo

sudo yum-config-manager --enable docker-ce-edge

sudo yum makecache fast

sudo yum install -y docker-ce
sudo systemctl enable docker
sudo systemctl start docker

yum install -y vim curl

sudo docker run -d --name sleuth-issue-mongo -p 27017:27017 -e MONGODB_USERNAME=sleuth -e MONGODB_PASSWORD=password -e MONGODB_DBNAME=SLEUTHISSUE frodenas/mongodb

#!/bin/bash
docker run -d --restart always --name nginx1 -p 8080:80 -v /home/andrew/MyJava/geocalculator/web-client:/usr/share/nginx/html nginx

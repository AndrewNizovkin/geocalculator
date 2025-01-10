#!/bin/bash
docker run -d --restart always --name nginx1 -p 80:80 -v /home/andrew/MyProjects:/usr/share/nginx/html nginx

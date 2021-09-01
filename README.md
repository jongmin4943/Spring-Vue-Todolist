# Spring-Vue-Todolist

## Front-End - Vue.js
vue cli 이용
```
npm install -g @vue/cli
vue create todo-frontend
```
default 옵션 선택, yarn 선택 <br>
element-ui <br>
vuex <br>
vue-router <br>
axios <br>
라이브러리 이용

## Back-End - Spring Boot
Spring Web <br>
Spring JDBC <br>
MyBatis <br>
Mariadb <br>
Lombok <br>
Spring Security <br>
Jwt <br>

## DataBase - MaridDB
Docker 이용
```
docker pull mariadb
docker run --name mariadb -d -p 3306:3306 -e MYSQL_ROOT_PASSWORD=root mariadb
// 데이터를 C드라이브 밑에 저장하고 싶은경우
docker run --name mariadb -d -p 3306:3306 -e MYSQL_ROOT_PASSWORD=root -v C:\DOCKER\MARIADB:/var/lib/mysql mariadb

docker exec -it mariadb /bin/bash
apt-get update && apt-get install -y vim
// /etc/mysql/my.cnf에 utf8 추가
[client]
default-character-set = utf8mb4

[mysql]
default-character-set = utf8mb4

[mysqld]
character-set-client-handshake = FALSE
character-set-server = utf8mb4
collation-server = utf8mb4_unicode_ci

//나간 뒤 재실행
exit
docker restart mariadb

//적용 됐나 확인
docker exec -it mariadb /bin/bash
mysql -uroot -proot
status
```

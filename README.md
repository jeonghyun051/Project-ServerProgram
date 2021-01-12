# JSP블로그 프로젝트
## 환경
- JDK 1.8
- TOMCAT 9.0
- STS TOOL
- MYSQL 8.0
- POSTMAN
- LOMBOK
- 인코딩(utf-8)
- git


## MYSQL 데이터베이스 생성 및 사용자 생성
```SQL
create user 'bloguser'@'%' identified by 'bitc5600';
grant all privileges on *.* to 'bloguser'@'%';
create database blog;
use blog;
```

## MYSQL 테이블 생성
- bloguser 사용자 생성
- use blog;  데이터 베이스 선택

```SQL
CREATE TABLE user2(
	id int primary key auto_increment,
    username varchar(100) not null unique,
    password varchar(100) not null,
	nameval varchar(100),
	age int(20),
    address varchar(100),
    residentnumber int(20),
    userRole varchar(20),
    createDate timestamp
) engine=InnoDB default charset=utf8;
commit;
```


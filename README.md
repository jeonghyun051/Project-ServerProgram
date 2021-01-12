# 🥇JSP블로그 프로젝트
## 환경
- JDK 1.8
- TOMCAT 9.0
- STS TOOL
- MYSQL 8.0
- POSTMAN
- LOMBOK
- 인코딩(utf-8)
- git
<br/>

## 🥈MYSQL 데이터베이스 생성 및 사용자 생성
- bloguser 사용자 생성
- use blog 데이터 베이스 선택
```SQL
create user 'bloguser'@'%' identified by 'bitc5600';
grant all privileges on *.* to 'bloguser'@'%';
create database blog;
use blog;
```
<br/>

## 🥈MYSQL 테이블 생성

- user2 테이블 생성

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
<br/>

## 🥈기능 구현(화면)
+ 첫화면에는 데이터베이스에 있는 모든 유저리스트가 나온다.
![1  첫화면 유저리스트](https://user-images.githubusercontent.com/74044292/104284187-fe4acb00-54f4-11eb-9edd-c8e0041c3000.png)
<br/>

+ user권한 회원가입 후 로그인 시 자신의 정보만 삭제할 수 있다.
![6  유저 로그인 자기것만 삭제가능](https://user-images.githubusercontent.com/74044292/104284200-00ad2500-54f5-11eb-8f0f-a58c7accccc4.png)
<br/>

+ admin권한 회원가입 후 로그인 시 모두의 게시물을 삭제할 수 있다.
![8  관리자 로그인 후 유저리스트 전부다 삭제가능](https://user-images.githubusercontent.com/74044292/104284204-0145bb80-54f5-11eb-8629-cfabe8179521.png)
<br/>
<br/>

## 🥈기능 구현(로그인)
+ 회원가입 시 아이디를 중복체크 해야한다.
![2  아이디 중복체크 후 회원가입](https://user-images.githubusercontent.com/74044292/104284192-fee36180-54f4-11eb-80b3-a2eaa9dc3bc2.png)
<br/>

+ 회원가입 시 권한으로 user, admin 나눠서 가입할 수 있다.<br/>
![4  user, admin 고르기](https://user-images.githubusercontent.com/74044292/104284195-ff7bf800-54f4-11eb-874d-fd614808f7bf.png)
<br/>

+ 회원가입 시 주소 API를 이용해서 주소를 입력할 수 있다.
![3  회원가입 주소찾기](https://user-images.githubusercontent.com/74044292/104284193-ff7bf800-54f4-11eb-809e-084a270ea1e0.png)
<br/>
<br/>

## 🥈기능 구현(데이터베이스)
+ 회원가입, 삭제시 모두 데이터베이스와 연동되어있다.
![5-1 회원가입 후 데이터베이스](https://user-images.githubusercontent.com/74044292/104284199-00148e80-54f5-11eb-9ceb-e68d2ecf50d2.png)
![10  관리자가 삭제 후 데이터 베이스](https://user-images.githubusercontent.com/74044292/104284209-0276e880-54f5-11eb-8d7e-46403b1be2c9.png)

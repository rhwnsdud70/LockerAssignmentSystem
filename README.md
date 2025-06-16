# 🔐 LockerAssignmentSystem

사물함 배정 시스템은 컴퓨터학부 학생들의 사물함 신청 및 배정을 효율적으로 관리하기 위한 웹 애플리케이션입니다.  
관리자는 사용자를 등록하고, 각 사용자의 사물함 배정을 도와주며, 사용자는 본인의 사물함 배정 상태를 확인할 수 있습니다.  
Spring Boot, JPA, MySQL, Thymeleaf를 기반으로 구성되어 있습니다.

---

## 🛠 개발 환경
- **Language** : Java 21
- **Framework** : Spring Boot 3.x
- **Template Engine** : Thymeleaf
- **Build Tool** : Gradle
- **IDE** : IntelliJ IDEA

---

## 💻 실행 환경
- 운영체제: Windows 10 이상
- Java: JDK 17 이상
- Gradle: 7.x 이상 (Wrapper 포함되어 있음)
- MySQL: 8.x (3306 포트 기준)

---

## 📁 프로젝트 디렉토리 구조 (src 하위)

- src/
  - main/
    - java/
      - yu22112077/
        - LockerAssignmentSystem/
          - LockerAssignmentSystemApplication.java
          - config/
            - LockerDataLoader.java
            - SecurityConfig.java
          - controller/
            - AdminController.java
            - AuthController.java
            - ExcelToAccountController.java
            - HTMLController.java
            - LockerController.java
            - UserController.java
          - model/
            - dto/
              - AdminUpdateUserDTO.java
              - LockerDTO.java
              - UpdateMajorDTO.java
              - UpdatePasswordDTO.java
              - UserDTO.java
              - UserResponseDTO.java
            - entity/
              - Locker.java
              - User.java
          - repository/
            - LockerRepository.java
            - UserRepository.java
          - service/
            - AuthService.java
    - resources/
      - application.properties
      - static/
        - images/
          - 115.png
          - 116.png
          - 117.png
          - 123.png
          - 124.png
          - 220.png
          - 221.png
          - 322B.png
          - 322F.png
          - 323.png
          - LAS_logo.png
          - locker.png
        - js/
          - header.js
      - templates/
        - allUser.html
        - edit-user.html
        - index.html
        - locker-view.html
        - locker.html
        - login.html
        - main.html
        - profile.html
        - register.html
        - upload-excel.html
  - test/
    - java/
      - yu22112077/
        - LockerAssignmentSystem/

---

## 📦 주요 기능

### 👤 일반 사용자 기능
- 회원가입 및 로그인
- 마이프로필 확인
- 전공 및 비밀번호 변경
- 사물함 확인

### 👑 관리자 기능
- 사물함 배정 및 해제
- 사용자 정보 수정
- 전체 사용자 목록 관리
- 엑셀로 사용자 일괄 등록

---

## 🗄 데이터베이스 설정

1. MySQL 서버를 시작합니다.

2. 데이터베이스와 유저를 생성합니다:
```sql
CREATE DATABASE lockerdb DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
CREATE USER 'username'@'localhost' IDENTIFIED BY 'password';
GRANT ALL PRIVILEGES ON lockerdb.* TO 'username'@'localhost';
FLUSH PRIVILEGES;
```

3. `src/main/resources/application.properties` 파일에서 다음 두 줄을 본인이 설정한 계정 정보에 맞게 수정합니다:

```properties
spring.datasource.username=username
spring.datasource.password=password
```

---

## ▶️ 프로젝트 실행 방법

1. 소스파일에서 `LockerAssignmentSystemApplication.java` 실행
2. 웹 브라우저에서 다음 주소로 접속  
   [http://localhost:8080/](http://localhost:8080/)

---

## ⚠️ 주의 사항
- 엑셀로 사용자 등록 시 학번(ID)이 중복될 경우 해당 사용자는 무시되고 로그 파일로 기록됩니다.
- 관리자 코드: `"LAS"` 입력 시 관리자 계정으로 등록됩니다.

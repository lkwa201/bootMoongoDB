#### 참고 사이트
```
* https://khdscor.tistory.com/115
* https://ksb-dev.tistory.com/354
* https://kotlinworld.com/556
* https://crazy-horse.tistory.com/entry/%EC%8A%A4%ED%94%84%EB%A7%81%EB%B6%80%ED%8A%B8-MongoDB%EB%A1%9C-CRUD-%EC%8B%A4%EC%8A%B5%ED%95%B4%EB%B3%B4%EA%B8%B0
* https://secretartbook.tistory.com/4
```

## https://ksb-dev.tistory.com/354
사용자 및 권한 등록하는 몽고셸 스크립트를 익히고 사용자를 등록
```
use admin //admin 테이블을 사용하겠다는 뜻.
db.createUser(
  {
    user: "root",
    pwd:  "1234",
    roles: [
    // 슈퍼 유저 권한
    	{ "role" : "readWriteAnyDatabase", "db" : "admin" },
    	{ "role" : "userAdminAnyDatabase", "db" : "admin" },
        { "role" : "dbAdminAnyDatabase", "db" : "admin" },
        { "role" : "clusterAdmin", "db" : "admin" },
        { "role" : "restore", "db" : "admin" },
        { "role" : "backup", "db" : "admin" }
	]
  }
)
```
그리고 다음 셸도 익혔다.
db.getUsers() : 사용자 검색

db.dropUser("삭제할아이디") : 사용자 삭제

다음, 내가 사용한 엔티티는 다음과 같다.
```java
@Getter
@Document("Users")
public class User {

    @Id
    private String id;
    private String userid;
    private String username;
    private String email;

    public User() {}

    public User(String userid, String username, String email) {
        this.userid = userid;
        this.username = username;
        this.email = email;
    }

  //엔티티 코드 외 생략
}
```

### 이후 내가 테스트한 몽고셸 코드

```
db.Users.find({"username":"홍길동"}) //단일 레코드 검색 셸 코드
db.Users.countDocuments(); //모든 레코드 수 반환 셸 코드
```
위 엔티티 코드대로 생성되면 내가 의도치 않게 몽고DB에서 다음과 같이 두 개의 컬럼을 추가 한다.

<img width="529" height="97" alt="스크린샷 2025-08-16 오전 11 06 37" src="https://github.com/user-attachments/assets/a8b348c8-8064-4123-9eb0-1591d4d34ca8" />

_id인 경우 고유의 컬럼과 고유키값을 갖으니 상관은 없지만, _class는 문제가 발생 할 수 있다고 한다. 해서 찾아본 결과로
```java
@Configuration
public class MongodbConfig {

    @Bean
    public MappingMongoConverter mappingMongoConverter(
            MongoDatabaseFactory mongoDatabaseFactory,
            MongoMappingContext mongoMappingContext
    ) {
        DbRefResolver dbRefResolver = new DefaultDbRefResolver(mongoDatabaseFactory);
        MappingMongoConverter converter = new MappingMongoConverter(dbRefResolver, mongoMappingContext);
        converter.setTypeMapper(new DefaultMongoTypeMapper(null));  
        return converter;
    }
}
```
위의 컨피그 파일을 작성해 주면 _class 컬럼 생성이 되지 않는다고 한다. 필자는 아직 테스트 안해 봄.

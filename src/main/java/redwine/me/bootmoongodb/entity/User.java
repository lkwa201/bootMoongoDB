package redwine.me.bootmoongodb.entity;

import jakarta.persistence.Id;
import lombok.Getter;
import org.springframework.data.mongodb.core.mapping.Document;
import redwine.me.bootmoongodb.domain.RequestUsers;
import redwine.me.bootmoongodb.domain.ResponseUsers;

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

    public void modifyUser(final RequestUsers users) {
        this.username = users.getUsername();
        this.email = users.getEmail();
    }

    public ResponseUsers toUsers() {
        return ResponseUsers.builder()
                .uid(userid)
                .username(username)
                .email(email)
                .build();

    }
}

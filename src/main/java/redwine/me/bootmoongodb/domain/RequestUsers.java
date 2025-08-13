package redwine.me.bootmoongodb.domain;

import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;
import redwine.me.bootmoongodb.entity.User;

@Getter
@Setter
public class RequestUsers {
    private String uid;
    private String username;
    private String email;

    public User toEntity() {
        return new User(uid, username, email);
    }

    @Override
    public String toString() {
        return "requestUsers{" +
                "username='" + username + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}

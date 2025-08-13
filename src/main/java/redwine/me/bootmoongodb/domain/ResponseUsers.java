package redwine.me.bootmoongodb.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import redwine.me.bootmoongodb.entity.User;

@Getter
@Setter
public class ResponseUsers {
    private String uid;
    private String username;
    private String email;

    @Builder
    public ResponseUsers(String uid, String username, String email) {
        this.uid = uid;
        this.username = username;
        this.email = email;
    }
}

package redwine.me.bootmoongodb.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import redwine.me.bootmoongodb.entity.User;

import java.util.Optional;

public interface UserRepository extends MongoRepository<User, String> {

    Optional<User> findUsersByUserid(String id);

    void deleteUsersByUserid(String userid);
}

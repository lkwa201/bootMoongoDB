package redwine.me.bootmoongodb.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import redwine.me.bootmoongodb.domain.RequestUsers;
import redwine.me.bootmoongodb.domain.ResponseUsers;
import redwine.me.bootmoongodb.entity.User;
import redwine.me.bootmoongodb.repository.UserRepository;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Transactional
@Service
public class UserService {

    private final UserRepository userRepository;

    //등록
    public void userAdd(RequestUsers request) {
        userRepository.save(request.toEntity());
    }

    //단건 조회
    @Transactional(readOnly = true)
    public ResponseUsers findUser(String id) {
        return userRepository.findUsersByUserid(id)
                .orElseThrow(() -> new IllegalArgumentException("User not found!")).toUsers();
    }

    //수정
    public void updateUser(final RequestUsers request, String uid) {
        User users = userRepository.findUsersByUserid(uid)
                .orElseThrow(() -> new IllegalArgumentException("User not found!"));
        users.modifyUser(request);
        userRepository.save(users);
    }

    //삭제
    public void deleteUser(String uid) {
        userRepository.deleteUsersByUserid(uid);
    }

    //모든 목록 조회
    public List<ResponseUsers> findAll() {
        List<User> userList = userRepository.findAll();
        return userList.stream().map(User::toUsers).collect(Collectors.toList());
    }
}

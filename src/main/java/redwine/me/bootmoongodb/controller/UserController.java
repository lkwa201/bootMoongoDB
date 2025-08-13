package redwine.me.bootmoongodb.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import redwine.me.bootmoongodb.domain.RequestUsers;
import redwine.me.bootmoongodb.domain.ResponseUsers;
import redwine.me.bootmoongodb.service.UserService;

import java.util.HashMap;
import java.util.List;

@RequestMapping(value = "/api/v1/")
@RequiredArgsConstructor
@RestController
public class UserController {

    private final UserService userService;

    //추가
    @PostMapping("userAdd")
    public void userAdd(@RequestBody(required = false) RequestUsers request) {
        System.out.println("request = " + request);
        userService.userAdd(request);
    }

    //조회
    @GetMapping("searchUser/{uid}")
    public ResponseEntity<ResponseUsers> findUser(@PathVariable String uid) {
        return ResponseEntity.ok(userService.findUser(uid));
    }

    //수정
    @PostMapping("updateUser/{uid}")
    public void updateUser(@RequestBody(required = false) RequestUsers request, @PathVariable String uid) {
        userService.updateUser(request, uid);
        //return ResponseEntity.ok(updateUser);
    }

    //삭제
    @PostMapping("deleteUser/{uid}")
    public void deleteUser(@PathVariable String uid) {
        userService.deleteUser(uid);
    }

    //전체 목록 조회
    @GetMapping("userList")
    public HashMap<String, List<ResponseUsers>> findAllUsers() {
        List<ResponseUsers> usersList = userService.findAll();
        HashMap<String, List<ResponseUsers>> map = new HashMap<>();
        map.put("userList",usersList);
        return map;
    }
}

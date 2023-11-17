package com.doran.user.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.doran.child.service.ChildService;
import com.doran.parent.entity.Parent;
import com.doran.parent.mapper.ParentMapper;
import com.doran.parent.service.ParentService;
import com.doran.parent.type.Provider;
import com.doran.redis.invite.service.InviteService;
import com.doran.user.dto.req.UserTokenBaseDto;
import com.doran.user.dto.res.UserInfoRes;
import com.doran.user.entity.User;
import com.doran.user.mapper.UserMapper;
import com.doran.user.repository.UserRepository;
import com.doran.user.type.Roles;
import com.doran.utils.exception.dto.CustomException;
import com.doran.utils.exception.dto.ErrorCode;
import com.doran.utils.sens.MessageType;
import com.doran.utils.sens.Naver_Sens_V2;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final ParentMapper parentMapper;
    private final ParentService parentService;
    private final ChildService childService;
    private final InviteService inviteService;

    //로컬테스트용 회원가입
    public User signUp(User user) {
        return userRepository.save(user);
    }

    //회원가입
    public void signUp(String name, String email, Provider provider, Roles roles) {
        log.info("회원 가입 진행");
        log.info("유저 생성");
        User parentUser = userMapper.toUser(name, roles);
        User saveUser = signUp(parentUser);
        log.info("유저id : {}", saveUser.getId());

        inviteService.save(saveUser.getId());

        log.info("부모 생성");
        Parent parent = parentMapper.toParent(email, provider);
        parent.setUser(saveUser);
        Parent saveParent = parentService.saveParent(parent);

        log.info("아이 생성");
        User childUser = signUp(userMapper.toUser(name + "아이들", Roles.CHILD));
        childService.saveChild(saveParent, childUser);
    }

    //email로 회원 조회
    //있으면 로그인
    //없으면 회원가입
    public Optional<UserTokenBaseDto> findUser(String email, Provider provider) {
        log.info("email, provider로 회원 조회 진행");
        return userRepository.findUser(email, provider);
    }

    public Optional<UserTokenBaseDto> findUser(int childId, int profileId) {
        return userRepository.findUser(childId, profileId);
    }

    //유저 조회 - id
    public User findUser(int id) {
        Optional<User> findUser = userRepository.findById(id);

        return findUser.orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));
    }

    //초대코드 전송
    public void sendMessage(String code, String tel) {
        log.info("메세지 보내러 들어옴");
        Naver_Sens_V2 message = new Naver_Sens_V2();

        message.send_msg(tel, code, MessageType.INVITE);
    }

    public List<UserInfoRes> allUser() {
        List<User> users = userRepository.findAllUser();
        List<UserInfoRes> userInfos = new ArrayList<>();
        for (User user : users) {
            UserInfoRes userInfo = new UserInfoRes();
            userInfo.setUserRole(user.getUserRole());
            userInfo.setId(user.getId());
            userInfo.setName(user.getName());
            userInfos.add(userInfo);
        }
        return userInfos;
    }

}

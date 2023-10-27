package com.doran.profile.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.doran.child.dto.res.ChildDto;
import com.doran.child.service.ChildService;
import com.doran.jwt.JwtProvider;
import com.doran.profile.dto.req.ChangeProfileAnimalDto;
import com.doran.profile.dto.req.ChangeProfileDto;
import com.doran.profile.dto.req.CreateProfileDto;
import com.doran.profile.dto.req.ProfileLoginDto;
import com.doran.profile.service.ProfileService;
import com.doran.redis.invite.key.Invite;
import com.doran.redis.invite.service.InviteService;
import com.doran.user.dto.req.UserTokenBaseDto;
import com.doran.user.service.OauthService;
import com.doran.utils.auth.Auth;
import com.doran.utils.common.UserInfo;
import com.doran.utils.response.CommonResponseEntity;
import com.doran.utils.response.SuccessCode;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/profile")
public class ProfileController {

    private final ProfileService profileService;
    private final InviteService inviteService;
    private final ChildService childService;
    private final OauthService oauthService;
    private final JwtProvider jwtProvider;

    @GetMapping("")
    public ResponseEntity<?> childProfileList(@RequestParam String code) {
        Invite invite = inviteService.findCode(code);
        ChildDto childDto = childService.findChildByParentUserId(invite.getUserId());
        return CommonResponseEntity.getResponseEntity(SuccessCode.OK,
            profileService.selectAllProfile(childDto.getId()));
    }

    @PostMapping("")
    public ResponseEntity<?> createChildProfile(@RequestBody CreateProfileDto req) {
        UserInfo userInfo = Auth.getInfo();
        profileService.createChildProfile(userInfo.getUserId(), req.getName());
        return CommonResponseEntity.getResponseEntity(SuccessCode.OK);
    }

    @PostMapping("/animal")
    public ResponseEntity<?> updateProfileAnimal(@RequestBody ChangeProfileAnimalDto req) {
        UserInfo userInfo = Auth.getInfo();
        profileService.updateProfileAnimal(userInfo, req.getAnimalId());
        return CommonResponseEntity.getResponseEntity(SuccessCode.OK);
    }

    /**
     * userInfo : 로그인된 CHILD 계정
     * profile : 선택한 profileId
     */
    @PostMapping("/change/{profile}")
    public ResponseEntity<?> changeProfile(@PathVariable ChangeProfileDto req, HttpServletResponse response) {

        UserInfo userInfo = Auth.getInfo();
        UserTokenBaseDto findDto = oauthService.getFindDto(userInfo.getUserId(), req.getProfileId());

        String accessToken = jwtProvider.createAccessToken(findDto);
        String refreshToken = jwtProvider.createRefreshToken(findDto);

        response.setHeader("AccessToken", accessToken);
        response.setHeader("RefreshToken", refreshToken);

        return CommonResponseEntity.getResponseEntity(SuccessCode.OK);
    }

    /**
     *
     * req : 선택한 profile에 있는 childUser의 id, profile의 id
     */
    @PostMapping("/login")
    public ResponseEntity<?> loginProfile(@RequestBody ProfileLoginDto req, HttpServletResponse response) {

        UserTokenBaseDto findDto = oauthService.getFindDto(req.getId(), req.getProfileId());

        String accessToken = jwtProvider.createAccessToken(findDto);
        String refreshToken = jwtProvider.createRefreshToken(findDto);

        response.setHeader("AccessToken", accessToken);
        response.setHeader("RefreshToken", refreshToken);

        return CommonResponseEntity.getResponseEntity(SuccessCode.OK);
    }
}

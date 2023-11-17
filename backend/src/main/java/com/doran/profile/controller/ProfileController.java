package com.doran.profile.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
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
import com.doran.profile.dto.req.ProfileGetDto;
import com.doran.profile.dto.req.ProfileLoginDto;
import com.doran.profile.dto.res.ProfileDto;
import com.doran.profile.dto.res.ProfileListDto;
import com.doran.profile.service.ProfileService;
import com.doran.redis.balcklist.service.BlackListService;
import com.doran.redis.invite.key.Invite;
import com.doran.redis.invite.service.InviteService;
import com.doran.user.dto.req.UserTokenBaseDto;
import com.doran.user.service.OauthService;
import com.doran.utils.auth.Auth;
import com.doran.utils.common.UserInfo;
import com.doran.utils.response.CommonResponseEntity;
import com.doran.utils.response.SuccessCode;

import jakarta.servlet.http.HttpServletRequest;
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
    private final BlackListService blackListService;

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

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_CHILD')")
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
    @PostMapping("/change")
    public ResponseEntity<?> changeProfile(@RequestBody ChangeProfileDto req, HttpServletResponse response,
        HttpServletRequest request) {
        log.info("chang 진행");

        String oldAt = jwtProvider.getAccessToken(request);
        blackListService.save(oldAt);

        UserInfo userInfo = Auth.getInfo();

        ChildDto childDto = childService.findChildByChildUSerId(userInfo.getUserId());

        UserTokenBaseDto findDto = oauthService.getFindDto(childDto.getId(), req.getProfileId());

        String accessToken = jwtProvider.createAccessToken(findDto);

        log.info("토큰 재발급 진행");
        response.setHeader("AccessToken", accessToken);

        return CommonResponseEntity.getResponseEntity(SuccessCode.OK);
    }

    /**
     *
     * req : 선택한 profile에 있는 childUser의 id, profile의 id
     */
    @PostMapping("/login")
    public ResponseEntity<?> loginProfile(@RequestBody ProfileLoginDto req, HttpServletResponse response) {

        UserTokenBaseDto findDto = oauthService.getFindDto(req.getChildId(), req.getProfileId());

        String accessToken = jwtProvider.createAccessToken(findDto);

        response.setHeader("AccessToken", accessToken);

        return CommonResponseEntity.getResponseEntity(SuccessCode.OK);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_PARENT')")
    @GetMapping("/list")
    public ResponseEntity<?> getProfileList() {

        UserInfo userInfo = Auth.getInfo();

        ChildDto childDto = childService.findChildByParentUserId(userInfo.getUserId());

        ProfileListDto profileListDto = profileService.selectAllProfile(childDto.getId());

        return CommonResponseEntity.getResponseEntity(SuccessCode.OK, profileListDto);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_PARENT')")
    @GetMapping("/info")
    public ResponseEntity<?> getProfileInfo(@RequestBody ProfileGetDto req) {

        ProfileDto profileDto = profileService.selectProfile(req.getProfileId());

        return CommonResponseEntity.getResponseEntity(SuccessCode.OK, profileDto);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_PARENT')")
    @DeleteMapping()
    public ResponseEntity<?> deleteProfile(@RequestBody ProfileGetDto req) {
        profileService.deleteProfile(req.getProfileId());

        return CommonResponseEntity.getResponseEntity(SuccessCode.OK, "정상적으로 삭제되었습니다.");
    }

}

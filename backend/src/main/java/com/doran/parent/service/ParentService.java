package com.doran.parent.service;

import org.springframework.stereotype.Service;

import com.doran.child.repository.ChildRepository;
import com.doran.parent.entity.Parent;
import com.doran.parent.repository.ParentRepository;
import com.doran.utils.exception.dto.CustomException;
import com.doran.utils.exception.dto.ErrorCode;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class ParentService {
    private final ParentRepository parentRepository;

    //부모 계정 저장
    public Parent saveParent(Parent parent) {
        return parentRepository.save(parent);
    }

    public Boolean checkParent(String role) {
        return role.equals("CHILD");
    }

	public Parent findParentByChildUserId(int childUserId) {
		return parentRepository.findParentByChildUserId(childUserId)
			.orElseThrow(() -> new CustomException(ErrorCode.PARENT_NOT_FOUND));
	}

    /*
    유저가 부모일 때 : 유저 ID 반납
    유저가 아이일 때 : 해당 부모의 유저 ID 반납
     */
    public int getParentUserId(int userId, String role) {
        return checkParent(role) ? userId : findParentByChildUserId(userId).getUser().getId();
    }


}

package com.doran.child.service;

import com.doran.child.mapper.ChildMapper;
import com.doran.child.entity.Child;
import com.doran.child.repository.ChildRepository;
import com.doran.parent.entity.Parent;
import com.doran.user.entity.User;
import com.doran.utils.exception.dto.CustomException;
import com.doran.utils.exception.dto.ErrorCode;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChildService {
    private final ChildRepository childRepository;
    private final ChildMapper childMapper;

    //아이 계정 저장
    public void saveChild(Parent parent, User user) {
        Child child = childMapper.toChild(parent, user);
        childRepository.save(child);
    }

    //부모의 유저아이디로 자식객체를 찾아옴.
    public Child findChildByParentUserId(int parentId) {
        return childRepository.findChildByParentUserId(parentId)
            .orElseThrow(() -> new CustomException(ErrorCode.CHILD_NOT_FOUND));
    }

}

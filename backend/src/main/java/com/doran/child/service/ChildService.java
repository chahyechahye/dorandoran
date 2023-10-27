package com.doran.child.service;

import org.springframework.stereotype.Service;

import com.doran.child.ChildMapper;
import com.doran.child.dto.res.ChildDto;
import com.doran.child.entity.Child;
import com.doran.child.repository.ChildRepository;
import com.doran.parent.entity.Parent;
import com.doran.user.entity.User;
import com.doran.utils.exception.dto.CustomException;
import com.doran.utils.exception.dto.ErrorCode;

import lombok.RequiredArgsConstructor;

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

    public ChildDto findChildByParentUserId(int userId) {
        return childRepository.findChildToParentUserId(userId)
                              .orElseThrow(() -> new CustomException(ErrorCode.CHILD_NOT_FOUND));
    }
}

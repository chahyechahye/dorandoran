package com.doran.child.service;

import com.doran.child.ChildMapper;
import com.doran.child.entity.Child;
import com.doran.child.repository.ChildRepository;
import com.doran.parent.entity.Parent;
import com.doran.user.entity.User;
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
}

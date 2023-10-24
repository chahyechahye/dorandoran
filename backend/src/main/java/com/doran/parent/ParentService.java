package com.doran.parent;

import org.springframework.stereotype.Service;

import com.doran.parent.entity.Parent;
import com.doran.parent.repository.ParentRepository;

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
}

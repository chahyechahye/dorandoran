package com.doran.content.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.doran.content.dto.res.ContentResDto;
import com.doran.content.repository.ContentRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class ContentService {
    private final ContentRepository contentRepository;

    public List<ContentResDto> getContentWithVoice(int userId, int pageId) {
        return contentRepository.getContentWithVoice(userId, pageId);
    }
}

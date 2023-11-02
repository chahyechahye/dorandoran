package com.doran.content.repository;

import java.util.List;

import com.doran.content.dto.res.ContentResDto;

public interface ContentRepositoryCustom {
    List<ContentResDto> getContentWithVoice(int userId, Integer pageId);
}

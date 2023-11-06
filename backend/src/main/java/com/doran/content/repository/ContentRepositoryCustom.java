package com.doran.content.repository;

import java.util.List;

import com.doran.content.dto.res.ContentResDto;
import com.doran.page.entity.Page;

public interface ContentRepositoryCustom {
    List<ContentResDto> getContentWithVoice(int userId, Integer pageId, Integer bookId);

    List<String> findContentByPageList(List<Page> pageList);

    void updateScript();
}

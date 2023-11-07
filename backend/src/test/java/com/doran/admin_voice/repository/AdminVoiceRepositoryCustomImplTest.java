package com.doran.admin_voice.repository;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.doran.admin_voice.dto.res.AdminFindResDto;
import com.doran.admin_voice.dto.res.AdminVoiceResDto;
import com.doran.utils.common.Genders;

@SpringBootTest
public class AdminVoiceRepositoryCustomImplTest {
    @Autowired
    private AdminVoiceRepository adminVoiceRepository;

    @Test
    @DisplayName("관리자 목소리 테스트")
    public void 관리자목소리_테스트() {
        List<AdminFindResDto> list = adminVoiceRepository.findAdminVoiceAndBook(null,25);
        if (list.size() == 0)
            System.out.println("응애 아무것도 없어요");
        for (AdminFindResDto a : list) {
            System.out.println(a.getBookId() + "------------------------");
            for (AdminVoiceResDto b : a.getAdminVoiceList())
                System.out.print(b.getContentId() + " ");
            System.out.println();
        }

    }
}
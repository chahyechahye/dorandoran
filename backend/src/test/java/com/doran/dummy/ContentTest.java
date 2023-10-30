package com.doran.dummy;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.doran.content.service.ContentService;
import com.doran.page.entity.Page;
import com.doran.page.service.PageService;

@SpringBootTest
public class ContentTest {
    @Autowired
    PageService pageService;

    @Autowired
    ContentService contentService;

    @Test
    public void test() {
        //동화책 id
        int bookId = 1;
        //임시 이미지
        String imgUrl = "https://storage.cloud.google.com/ssafy-last-project/4302f8be-34c1-4072-a3aa-b4c69d85c6e4";
        AtomicInteger idx = new AtomicInteger(1);

        System.out.println("test 중");
        String[] scripts = makeScripts();

        Arrays.stream(scripts)
            .forEach(s -> {
                Page page = pageService.insertPage(bookId, imgUrl, idx.getAndIncrement());

                contentService.insertContent(page, s);
            });

    }

    public String[] makeScripts() {
        String text = "옛날 아주 먼 옛날에, 토끼와 거북이가 살고 있었습니다.\n"
            + "비켜라! 비켜! 깡충깡충 토끼님이 나가신다!!\n"
            + "야! 거기 느림보 거북이!! 비켜!!\n"
            + "화가 난 거북이는 길을 멈추고 말했어요\n"
            + "토끼!! 산꼭대기까지 달리기 경주하자!\n"
            + "너랑?? 당연히 내가 이길텐데?~\n"
            + "내일 공원으로 나와!!\n"
            + "다음 날 토끼와 거북이는 경주를 하기 위해 공원에 모였습니다.\n"
            + "준비~ 땅!\n"
            + "토끼는 깡충깡충 뛰어 순식간에 사라졌고\n"
            + "거북이는 느릿느릿 땀을 흘리며 따라갔어요.\n"
            + "이 느림보가 어디쯤 오고 있나~\n"
            + "아직도 멀었네~ 조금만 자다 가야지~\n"
            + "열심히 달리고 있던 토끼는 잠시 나무 밑에 쉬어 가기로 했습니다.\n"
            + "토끼가 자는 사이, 거북이는 열심히 기어서 토끼가 자고 있는 나무 밑을 지나치게 되었습니다.\n"
            + "아휴 잘 잤다 어차피 거북이는 못 따라와!! 이제 다시 가볼까??\n"
            + "아니 뭐야?? 거북이가 왜 저기 있어!!\n"
            + "거북이는 이미 산꼭대기에 도착해있었어요.\n"
            + "토끼!! 너가 자는 사이에 나는 이미 도착했지~\n"
            + "토끼는 발을 동동 구르며 후회했지만 아무 소용이 없었답니다.";

        return text.split("\n");
    }
}

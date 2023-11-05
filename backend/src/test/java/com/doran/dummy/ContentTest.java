package com.doran.dummy;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.doran.content.service.ContentService;
import com.doran.page.entity.Page;
import com.doran.page.service.PageService;
import com.doran.record_book.dto.res.ScriptDto;
import com.doran.record_book.repository.RecordBookRepository;

@SpringBootTest
public class ContentTest {
    @Autowired
    PageService pageService;

    @Autowired
    ContentService contentService;
    @Autowired
    RecordBookRepository recordBookRepository;

    @Test
    void ttttttt() {
        List<String> bookName = recordBookRepository.findBookName();

        for (String s : bookName) {
            System.out.println(s);
        }

        // List<Long> toTalPage = recordBookRepository.findToTalPage(bookName);
        // for (Long aLong : toTalPage) {
        //     System.out.println(aLong);
        // }

        for (String s : bookName) {
            List<ScriptDto> script = recordBookRepository.findScript(s);
            for (ScriptDto scriptDto : script) {
                System.out.println(scriptDto);
            }
        }
    }

    @Test
    public void test() {
        //동화책 id
        int bookId = 2;
        //임시 이미지
        String imgUrl = "https://storage.cloud.google.com/ssafy-last-project/4302f8be-34c1-4072-a3aa-b4c69d85c6e4";
        AtomicInteger idx = new AtomicInteger(1);

        System.out.println("test 중");
        String[] scripts = makeScripts();

        Arrays.stream(scripts)
            .forEach(s -> {
                Page page = pageService.insertPage(bookId, imgUrl, idx.getAndIncrement());

                String[] split = s.split("#");
                Arrays.stream(split)
                    .forEach(sc -> contentService.insertContent(page, sc));
            });

    }

    @Test
    public String[] makeScripts() {
        String text = "어느 숲 속 마을에 아기돼지 삼 형제가 살고 있었어요.\n"
            + "아기돼지 삼 형제는 집을 떠나 각자의 집을 짓고 살기로 했어요.\n"
            + "큰형 돼지는 주변의 갈대를 주워 집을 짓기 시작했어요.#갈대를 얼기설기 엮어 만든 집은 금세 완성이 되었지요.\n"
            + "둘째 돼지는 산에서 나뭇가지를 주워 집을 짓기 시작했어요.#둘째 돼지의 나무집도 어느새 완성이 되었지요.\n"
            + "집을 다 지은 큰형 돼지와 둘째 돼지는 노래하고 춤추며 신나게 놀았어요.\n"
            + "하지만 막내 돼지는 형들과 달랐어요.#시간은 오래 걸리겠지만 튼튼한 벽돌집을 짓고 싶었어요.\n"
            + "막내 돼지는 한 장 한 장 벽돌을 쌓아 흙을 발라가며 열심히 집을 지었지요.\n"
            + "큰형 돼지와 둘째 돼지가 막내돼지를 찾아왔어요.\n"
            + "\"막내야 우리 같이 놀자~\"#\"안 돼! 난 지금 벽돌집을 짓느라 바쁘거든\"\n"
            + "그러던 어느 날, 큰 형 돼지의 집에 늑대가 나타났어요.\n"
            + "\"돼지야, 이리 나와봐! 문좀 열어줘.\"#\"싫어, 나를 잡아먹으려는 거지?\"\n"
            + "큰형 돼지가 문을 열어주지 않자 늑대는 크게 숨을 들이마셨어요.\n"
            + "그리고는 \"후\"하고 입김을 불었어요.\n"
            + "그러자 큰형 돼지의 갈대집이 우수수 무너지고 말았답니다.\n"
            + "큰형 돼지는 얼른 둘째 돼지의 집으로 도망을 쳤어요.#하지만 늑대는 둘째 돼지의 집까지 쫓아왔어요.\n"
            + "'돼지들아, 이리와 문 좀 열어줘.'#\"싫어, 우리를 잡아먹으려는 거지?\"\n"
            + "둘째 돼지가 문을 열어주지 않자 늑대는 이번에도 크게 숨을 들이마셨어요.\n"
            + "그리고는 '후후' 입김을 불었지요.\n"
            + "그러자 둘째 돼지의 나무집도 와르르 무너지고 말았어요.\n"
            + "큰형 돼지와 둘째 돼지는#서둘러 막내 돼지의 집으로 도망을 쳤어요.\n"
            + "\"막내야, 살려줘! 내 우리집이 날아가버렸어.\"#\"형님들, 걱정 마세요\"\n"
            + "늑대는 막내 돼지의 집까지 찾아왔어요.\n"
            + "\"돼지들아~ 문좀 열어줘~\"#\"안돼 이 나쁜 늑대야, 저리가!\"\n"
            + "늑대는 큰 숨을 세번 들이마시더니 입김을 불기 시작했어요.\n"
            + "'후후후'하고 불었지만#막내 돼지의 튼튼한 벽돌집은 꼼짝도 하지 않았어요.\n"
            + "\"그렇다고 내가 못 들어갈 줄 알고?\"#늑대는 재빨리 지붕으로 올라가 굴뚝을 타고 들어오려고 했어요.\n"
            + "아기돼지 삼형제는 뜨거운 물이 가득 담긴 커다란 냄비를 벽난로에 올려놓았어요.\n"
            + "그런줄도 모르고 굴뚝으로 내려온 늑대는#뜨거운 냄비 속으로 풍덩 빠지고 말았지요.\n"
            + "\"아 뜨거워! 늑대 살려!\"#엉덩이에 불이 난 늑대는 펄쩍 뛰면서 달아났지요.\n"
            + "그 후로 늑대는 다시는 아기돼지 삼형제를 찾아오지 않았어요.\n"
            + "아기돼지 삼형제는 막내돼지의 벽돌집에서 #오래오래 행복하게 살았답니다.";

        return text.split("\n");
    }

    //전처리
    @Test
    public void test3() {
        String text = "어느 숲 속 마을에 엄마돼지와 아기돼지 삼 형제가 살고 있었어요.\n"
            + "어느 날 엄마돼지는 아기돼지 삼 형제를 불러 모았어요.\n"
            + "'얘들아, 이제 너희들도 다 컸으니 집을 떠나 혼자 힘으로 살아보렴\"\n"
            + "아기돼지 삼 형제는 집을 떠나 각자의 집을 짓고 살기로 했어요.\n"
            + "큰형 돼지는 주변의 갈대를 주워 집을 짓기 시작했어요.\n"
            + "갈대를 얼기설기 엮어 만든 집은 금세 완성이 되었지요.\n"
            + "둘째 돼지는 산에서 나뭇가지를 주워 집을 짓기 시작했어요.\n"
            + "둘째 돼지의 나무집도 어느새 완성이 되었지요.\n"
            + "집을 다 지은 큰형 돼지와 둘째 돼지는 노래하고 춤추며 신나게 놀았어요.\n"
            + "하지만 막내 돼지는 형들과 달랐어요.\n"
            + "시간은 오래 걸리겠지만 튼튼한 벽돌집을 짓고 싶었어요.\n"
            + "막내 돼지는 한 장 한 장 벽돌을 쌓아 \n"
            + "흙을 발라가며 열심히 집을 지었지요.\n"
            + "큰형 돼지와 둘째 돼지가 막내돼지를 찾아왔어요.\n"
            + "\"막내야 우리 같이 놀자~\"\n"
            + "\"안 돼! 난 지금 벽돌집을 짓느라 바쁘거든\"\n"
            + "그러던 어느 날, 큰 형 돼지의 집에 늑대가 나타났어요.\n"
            + "\"돼지야, 이리 나와봐! 문좀 열어줘.\"\n"
            + "\"싫어, 나를 잡아먹으려는 거지?\"\n"
            + "큰형 돼지가 문을 열어주지 않자 늑대는 크게 숨을 들이마셨어요.\n"
            + "그리고는 \"후\"하고 입김을 불었어요.\n"
            + "그러자 큰형 돼지의 갈대집이 우수수 무너지고 말았답니다.\n"
            + "큰형 돼지는 얼른 둘째 돼지의 집으로 도망을 쳤어요.\n"
            + "하지만 늑대는 둘째 돼지의 집까지 쫓아왔어요.\n"
            + "'돼지들아, 이리와 문 좀 열어줘.'\n"
            + "\"싫어, 우리를 잡아먹으려는 거지?\"\n"
            + "둘째 돼지가 문을 열어주지 않자 늑대는 이번에도 크게 숨을 들이마셨어요.\n"
            + "그리고는 '후후' 입김을 불었지요.\n"
            + "그러자 둘째 돼지의 나무집도 와르르 무너지고 말았어요.\n"
            + "큰형 돼지와 둘째 돼지는\n"
            + "서둘러 막내 돼지의 집으로 도망을 쳤어요.\n"
            + "\"막내야, 살려줘! 내 갈대집이 날아가버렸어.\"\n"
            + "\"내 나무집도 날아가 버렸어!\"\n"
            + "\"형님들, 걱정 마세요\"\n"
            + "막내 돼지는 늑대가 들어오지 못하게 문을 꼭 잠갔어요.\n"
            + "\n"
            + "\n"
            + "\n"
            + "\n"
            + "\n"
            + "늑대는 막내 돼지의 집까지 찾아왔어요.\n"
            + "\n"
            + "\n"
            + "\n"
            + "\"돼지들아~ 문좀 열어줘~\"\n"
            + "\n"
            + "\n"
            + "\n"
            + "\"안돼 이 나쁜 늑대야, 저리가!\"\n"
            + "\n"
            + "\n"
            + "\n"
            + "늑대는 큰 숨을 세번 들이마시더니 입김을 불기 시작했어요.\n"
            + "\n"
            + "\n"
            + "\n"
            + "'후후후'하고 불었지만\n"
            + "\n"
            + "\n"
            + "\n"
            + "막내 돼지의 튼튼한 벽돌집은 꼼짝도 하지 않았어요.\n"
            + "\n"
            + "\n"
            + "\n"
            + "\n"
            + "\n"
            + "\"그렇다고 내가 못 들어갈 줄 알고?\"\n"
            + "\n"
            + "\n"
            + "\n"
            + "늑대는 재빨리 지붕으로 올라가 굴뚝을 타고 들어오려고 했어요.\n"
            + "\n"
            + "\n"
            + "\n"
            + "아기돼지 삼형제는 뜨거운 물이 가득 담긴 커다란 냄비를 벽난로에 올려놓았어요.\n"
            + "\n"
            + "\n"
            + "\n"
            + "그런줄도 모르고 굴뚝으로 내려온 늑대는\n"
            + "\n"
            + "\n"
            + "\n"
            + "뜨거운 냄비 속으로 풍덩 빠지고 말았지요.\n"
            + "\n"
            + "\n"
            + "\n"
            + "\"아 뜨거워! 늑대 살려!\"\n"
            + "\n"
            + "\n"
            + "\n"
            + "엉덩이에 불이 난 늑대는 펄쩍 뛰면서 달아났지요.\n"
            + "\n"
            + "\n"
            + "\n"
            + "그 후로 늑대는 다시는 아기돼지 삼형제를 찾아오지 않았어요.\n"
            + "\n"
            + "\n"
            + "\n"
            + "아기돼지 삼형제는 막내돼지의 벽돌집에서 \n"
            + "\n"
            + "\n"
            + "\n"
            + "오래오래 행복하게 살았답니다.\n";

        String[] split = text.split("\\n+");

        for (String s : split) {
            System.out.println(s);
        }

    }

}

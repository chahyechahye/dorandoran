package com.doran.dummy;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.doran.record_book.dto.res.ScriptDto;
import com.doran.record_book.entity.RecordBook;
import com.doran.record_book.mapper.RecordBookMapper;
import com.doran.record_book.repository.RecordBookRepository;

import jakarta.transaction.Transactional;

@SpringBootTest
public class RecordBookTest {
    @Autowired
    RecordBookRepository recordBookRepository;

    @Autowired
    RecordBookMapper recordBookMapper;

    @Test
    @DisplayName("대본 넣기")
    @Transactional
    public void insert() {
        String title = "tttttt";
        AtomicInteger scriptNum = new AtomicInteger(1);
        String[] script = replace();

        Arrays.stream(script)
            .forEach(s -> {
                RecordBook recordBook = recordBookMapper.toRecordBook(title, s, scriptNum.getAndIncrement());

                recordBookRepository.save(recordBook);
            });
        
        List<ScriptDto> script1 = recordBookRepository.findScript(title);

        for (ScriptDto scriptDto : script1) {
            System.out.println(scriptDto.getScript());
            System.out.println(scriptDto.getScriptNum());
        }
    }

    @DisplayName("정제 진행")
    public String[] replace() {
        String str = "tttttttttttttt \n"
            + "tttttttttttttt\n"
            + "a\n"
            + "n\n"
            + "ds\n"
            + "sdf\n"
            + "sdf\n"
            + "sdf\n"
            + "sdf";

        return str.split("\\n+");
    }
}

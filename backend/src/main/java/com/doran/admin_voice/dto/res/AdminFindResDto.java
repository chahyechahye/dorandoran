package com.doran.admin_voice.dto.res;

import java.util.List;

import com.doran.admin_voice.entity.AdminVoice;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AdminFindResDto {
    int bookId;
    String title;
    List<AdminVoiceResDto> adminVoiceList;
}

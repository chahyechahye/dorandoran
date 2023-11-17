package com.doran.utils.sens;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@AllArgsConstructor
public enum MessageType {
    INVITE("도란도란 초대 코드 : ["),
    MODEL("목소리 모델 생성이 완료되었습니다."),
    VOICE("동화 등록이 완료되었습니다.");

    private final String msg;
}

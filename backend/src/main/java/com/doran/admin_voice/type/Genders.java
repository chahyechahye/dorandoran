package com.doran.admin_voice.type;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Genders {
    MALE("GENDER_MALE"),
    FEMALE("GENDER_FEMALE");
    private final String gender;
}

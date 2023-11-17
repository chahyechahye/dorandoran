package com.doran.utils.rabbitmq.dto.res;

import com.doran.utils.common.Genders;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class ModelResMessage {
    int userId;
    private Genders genders;
}

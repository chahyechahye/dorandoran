package com.doran.album.dto.req;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class AlbumReqDto {
    private MultipartFile multipartFile;
}

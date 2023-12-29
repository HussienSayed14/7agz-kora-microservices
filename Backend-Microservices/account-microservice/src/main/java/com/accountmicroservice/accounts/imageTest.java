package com.accountmicroservice.accounts;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class imageTest {

    private MultipartFile file;
    private String email;
}

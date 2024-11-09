package com.oliverIT.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResetPwdFormDTO {

    private String email;

    private String oldPwd;

    private String newPwd;

    private String confirmPwd;

}

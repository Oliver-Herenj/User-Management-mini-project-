package com.oliverIT.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterFormDTO {

    private Integer userId;

    private String uname;

    private String email;

    private String phno;

    private String pwd;

    private String pwdUpdated;

    private Integer countryId;

    private Integer stateId;

    private Integer cityId;



}

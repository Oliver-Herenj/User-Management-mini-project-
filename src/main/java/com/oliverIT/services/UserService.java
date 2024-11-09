package com.oliverIT.services;




import com.oliverIT.dto.LoginFormDTO;
import com.oliverIT.dto.RegisterFormDTO;
import com.oliverIT.dto.ResetPwdFormDTO;
import com.oliverIT.dto.UserDTO;

import java.util.Map;


public interface UserService{

    public Map<Integer, String> getCountries();

    public Map<Integer, String> getState(Integer countryId);

    public Map<Integer, String> getCities(Integer stateId);

    public boolean duplicateEmailCheck(String email);

    public boolean saveUser(RegisterFormDTO registerFormDTO);

    public UserDTO login(LoginFormDTO loginFormDTO);

    public boolean resetPwd(ResetPwdFormDTO resetPwdFormDTO);

}

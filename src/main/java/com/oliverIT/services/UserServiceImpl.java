package com.oliverIT.services;

import com.oliverIT.dto.LoginFormDTO;
import com.oliverIT.dto.RegisterFormDTO;
import com.oliverIT.dto.ResetPwdFormDTO;

import com.oliverIT.dto.UserDTO;
import com.oliverIT.entity.CityEntity;
import com.oliverIT.entity.CountryEntity;
import com.oliverIT.entity.StateEntity;
import com.oliverIT.entity.UserEntity;
import com.oliverIT.repo.CityRepo;
import com.oliverIT.repo.CountryRepo;
import com.oliverIT.repo.StateRepo;
import com.oliverIT.repo.UserRepo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private CountryRepo countryRepo;

    @Autowired
    private CityRepo cityRepo;

    @Autowired
    private StateRepo stateRepo;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private EmailService emailService;
    
    Random random = new Random();

    @Override
    public Map<Integer, String> getCountries() {

        Map<Integer, String> countryMap = new HashMap<>();

        List<CountryEntity> countryList = countryRepo.findAll();

        countryList.forEach(
        		c -> countryMap.put(c.getCountryId(),c.getCountryName())
        		);

        return countryMap;
    }

    @Override
    public Map<Integer, String> getState(Integer countryId) {

        Map<Integer, String> stateMap = new HashMap<>();

        List<StateEntity> stateList = stateRepo.findByCountryId(countryId);

        stateList.forEach(
        		s -> stateMap.put(s.getStateId(), s.getStateName())
        );

        return stateMap;
    }
    @Override
    public Map<Integer, String> getCities(Integer stateId) {

        Map<Integer, String> cityMap = new HashMap<>();

        List<CityEntity> cityList = cityRepo.findByStateId(stateId);

        cityList.forEach(
        		c ->cityMap.put(c.getCityId(), c.getCityName())
        );

        return cityMap;
    }

    @Override
    public boolean duplicateEmailCheck(String email) {

        UserEntity byEmail = userRepo.findByEmail(email);

        return byEmail != null;
            

    }

    @Override
    public boolean saveUser(RegisterFormDTO registerFormDTO) {

        UserEntity userEntity = new UserEntity();

        BeanUtils.copyProperties(registerFormDTO, userEntity);

        CountryEntity country = countryRepo.findById(registerFormDTO.getCountryId()).orElse(null);
        userEntity.setCountry(country);

        StateEntity state = stateRepo.findById(registerFormDTO.getStateId()).orElse(null);
        userEntity.setState(state);

        CityEntity city = cityRepo.findById(registerFormDTO.getCityId()).orElse(null);
        userEntity.setCity(city);

        String randomPwd = generatePwd();

        userEntity.setPwd(randomPwd);
        userEntity.setPwdUpdated("No");
        UserEntity savedUser = userRepo.save(userEntity);

        if(savedUser.getUserId() != null) {
            String subject = "Your Account created";
            String body ="Your password to login :"+randomPwd;
            String to = registerFormDTO.getEmail();
            emailService.sendEmail(subject,body,to);
            return true;

        }
        return false;
    }

    @Override
    public UserDTO login(LoginFormDTO loginFormDTO) {
        UserEntity userEntity = userRepo.findByEmailAndPwd(loginFormDTO.getEmail(), loginFormDTO.getPwd());
        if(userEntity != null) {
            UserDTO userDto = new UserDTO();
            BeanUtils.copyProperties(userEntity, userDto);
            return userDto;
        }
        return null;
    }

    @Override
    public boolean resetPwd(ResetPwdFormDTO resetPwdFormDTO) {
        String email = resetPwdFormDTO.getEmail();

        UserEntity userEntity = userRepo.findByEmail(email);

        //Setting new password
        userEntity.setPwd(resetPwdFormDTO.getNewPwd());
        userEntity.setPwdUpdated("Yes");

        userRepo.save(userEntity); //UPSERT
        return true;
    }

    public String generatePwd() {

        String upperCase = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String lowerCase = "abcdefghijklmnopqrstuvwxyz";

        String alphabets = upperCase + lowerCase;
        

        StringBuilder generatedPwd = new StringBuilder();

        for(int i=0;i<5;i++) {
            int randomIndex = random.nextInt(alphabets.length());
            generatedPwd.append(alphabets.charAt(randomIndex));
        }

        return generatedPwd.toString();
    }

}



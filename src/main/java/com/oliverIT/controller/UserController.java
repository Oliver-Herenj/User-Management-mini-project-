package com.oliverIT.controller;

import com.oliverIT.constants.AppConstants;
import com.oliverIT.dto.*;
import com.oliverIT.services.DashboardService;
import com.oliverIT.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
public class UserController {

    @Autowired(required = true)
    private UserService userService;

    @Autowired
    private DashboardService dashboardService;



    @GetMapping("/register")
    public String loadRegisterPage(Model model) {

        Map<Integer, String> countriesMap = userService.getCountries();
        model.addAttribute("countries",countriesMap);

        RegisterFormDTO registerFormDTO = new RegisterFormDTO();
        model.addAttribute("registerForm",registerFormDTO);

        return "register";

    }

    @GetMapping("/states/{countryId}")
    @ResponseBody
    public Map<Integer, String> getStates(@PathVariable Integer countryId, Model model) {

        Map<Integer, String> statesMap = userService.getState(countryId);
        model.addAttribute("states", statesMap);

        return statesMap;

    }

    @GetMapping("/cities/{stateId}")
    @ResponseBody
    public Map<Integer, String> getCities(@PathVariable Integer stateId, Model model){

        Map<Integer, String> citiesMap = userService.getCities(stateId);
        model.addAttribute("cities", citiesMap);

        return citiesMap;
    }


    @PostMapping("/register")
    public String handleRegistration(RegisterFormDTO registerFormDTO, Model model) {

        boolean status = userService.duplicateEmailCheck(registerFormDTO.getEmail());
        if(status) {
            model.addAttribute("emsg","Duplicate Email Found");
        }else {
            boolean saveUser = userService.saveUser(registerFormDTO);
            if(saveUser) {
                //user saved
                model.addAttribute("smsg","Registration Success, Please check your email ..");
            }else {
                //failed to save
                model.addAttribute("emsg", "Registration Failed!");
            }
        }

        model.addAttribute("registerForm",new RegisterFormDTO());
        model.addAttribute("countries",userService.getCountries());

        return "register";

    }

    @GetMapping("/")
    public String index(Model model) {

        LoginFormDTO loginFormDTO = new LoginFormDTO();
        model.addAttribute("loginForm", loginFormDTO);

        return "login";
    }

    @PostMapping("/login")
    public String handleUserLogin(LoginFormDTO loginFormDTO, Model model) {

        UserDTO userDTO = userService.login(loginFormDTO);
        if(userDTO == null) {
            model.addAttribute("emsg", "Invalid Credentials!");

            model.addAttribute("loginForm", new LoginFormDTO());

        }else {
            String updatedPwd = userDTO.getPwdUpdated();
            if("Yes".equals(updatedPwd)) {
                //display dashboard
                return "redirect:/dashboard";
            }else {
                //display reset pwd page
                return "redirect:/rest-pwd-page?email="+userDTO.getEmail();
            }
        }
        return "login";
    }

    @GetMapping("/dashboard")
    public String dashboard(Model model) {

        QuoteApiResponseDTO quoteApiResponseDTO = dashboardService.getQuote();
        model.addAttribute("quote", quoteApiResponseDTO);

        return "dashboard";
    }

    @GetMapping("/rest-pwd-page")
    public String loadResetPwdPage(@RequestParam("email") String email, Model model) {

        ResetPwdFormDTO resetPwdFormDTO = new ResetPwdFormDTO();
        resetPwdFormDTO.setEmail(email);

        model.addAttribute(AppConstants.RESET_PWD, resetPwdFormDTO);

        return AppConstants.RESET_PWD;
    }

    @PostMapping("/resetPwd")
    public String handleResetPwd(ResetPwdFormDTO resetPwdFormDTO, Model model) {

        boolean resetPwd = userService.resetPwd(resetPwdFormDTO);
        if(resetPwd) {
            return "redirect:/dashboard";
        }

        return AppConstants.RESET_PWD;
    }

}

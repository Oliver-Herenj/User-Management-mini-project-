package com.oliverIT.repo;


import com.oliverIT.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<UserEntity, Integer>{

    public UserEntity findByEmail(String email);

    public UserEntity findByEmailAndPwd(String emial, String pwd);

}

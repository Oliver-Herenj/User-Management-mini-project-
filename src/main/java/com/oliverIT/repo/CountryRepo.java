package com.oliverIT.repo;

import com.oliverIT.entity.CountryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CountryRepo extends JpaRepository<CountryEntity, Integer>{

}

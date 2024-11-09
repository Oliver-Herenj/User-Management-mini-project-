package com.oliverIT.repo;

import com.oliverIT.entity.StateEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StateRepo extends JpaRepository<StateEntity, Integer>{
    public List<StateEntity> findByCountryId(Integer countryId);
}

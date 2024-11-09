package com.oliverIT.repo;

import com.oliverIT.entity.CityEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CityRepo extends JpaRepository<CityEntity, Integer>{
    public List<CityEntity> findByStateId(Integer stateId);

}

package com.jason.baseballschedule.repositories;

import com.jason.baseballschedule.models.Dorm;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DormRepo extends CrudRepository<Dorm, Long> {
    List<Dorm> findAll();
}
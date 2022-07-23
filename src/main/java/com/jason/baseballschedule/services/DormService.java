package com.jason.baseballschedule.services;

import com.jason.baseballschedule.models.Dorm;
import com.jason.baseballschedule.repositories.DormRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DormService {
    private final DormRepo dormRepo;

    public DormService(DormRepo dormRepo) {
        this.dormRepo = dormRepo;
    }

    public List<Dorm> allDorms(){
        return dormRepo.findAll();
    }

    public Dorm addDorm(Dorm dorm) {
        return dormRepo.save(dorm);
    }

    public Dorm findDorm(Long id) {
        Optional<Dorm> optionalDorm = dormRepo.findById(id);
        if(optionalDorm.isPresent()) {
            return optionalDorm.get();
        }else {
            return null;
        }
    }
}
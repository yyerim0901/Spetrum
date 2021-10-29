package com.spectrum.service;

import com.spectrum.entity.Dogging;
import com.spectrum.repository.DoggingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class DoggingServiceImpl implements DoggingService{

    @Autowired
    private DoggingRepository doggingRepository;

    @Override
    public List<Dogging> MyDoggingList(Long userid){
        return doggingRepository.findByUserId(userid);
    }

    @Override
    public Dogging PostDogging(Dogging dogging){

        Date date = new Date();

        dogging.setDate(date);
        return doggingRepository.save(dogging);
    }

    @Override
    public void DeleteDogging(Long id){
        doggingRepository.deleteById(id);
    }

}

package com.spectrum.service;

import com.spectrum.entity.Dogging;
import com.spectrum.repository.DoggingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DoggingServiceImpl implements DoggingService{

    @Autowired
    private DoggingRepository doggingRepository;

    @Override
    public List<Dogging> MyDoggingList(Long id){
        return doggingRepository.findByUserId(id);
    }

}

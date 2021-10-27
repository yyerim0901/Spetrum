package com.spectrum.doggingTest;

import com.spectrum.entity.Dogging;
import com.spectrum.repository.DoggingRepository;
import com.spectrum.service.DoggingService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class DoggingTest {

    @Autowired
    private DoggingService doggingService;

    @Test
    public void postDogging(){
        Dogging dogging = new Dogging();
        dogging.setDistance(20);
        dogging.setTime(15);
        dogging.setLocation("대전광역시");
        dogging.setPicture("junit test data");

        doggingService.PostDogging(dogging);
    }

    @Test
    public void deleteDogging(){
        doggingService.DeleteDogging(2L);
    }
}

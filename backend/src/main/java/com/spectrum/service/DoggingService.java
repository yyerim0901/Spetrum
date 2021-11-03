package com.spectrum.service;

import com.spectrum.entity.Dogging;
import com.spectrum.entity.Path;

import java.util.List;

public interface DoggingService {

    public List<Dogging> MyDoggingList(String token);
    public void PostDogging(Dogging dogging, String token);
    public void DeleteDogging(Long id);
//    public List<Path> SaveDoggingPath(Float[] lats, Float[] lngs);

}

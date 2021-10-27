package com.spectrum.service;

import com.spectrum.entity.Dogging;

import java.util.List;

public interface DoggingService {

    public List<Dogging> MyDoggingList(Long id);
    public Dogging PostDogging(Dogging dogging);

}

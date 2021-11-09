package com.spectrum.service;

import com.spectrum.entity.Pet;

import java.util.Optional;

public interface PetService {

    public Pet AssignPet(int keyword, String token);

}

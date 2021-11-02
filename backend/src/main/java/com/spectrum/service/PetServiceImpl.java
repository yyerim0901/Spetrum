package com.spectrum.service;

import com.spectrum.entity.Pet;
import com.spectrum.repository.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class PetServiceImpl implements PetService {

    @Autowired
    private PetRepository petRepository;

    @Override
    public Pet AssignPet(int keyword){
        List<Pet> list = petRepository.findAllByKeyword(keyword);
        System.out.println(keyword);

        int size = 0;
        Random rand = new Random();
        Pet pet;

        if(list.isEmpty()){
            List<Pet> all = petRepository.findAll();
            size = list.size();
            pet = list.get(rand.nextInt(size));

        }else {
            size = list.size();
            pet = list.get(rand.nextInt(size));
        }
        System.out.println(pet.getId());
        return pet;
    }

}

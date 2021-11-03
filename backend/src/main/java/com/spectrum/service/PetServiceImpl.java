package com.spectrum.service;

import com.spectrum.common.jwt.JWTUtil;
import com.spectrum.entity.Pet;
import com.spectrum.entity.User;
import com.spectrum.repository.PetRepository;
import com.spectrum.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class PetServiceImpl implements PetService {

    @Autowired
    private PetRepository petRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JWTUtil jwtUtil;

    @Override
    public Pet AssignPet(int keyword, String token){
        List<Pet> list = petRepository.findAllByKeyword(keyword);

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
        //user 테이블에 펫피티아이 검사결과 점수 넣기
        String userId = jwtUtil.getUsername(token);
        Optional<User> userOptional = userRepository.findByUserId(userId);
        User user = userOptional.get();
        user.setPetpit(keyword);
        userRepository.save(user);
        
        return pet;
    }

}

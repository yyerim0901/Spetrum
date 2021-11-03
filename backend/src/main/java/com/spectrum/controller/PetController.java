package com.spectrum.controller;

import com.spectrum.entity.Pet;
import com.spectrum.service.PetService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Api(value = "펫피티아이 API", tags = {"PET_MBTI"})
@RestController
@RequestMapping("/api/petbti")
public class PetController {

    @Autowired
    private PetService petService;

    @ApiOperation(
            value = "유기동물 지정",
            notes = "점수 계산 후 나온 **keyword**를 통해 유기동물 배정"
    )
    @GetMapping
    public ResponseEntity<?> assignPet(@RequestParam int keyword){

        Pet pet = petService.AssignPet(keyword);

        if(pet == null){
            return new ResponseEntity<>("유기동물 배정 오류 발생", HttpStatus.NO_CONTENT);
        }else return new ResponseEntity<>(pet,HttpStatus.OK);

    }

}

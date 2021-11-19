package com.spectrum.petsitter;

import com.spectrum.common.request.PBoardPostReq;
import com.spectrum.service.PBoardService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

@SpringBootTest
public class PetSitterTest {

    @Autowired
    private PBoardService petSitterService;

    @Test
    public void postPetSitter(){
        PBoardPostReq petSitter = new PBoardPostReq();

        petSitter.setTitle("안녕");
        petSitter.setContent("안녕");
//        petSitter.setLat(5L);
//        petSitter.setLng(5L);

        MultipartFile multipartFile = new MultipartFile() {
            @Override
            public String getName() {
                return null;
            }

            @Override
            public String getOriginalFilename() {
                return null;
            }

            @Override
            public String getContentType() {
                return null;
            }

            @Override
            public boolean isEmpty() {
                return false;
            }

            @Override
            public long getSize() {
                return 0;
            }

            @Override
            public byte[] getBytes() throws IOException {
                return new byte[0];
            }

            @Override
            public InputStream getInputStream() throws IOException {
                return null;
            }

            @Override
            public void transferTo(File dest) throws IOException, IllegalStateException {

            }
        };
//        petSitterService.postPetSitter(petSitter, multipartFile);

    }



}

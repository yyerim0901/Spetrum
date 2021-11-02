package com.spectrum.service;

import com.spectrum.entity.SBoard;
import com.spectrum.entity.SBoardFile;
import com.spectrum.entity.User;
import com.spectrum.repository.SBoardFileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class FileHandler {

    @Autowired
    SBoardService sBoardService;
    @Autowired
    ResourceLoader resourceLoader;

    String BASE_PATH = new File("").getAbsolutePath() +"/src/main/resources/image/";

    public void deleteFile(User user, Long sboardid, Optional<List<SBoardFile>> photoList) {

        for (SBoardFile photo : photoList.get()){
            File file = new File(photo.getSave_file());
            file.delete();
        }
        String path = BASE_PATH + "sns/" + user.getId() + '/' + sboardid;
        System.out.println(path);
        File folder = new File(path);
        Boolean isOk = folder.delete();
        System.out.println(isOk);
    }

    public List<SBoardFile> parseFileInfo(List<MultipartFile> multipartFiles, User user, SBoard sboard) throws IOException {
        // 반환할 파일 리스트
        List<SBoardFile> fileList = new ArrayList<>();

        // 전달되어 온 파일이 존재할 경우
        if(!CollectionUtils.isEmpty(multipartFiles)) {
//            // 파일명을 업로드 한 날짜로 변환하여 저장
//            LocalDateTime now = LocalDateTime.now();
//            DateTimeFormatter dateTimeFormatter =
//                    DateTimeFormatter.ofPattern("yyyyMMdd");
//            String current_date = now.format(dateTimeFormatter);



            // 파일을 저장할 세부 경로 지정
            String boardpath = BASE_PATH + "sns/" + user.getId() +'/'+ sboard.getId();

            // 다중 파일 처리
            for(MultipartFile multipartFile : multipartFiles) {

                // 파일의 확장자 추출
                String originalFileExtension;
                String contentType = multipartFile.getContentType();

                // 확장자명이 존재하지 않을 경우 처리 x
                if(ObjectUtils.isEmpty(contentType)) {
                    break;
                }
                else {  // 확장자가 jpeg, png인 파일들만 받아서 처리
                    if(contentType.contains("image/jpeg"))
                        originalFileExtension = ".jpg";
                    else if(contentType.contains("image/png"))
                        originalFileExtension = ".png";
                    else  // 다른 확장자일 경우 처리 x
                        break;
                }
                String final_name = boardpath + '/' + multipartFile.getOriginalFilename();
                System.out.println(final_name +" final name@@@@@@@");
                // 파일 DTO 이용하여 Photo 엔티티 생성
                SBoardFile photo = new SBoardFile();
                photo.setSave_file(final_name);
                photo.setSBoard(sboard);

                // 생성 후 리스트에 추가
                fileList.add(photo);
                // 업로드 한 파일 데이터를 지정한 파일에 저장
                File file = new File(final_name);
                multipartFile.transferTo(file);

                // 파일 권한 설정(쓰기, 읽기)
                file.setWritable(true);
                file.setReadable(true);
            }
        }

        return fileList;
    }
}

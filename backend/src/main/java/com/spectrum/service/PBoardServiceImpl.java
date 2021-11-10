package com.spectrum.service;

import com.spectrum.common.jwt.JWTUtil;
import com.spectrum.common.request.PBoardPostReq;
import com.spectrum.common.request.PBoardUpdateReq;
import com.spectrum.entity.PBoard;
import com.spectrum.entity.User;
import com.spectrum.repository.PBoardRepository;
import com.spectrum.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.locationtech.jts.geom.Point;
//import org.locationtech.jts.io.WKTReader;
import org.locationtech.jts.io.WKTReader;
import org.qlrm.mapper.JpaResultMapper;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.geo.Point;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PBoardServiceImpl implements PBoardService {

    @Autowired
    private PBoardRepository pBoardRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JWTUtil jwtUtil;

    String BASE_PATH = "/var/lib/jenkins/workspace/PJT/backend/src/main/resources/";
//    String BASE_PATH = new File("").getAbsolutePath() + "/src/main/resources/";

    private final EntityManager em;

    @Override
    public List<PBoard> sortOfDistance() throws Exception{

        Double latitude = 35.20504260934892;
        Double longitude = 129.07708972645435;

        //현재 위치에서 가장 가까운 순으로 정렬 -> POINT(경도, 위도)를 현재위치로 잡으면 됨
        String sortOfDis = "ST_Distance_Sphere(POINT('"+longitude+"', '"+latitude+"'), point)";
        String nativeQuery = "select *, "+sortOfDis+" as distance\n" +
                "from pboard\n" +
                "where "+sortOfDis+"<15000\n"+ //15km이내
                "order by distance;";

        Query query = em.createNativeQuery(nativeQuery, PBoard.class);
        List<PBoard> list = query.getResultList();

        return list;
    }

    @Override
    public void postPetSitter(PBoardPostReq petSitterPostRequest, MultipartFile postPicture, String token) throws Exception {
        PBoard petSitter = new PBoard();

        String userId = jwtUtil.getUsername(token);
        Optional<User> userOptional = userRepository.findByUserId(userId);
        petSitter.setUser(userOptional.get());

        Date date = new Date();

        petSitter.setTitle(petSitterPostRequest.getTitle());
        petSitter.setContent(petSitterPostRequest.getContent());
        //위경도 프론트에서 가지고 오기
        Double latitude = petSitterPostRequest.getLat().doubleValue();
        Double longitude = petSitterPostRequest.getLng().doubleValue();
        String pointWKT = String.format("POINT(%s %s)", longitude, latitude);

        Point point = (Point) new WKTReader().read(pointWKT);
        petSitter.setPoint(point);

        petSitter.setCreated(date);
        petSitter.setStatus(0); //글 작성시에는 0으로 default

        PBoard tmppboard =  pBoardRepository.save(petSitter);

        String url = BASE_PATH + "image/petsitter/";
        url += userOptional.get().getId();
        File folder = new File(url);
        System.out.println(folder.exists());
        if(!folder.exists()) {
            boolean wasSuccessful = folder.mkdirs();
            // 디렉터리 생성에 실패했을 경우
            if(!wasSuccessful)
                System.out.println("file: was not successful");
        }
        folder.setWritable(true);
        folder.setReadable(true);

        if(postPicture != null) {
            url = url + "/" + tmppboard.getId() + "-" + postPicture.getOriginalFilename();
            System.out.println(url);
            File photo = new File(url);
            postPicture.transferTo(photo);
        }else{
            //이미지 없는 경우
            url = BASE_PATH + "image/petsitter/default.png";
        }
        tmppboard.setPicture(getShortFilePath(url));

        pBoardRepository.save(tmppboard);

    }

    //글 수정 시 권한 검사
    @Override
    public boolean checkWriterOfBoard(PBoardUpdateReq pBoardUpdateReq, String token){
        //status0인지 체크, 글id 필요
        Optional<PBoard> pBoardOptional = pBoardRepository.findById(pBoardUpdateReq.getId());
        PBoard pBoard = pBoardOptional.get();

        String userId = jwtUtil.getUsername(token);
        Optional<User> userOptional = userRepository.findByUserId(userId);
        User user = userOptional.get();

        if(pBoard.getUser().equals(user)) return true;
        else return false;
    }

    @Override
    public void updatePetSitter(PBoardUpdateReq petSitterUpdateReq, MultipartFile newPicture, String token) throws Exception {

        Long id = petSitterUpdateReq.getId();
        String userId = jwtUtil.getUsername(token);
        Optional<User> userOptional = userRepository.findByUserId(userId);
        Long user_pk = userOptional.get().getId();
        PBoard petSitter = pBoardRepository.getById(id);

        Date date = new Date();
        petSitter.setUpdated(date);

        petSitter.setTitle(petSitterUpdateReq.getTitle());
        petSitter.setContent(petSitterUpdateReq.getContent());

        Double latitude = petSitterUpdateReq.getLat().doubleValue();
        Double longitude = petSitterUpdateReq.getLng().doubleValue();
        String pointWKT = String.format("POINT(%s %s)", longitude, latitude);

        Point point = (Point) new WKTReader().read(pointWKT);
        petSitter.setPoint(point);

        String url = BASE_PATH + "image/petsitter/";
        if (!petSitter.getPicture().equals("image/petsitter/default.png")){
            String absolutepath = BASE_PATH + petSitter.getPicture();
            Path deleteFilePath = Paths.get(absolutepath);
            Files.deleteIfExists(deleteFilePath);
        }
        if(newPicture != null) {
            url += user_pk + "/" + petSitter.getId() + "-" + newPicture.getOriginalFilename();
            File photo = new File(url);
            newPicture.transferTo(photo);
        }else{
            //새로운 수정이미지가 없는 경우 그냥 이전 이미지 들고와서 사용
            url = BASE_PATH + "image/petsitter/default.png";
        }
        petSitter.setPicture(getShortFilePath(url));
        pBoardRepository.save(petSitter);

    }
    @Override
    public void deletePetSitter(Long petSitterId) throws IOException {
        PBoard petSitter = pBoardRepository.getById(petSitterId);
        if (!petSitter.getPicture().equals("image/petsitter/default.png")){
            String absolutepath = BASE_PATH + petSitter.getPicture();
            Path deleteFilePath = Paths.get(absolutepath);
            Files.deleteIfExists(deleteFilePath);
        }
        pBoardRepository.deleteById(petSitterId);
    }

    @Override
    public List<PBoard> myPetsitterList(String token){
        String userId = jwtUtil.getUsername(token.split(" ")[1]);
        Optional<User> userOptional = userRepository.findByUserId(userId);
        User user = userOptional.get();
        System.out.println(user.getId());
        return pBoardRepository.findAllByUserId(user.getId());
    }

    @Override
    public List<PBoard> allPetsitterList(float longitude, float latitude){

        String sortOfDis = "ST_Distance_Sphere(POINT('"+longitude+"', '"+latitude+"'), point)";
        String nativeQuery = "select *, "+sortOfDis+" as distance\n" +
                "from pboard\n" +
                "where "+sortOfDis+"<15000\n"+ //15km이내
                "order by distance;";

        Query query = em.createNativeQuery(nativeQuery, PBoard.class);
        List<PBoard> list = query.getResultList();

        return list;
    }

    @Override
    public com.spectrum.entity.PBoard detailOfPetsitter(Long petsitterId){
        Optional<com.spectrum.entity.PBoard> petOp = pBoardRepository.findById(petsitterId);
        return petOp.get();
    }

    private String getShortFilePath(String path) {
        int idx = path.indexOf("image");
        return path.substring(idx, path.length());
    }
}

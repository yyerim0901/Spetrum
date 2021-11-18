package com.spectrum.service;

import com.spectrum.entity.Dogging;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class CustomImage extends JPanel {

    static String BASE_PATH = "/var/lib/jenkins/workspace/PJT/backend/src/main/resources/image";
    static String LOGO_PATH = "/var/lib/jenkins/workspace/PJT/backend/src/main/resources/logo_image/white_logo.png";

    public static String customImage(MultipartFile multipartFile, Dogging dogging) throws IOException {
        //converting multipartfile to bufferedImage
        BufferedImage image = ImageIO.read(multipartFile.getInputStream());
        BufferedImage overlay = ImageIO.read(new FileInputStream(LOGO_PATH));
        
        //로고 이미지 크기 조절
        int h = image.getHeight()/15;
        double w = h*2.56; //512/200
        Image resizeImage = overlay.getScaledInstance((int)w,h,Image.SCALE_SMOOTH);

        Graphics g = image.getGraphics();
        //폰트 참고 : http://www.java2s.com/Code/Java/2D-Graphics-GUI/FontList.html
        Font font = new Font("Monospaced",Font.BOLD,40);
        g.setFont(font);
        //글자 색과 투명도 조절(R,G,B,transparent)
        Color myColor = new Color(255,255,255,200);
        g.setColor(myColor);

        int height = image.getHeight();
        int width = image.getWidth();

        //(넣을 글자 string, x좌표, y좌표)
        //위치 수동 조절해야 됨
        //지역
        String[] loc = dogging.getLocation().split(" ");
        String location = loc[0]+" "+loc[1];
        g.drawString(location,width/10,height-(height/5)+200);
        //소요 시간
        String time = dogging.getTime();
        g.drawString(time, width/10,height-(height/5)+100);
        //거리
        String distance = String.format("%.2f",dogging.getDistance());
        g.drawString(distance+"km",width/10,height-(height/5));

        //로고 이미지 붙이기
        image.getGraphics().drawImage(resizeImage,width-(int)(w+(w/2)),height/20,null);

        //현재 날짜 및 시간
        Timestamp today = new Timestamp(System.currentTimeMillis());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd hh:mm");
        g.drawString(sdf.format(today),width/10,height/12);

        g.dispose();

        //저장 위치 조절 & 저장되는 이름 사진 하나당 변경
        String userId = Long.toString(dogging.getUser().getId());
        String doggingId = Long.toString(dogging.getId());
        String fileName = userId+"_"+doggingId+".png";
        String path = BASE_PATH + "/dogging";

        //파일경로가 없으면 만드는 부분 만들어야 함!
//        System.out.println(path);
//        File check = new File(path);
//        System.out.println(check.exists());
//        if(!check.exists()) check.mkdirs();
//        //권한 오류 발생
//        check.setReadable(true);
//        check.setWritable(true);

//        Runtime.getRuntime().exec("chmod -R 777 " + path);

//        System.out.println(check.exists());
        path += "/"+ fileName;
//        \var\lib\jenkins\workspace\PJT\backend\src\main\resources\image\dogging\9\29
//        String path = "C:\\Users\\multicampus\\Desktop\\test1.png";

        ImageIO.write(image, "png", new FileOutputStream(path));
        return path;
    }
}

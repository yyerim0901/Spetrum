package com.spectrum.service;

import com.spectrum.common.request.DoggingPostReq;
import com.spectrum.common.response.DoggingDetailResponse;
import com.spectrum.entity.Dogging;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface DoggingService {

    public void saveUserInfo(String token);
    public List<Dogging> MyDoggingList() throws IOException;
    public DoggingDetailResponse DetailOfDogging(Long doggingId) throws IOException;
    public void PostDogging(DoggingPostReq doggingPostReq);
    public void customImage(MultipartFile multipartFile, Long doggingId)throws IOException;
    public void DeleteDogging(Long id);
    public void SaveDoggingPath(Long userId, Long doggingId, float[] lats, float[] lngs) throws IOException;
}

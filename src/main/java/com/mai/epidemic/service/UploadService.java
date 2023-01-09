package com.mai.epidemic.service;


import com.mai.epidemic.commons.Result;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface UploadService {


    Result uploadImages(MultipartFile img) throws IOException;
}

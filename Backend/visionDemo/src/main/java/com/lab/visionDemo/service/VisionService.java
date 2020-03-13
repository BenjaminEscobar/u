package com.lab.visionDemo.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * VisionService
 */
public interface VisionService {

    void obtenerTexto(MultipartFile file);
}
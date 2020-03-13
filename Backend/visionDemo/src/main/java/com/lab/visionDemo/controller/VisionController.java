package com.lab.visionDemo.controller;

import com.lab.visionDemo.model.Response;
import com.lab.visionDemo.service.VisionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * VisionController
 */
@CrossOrigin
@RestController
@RequestMapping("/vision")
public class VisionController {

    @Autowired
    private VisionService visionService;

    @GetMapping(value = "/gettext")
    public ResponseEntity<Response> getText(@RequestParam("file") MultipartFile file) {
        Response res = new Response();

        visionService.obtenerTexto(file);

        return new ResponseEntity<>(res, HttpStatus.OK);
    }

}
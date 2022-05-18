package com.mmenshikov.lyukinafashion.storage.controller;


import com.mmenshikov.lyukinafashion.storage.service.ImageServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileNotFoundException;

@RequestMapping("img")
@RestController
@RequiredArgsConstructor
public class ImageController {

    private final ImageServiceImpl imageService;

    @GetMapping(path = "", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public Resource get(@RequestParam String path) throws FileNotFoundException {
        return imageService.get(path);
    }
}

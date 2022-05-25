package com.mmenshikov.lyukinafashion.frontapi.controller;

import com.mmenshikov.lyukinafashion.frontapi.domain.dto.MainPageDto;
import com.mmenshikov.lyukinafashion.frontapi.service.MainPageService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("front")
public class MainController {

    private final MainPageService mainPageService;

    @GetMapping("main")
    public MainPageDto mainPage(@RequestParam Integer productsPage,
                                @RequestParam(required = false) Long categoryId) {
        return mainPageService.getMainPage(productsPage, categoryId);
    }
}

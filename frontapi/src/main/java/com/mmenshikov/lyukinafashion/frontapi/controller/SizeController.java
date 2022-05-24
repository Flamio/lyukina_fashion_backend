package com.mmenshikov.lyukinafashion.frontapi.controller;

import com.mmenshikov.lyukinafashion.domain.dto.SizeDto;
import com.mmenshikov.lyukinafashion.interfaces.SizeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("front/sizes")
public class SizeController {

    private final SizeService sizeService;

    @GetMapping("{id}")
    public SizeDto get(@PathVariable Long id) {return sizeService.get(id); }

    @GetMapping("/list/{ids}")
    public List<SizeDto> getList(@PathVariable List<Long> ids) {return sizeService.getList(ids); }
}

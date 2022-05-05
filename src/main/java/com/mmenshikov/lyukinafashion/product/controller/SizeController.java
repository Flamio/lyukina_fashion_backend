package com.mmenshikov.lyukinafashion.product.controller;

import com.mmenshikov.lyukinafashion.product.domain.dto.SizeDto;
import com.mmenshikov.lyukinafashion.product.service.SizeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;
import java.util.List;

@RestController
@RequestMapping("sizes")
@RequiredArgsConstructor
public class SizeController {

    private final SizeService sizeService;

    @GetMapping("{id}")
    public SizeDto get(@PathVariable Long id) {return sizeService.get(id); }

    @GetMapping("/list/{ids}")
    public List<SizeDto> getList(@PathVariable List<Long> ids) {return sizeService.getList(ids); }
}

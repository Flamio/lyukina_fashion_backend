package com.mmenshikov.lyukinafashion.product.service;

import com.mmenshikov.lyukinafashion.domain.dto.SizeDto;
import com.mmenshikov.lyukinafashion.domain.entity.Size;
import com.mmenshikov.lyukinafashion.product.repository.SizeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SizeService {
    private final SizeRepository sizeRepository;
    private final ConversionService conversionService;

    public SizeDto get(Long id) {
        final Size size = sizeRepository.getById(id);
        return conversionService.convert(size, SizeDto.class);
    }

    public List<SizeDto> getList(List<Long> ids) {
        return sizeRepository.findAllById(ids)
                .stream()
                .map((size -> conversionService.convert(size,SizeDto.class)))
                .collect(Collectors.toList());
    }
}

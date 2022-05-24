package com.mmenshikov.lyukinafashion.interfaces;

import com.mmenshikov.lyukinafashion.domain.dto.SizeDto;

import java.util.List;

public interface SizeService {
    SizeDto get(Long id);
    List<SizeDto> getList(List<Long> ids);
}

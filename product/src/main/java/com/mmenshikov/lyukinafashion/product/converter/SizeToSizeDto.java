package com.mmenshikov.lyukinafashion.product.converter;

import com.mmenshikov.lyukinafashion.domain.dto.SizeDto;
import com.mmenshikov.lyukinafashion.domain.entity.Size;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class SizeToSizeDto implements Converter<Size, SizeDto> {
    @Override
    public SizeDto convert(Size source) {
        return new SizeDto()
                .setName(source.getName())
                .setId(source.getId());
    }
}

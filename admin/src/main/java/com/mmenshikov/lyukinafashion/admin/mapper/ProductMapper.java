

package com.mmenshikov.lyukinafashion.admin.mapper;

import com.mmenshikov.lyukinafashion.admin.dto.ProductUpdateDto;
import com.mmenshikov.lyukinafashion.domain.dto.ProductDto;
import com.mmenshikov.lyukinafashion.domain.dto.ProductForm;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface ProductMapper {

  ProductForm toProductForm(ProductDto productDto);

  void update(@MappingTarget ProductForm productForm, ProductUpdateDto updateDto);
}

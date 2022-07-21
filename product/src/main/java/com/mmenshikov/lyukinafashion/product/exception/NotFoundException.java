package com.mmenshikov.lyukinafashion.product.exception;

import com.mmenshikov.lyukinafashion.domain.exception.BaseException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NotFoundException extends BaseException {

  public NotFoundException(final Long productId, final Long sizeId) {
    super("product with id " + productId + " and size id " + sizeId + " not found");
  }

  public NotFoundException(final Long id) {
    super("product with id " + id + " not found");
  }

  public NotFoundException(final String pageName) {
    super("product with pageName " + pageName + " not found");
  }
}

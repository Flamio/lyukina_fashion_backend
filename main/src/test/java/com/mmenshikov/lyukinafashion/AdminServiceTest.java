package com.mmenshikov.lyukinafashion;

import com.mmenshikov.lyukinafashion.admin.dto.ProductUploadDto;
import com.mmenshikov.lyukinafashion.admin.service.AdminService;
import com.mmenshikov.lyukinafashion.category.repository.CategoryRepository;
import com.mmenshikov.lyukinafashion.domain.dto.CategoryForm;
import com.mmenshikov.lyukinafashion.domain.entity.ProductObjectPurpose;
import com.mmenshikov.lyukinafashion.interfaces.ProductService;
import com.mmenshikov.lyukinafashion.product.repository.ProductRepository;
import com.mmenshikov.lyukinafashion.storage.repository.StorageObjectRepository;
import com.mmenshikov.lyukinafashion.storage.service.FileSystemService;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@SpringBootTest
@RunWith(SpringRunner.class)
@ActiveProfiles("test")
public class AdminServiceTest {

    @Autowired
    private AdminService adminService;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private StorageObjectRepository storageObjectRepository;

    @MockBean
    private FileSystemService fileSystemService;

    @Test
    @Transactional
    public void createCategoryTest() {
        var categoryForm = new CategoryForm()
                .setIsNew(true)
                .setName("test");

        adminService.addCategory(categoryForm);

        var categories = categoryRepository.findAll();

        Assertions.assertThat(categories.size()).isEqualTo(1);
        Assertions.assertThat(categories.get(0).getName()).isEqualTo("test");
        Assertions.assertThat(categories.get(0).getIsNew()).isTrue();
    }

    @Test
    @Transactional
    public void createProductTest() {

        var category = new CategoryForm()
                .setName("test")
                .setIsNew(true);

        var categoryId = adminService.addCategory(category);

        var thumbs = Stream.of(new MockMultipartFile("thumb",
                "hello.jpg",
                MediaType.APPLICATION_OCTET_STREAM_VALUE,
                "Hello, World!".getBytes())).map(mockMultipartFile -> (MultipartFile) mockMultipartFile)
                .collect(Collectors.toList());

        var bigs = Stream.of(new MockMultipartFile("big",
                "hello.jpg",
                MediaType.APPLICATION_OCTET_STREAM_VALUE,
                "Hello, World!".getBytes())).map(mockMultipartFile -> (MultipartFile)mockMultipartFile)
                .collect(Collectors.toList());

        var mainPic = new MockMultipartFile("main",
                "hello.jpg",
                MediaType.APPLICATION_OCTET_STREAM_VALUE,
                "Hello, World!".getBytes());

        var cartThumb = new MockMultipartFile("cartThumb",
                "hello.jpg",
                MediaType.APPLICATION_OCTET_STREAM_VALUE,
                "Hello, World!".getBytes());

        var productDto = new ProductUploadDto()
                .setCategoryId(categoryId)
                .setSizeIds(List.of(1L, 4L))
                .setDescription("test")
                .setPrice(BigDecimal.TEN)
                .setName("test")
                .setIsNew(true)
                .setPageName("test_product");

        adminService.uploadProduct(thumbs, bigs, mainPic, cartThumb, productDto);

        var products = productRepository.findAll();

        var storageObjects = storageObjectRepository.findAll();

        var thumbsObjects = storageObjects.stream()
                .filter(storageObject -> ProductObjectPurpose.CART_THUMB.equals(storageObject.getPurpose()))
                        .collect(Collectors.toList());

        Assertions.assertThat(products.size())
                .isEqualTo(1);

    }
}

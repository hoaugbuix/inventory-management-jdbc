package com.hoangbuix.dev.validate;

import com.hoangbuix.dev.entity.ProductInfoEntity;
import com.hoangbuix.dev.service.ProductInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;


@Component
public class ProductInfoValidator implements Validator {
    @Autowired
    private ProductInfoService productInfoService;

    @Override
    public boolean supports(Class<?> clazz) {
        return clazz == ProductInfoEntity.class;
    }

    @Override
    public void validate(Object target, Errors errors) {
        ProductInfoEntity productInfo = (ProductInfoEntity) target;
        ValidationUtils.rejectIfEmpty(errors, "code", "msg.required");
        ValidationUtils.rejectIfEmpty(errors, "name", "msg.required");
        ValidationUtils.rejectIfEmpty(errors, "description", "msg.required");
//        if (productInfo.getId() != null) {
//            ValidationUtils.rejectIfEmpty(errors, "multipartFile", "msg.required");
//        }
        if (productInfo.getCode() != null) {
            ProductInfoEntity results = productInfoService.findByCode(productInfo.getCode());
            if (results != null) {
                if (productInfo.getId() != null && productInfo.getId() != 0) {
                    if (results.getId() != productInfo.getId()) {
                        errors.rejectValue("code", "msg.code.exist");
                    }
                } else {
                    errors.rejectValue("code", "msg.code.exist");
                }

            }
        }
//        if (!productInfo.getMultipartFile().getOriginalFilename().isEmpty()) {
//            String extension = FilenameUtils.getExtension(productInfo.getMultipartFile().getOriginalFilename());
//            if (!extension.equals("jpg") && !extension.equals("png")) {
//                errors.rejectValue("multipartFile", "msg.file.extension.error");
//            }
//        }

    }
}
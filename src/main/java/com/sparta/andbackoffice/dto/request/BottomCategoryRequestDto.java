package com.sparta.andbackoffice.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BottomCategoryRequestDto {
    private Long middleCategoryId;
    private String categoryName;
}
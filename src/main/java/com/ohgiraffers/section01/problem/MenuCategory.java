package com.ohgiraffers.section01.problem;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class MenuCategory {
    private int menuCode;
    private String menuName;
    private int menuPrice;
    private Category category;
    private String orderableStatus;
}

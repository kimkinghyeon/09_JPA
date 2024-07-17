package com.ohgiraffers.section03.projection;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity(name="bidirection_category")
@Table(name="tbl_category")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

public class BiDirectionCategory {
    @Id
    @Column(name="category_code")
    private int categoryCode;
    @Column(name="category_name")
    private String categoryName;
    @Column(name="ref_category_code")
    private Integer refCategoryCode;
    @OneToMany(mappedBy = "category",fetch =  FetchType.EAGER)
    private List<BiDirectionMenu> menuList;

    @Override
    public String toString() {
        return "BiDirectionCategory{" +
                "refCategoryCode=" + refCategoryCode +
                ", categoryName='" + categoryName + '\'' +
                ", categoryCode=" + categoryCode +
                '}';
    }
}

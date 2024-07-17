package com.ohgiraffers.section08.namedquery;

import jakarta.persistence.*;
import lombok.*;

@Entity(name = "menu_section08")
@Table(name = "tbl_menu")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
// @NamedQueries 정적 쿼리를 정의하는데 사용되는 어노테이션
// JPQL 을 미리 엔티티 클래스에 정의를 해둠으로써 재사용성을 높일 수 있다.
@NamedQueries({
        @NamedQuery(
                name = "section08.namedquery.Menu.findAll",
                query = """
                        select m from menu_section08 m
                        """
        ),
        @NamedQuery(
                name = "section08.namedquery.Menu.findByMenuName",
                query = """
                        select
                        m
                        from menu_section08 m
                        where m.menuName = (select c.menuName
                                                from menu_section08 c
                                                where c.menuName = :menuName)
                        """
        ),
        @NamedQuery(
                name = "section08_namedquery.Menu.findByBab",
                query = """
                        select 
                        m
                        from menu_section08 m
                        where m.menuName like '%밥%'
                        """
        ),
        @NamedQuery(
                name = "section08_namedquery.Menu.findByMenuNameLike",
                query = """
                        select 
                        m
                        from menu_section08 m
                        where m.menuName like concat('%', :menuName ,'%') 
                        """
        )
})
public class Menu {
    @Id
    @Column(name = "menu_code")
    private int menuCode;
    @Column(name = "menu_name")
    private String menuName;
    @Column(name = "menu_price")
    private int menuPrice;
    @Column(name = "category_code")
    private int categoryCode;
    @Column(name = "orderable_status")
    private String orderableStatus;

}

package com.tdd.core.entity;

import com.tdd.core.constant.Constant;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.UuidGenerator;

@Entity
@Table(name = Constant.TABLE.CATEGORY_PRODUCT_TBL, indexes = {
        @Index(name = "TDD_CATEGORY_PRODUCT_PRODUCT_ID_INDEX", columnList = "PRODUCT_ID"),
        @Index(name = "TDD_CATEGORY_PRODUCT_CATEGORY_ID_INDEX", columnList = "CATEGORY_ID"),
})
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CategoryProduct extends SuperEntity {
    @Id
    @Column(name = "CATEGORY_PRODUCT_ID")
    @UuidGenerator
    private String productCategoryId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PRODUCT_ID")
    private Product product;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CATEGORY_ID")
    private Category category;

    // 1 sản phẩm có thể thuộc nhiều category, tuy nhiên 1 sản phẩm chỉ đc gán cho 1 category chính
    @Column(name = "IS_PRIMARY")
    private String isPrimary;
}

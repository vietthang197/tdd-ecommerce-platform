package com.tdd.core.entity;

import com.tdd.core.constant.Constant;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.UuidGenerator;
import org.hibernate.query.sql.internal.ParameterRecognizerImpl;

import java.util.Set;

@Entity
@Table(name = Constant.TABLE.PRODUCT_ASSET_TBL, indexes = {
        @Index(name = "TDD_PRODUCT_ASSET_PRODUCT_ID_INDEX", columnList = "PRODUCT_ID"),
        @Index(name = "TDD_PRODUCT_ASSET_FILE_ID_INDEX", columnList = "FILE_ID")
})
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductAsset extends SuperEntity {
    @Id
    @Column(name = "PRODUCT_ASSET_ID")
    @UuidGenerator
    private String productAssetId;

    // Đây có phải là ảnh đại diện chính của sản phẩm hay không Y/N
    @Column(name = "IS_PRIMARY", nullable = false)
    private String isPrimary;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="PRODUCT_ID", nullable=false)
    private Product product;

    // loại asset IMAGE / PDF / VIDEO
    @Column(name = "TYPE", nullable = false)
    private String type;

    // mã id của ms file
    @Column(name = "FILE_ID")
    private String fileId;

    @Column(name = "ALT_TEXT")
    private String altText;

    @Column(name = "TITLE")
    private String title;

    @OneToMany(cascade= CascadeType.ALL, mappedBy="productAsset", fetch = FetchType.LAZY)
    private Set<ProductAssetTag> productAssetTags;
}

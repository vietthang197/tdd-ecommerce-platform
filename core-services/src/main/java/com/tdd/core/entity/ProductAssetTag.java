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
@Table(name = Constant.TABLE.PRODUCT_ASSET_TAG_TBL, indexes = {
        @Index(name = "TDD_PRODUCT_ASSET_TAG_PRODUCT_ASSET_INDEX", columnList = "PRODUCT_ASSET_ID")
})
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductAssetTag extends SuperEntity{

    @Id
    @Column(name = "PRODUCT_ASSET_TAG_ID")
    @UuidGenerator
    private String productAssetTagId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="PRODUCT_ASSET_ID", nullable=false)
    private ProductAsset productAsset;
}

package com.tdd.catalog.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tdd.catalog.constant.Constant;
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
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.UuidGenerator;

@Entity
@Table(name = Constant.TABLE.PRODUCT_ASSET_TAG_TBL, indexes = {
        @Index(name = "TDD_PRODUCT_ASSET_TAG_PRODUCT_ASSET_INDEX", columnList = "PRODUCT_ASSET_ID")
})
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductAssetTag extends SuperEntity {

    @Id
    @Column(name = "PRODUCT_ASSET_TAG_ID")
    @UuidGenerator
    private String productAssetTagId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="PRODUCT_ASSET_ID", nullable=false)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @JsonIgnore
    private ProductAsset productAsset;
}

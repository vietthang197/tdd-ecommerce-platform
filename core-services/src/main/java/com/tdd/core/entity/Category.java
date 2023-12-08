package com.tdd.core.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tdd.core.constant.Constant;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.UuidGenerator;

import java.io.Serializable;
import java.time.LocalDateTime;

/*Đại diện cho category*/
@Entity
@Table(name = Constant.TABLE.CATEGORY_TBL, indexes = {
        @Index(name = "TDD_CATEGORY_URL_INDEX", columnList = "URL"),
        @Index(name = "TDD_CATEGORY_ACTIVE_START_DATE_INDEX", columnList = "ACTIVE_START_DATE"),
        @Index(name = "TDD_CATEGORY_ACTIVE_END_DATE_INDEX", columnList = "ACTIVE_END_DATE"),
        @Index(name = "TDD_CATEGORY_OVERRIDE_GENERATED_URL_INDEX", columnList = "OVERRIDE_GENERATED_URL"),
        @Index(name = "TDD_CATEGORY_PARENT_CATEGORY_ID_INDEX", columnList = "PARENT_CATEGORY_ID"),
})
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Category implements Serializable {

    @Id
    @Column(name = "CATEGORY_ID")
    @UuidGenerator
    private String categoryId;

    @Column(name = "ACTIVE_START_DATE", nullable = false)
    private LocalDateTime activeStartDate;

    @Column(name = "ACTIVE_END_DATE")
    private LocalDateTime activeEndDate;

    @Column(name = "ATTRIBUTES") // format JSON
    private String attributes;

    @Column(name = "NAME", nullable = false)
    private String name;

    @Column(name = "URL", length = 500)
    private String url;

    @Column(name = "DESCRIPTION", length = 500)
    private String description;

    @Column(name = "TAX_CODE")
    private String taxCode;

    @Column(name = "META_TITLE")
    private String metaTitle;

    @Column(name = "META_DESCRIPTION")
    private String metaDescription;

    @Column(name = "OVERRIDE_GENERATED_URL", length = 500)
    private String overrideGeneratedUrl;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "PARENT_CATEGORY_ID")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @JsonIgnore
    private Category parentCategory;
}

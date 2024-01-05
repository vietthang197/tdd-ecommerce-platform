package com.tdd.catalog.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tdd.catalog.constant.Constant;
import jakarta.persistence.CascadeType;
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
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.UuidGenerator;
import org.hibernate.type.SqlTypes;

import java.time.LocalDateTime;
import java.util.Map;

/*Đại diện cho category*/
@Entity
@Table(name = Constant.TABLE.CATEGORY_TBL, indexes = {
        @Index(name = "TDD_CATEGORY_URL_INDEX", columnList = "URL"),
        @Index(name = "TDD_CATEGORY_ACTIVE_START_DATE_INDEX", columnList = "ACTIVE_START_DATE"),
        @Index(name = "TDD_CATEGORY_ACTIVE_END_DATE_INDEX", columnList = "ACTIVE_END_DATE"),
        @Index(name = "TDD_CATEGORY_OVERRIDE_GENERATED_URL_INDEX", columnList = "OVERRIDE_GENERATED_URL"),
        @Index(name = "TDD_CATEGORY_PARENT_CATEGORY_ID_INDEX", columnList = "PARENT_CATEGORY_ID"),
        @Index(name = "TDD_CATEGORY_IS_DELETED_INDEX", columnList = "IS_DELETED")
})
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Category extends SuperEntity {
    public static final int URL_LENGTH = 1000;
    public static final int DESCIPTION_LENGTH = 1500;
    public static final int OVERRIDE_GENERATED_URL = 500;

    @Id
    @Column(name = "CATEGORY_ID")
    @UuidGenerator
    private String categoryId;

    @Column(name = "ACTIVE_START_DATE", nullable = false)
    private LocalDateTime activeStartDate;

    @Column(name = "ACTIVE_END_DATE")
    private LocalDateTime activeEndDate;

    @Column(name = "ATTRIBUTES") // format JSON
    @JdbcTypeCode(SqlTypes.JSON)
    private Map<String, String> attributes;

    @Column(name = "NAME", nullable = false)
    private String name;

    @Column(name = "URL", length = 1000, unique = true)
    private String url;

    @Column(name = "DESCRIPTION", length = 1500)
    private String description;

    @Column(name = "TAX_CODE")
    private String taxCode;

    @Column(name = "META_TITLE")
    private String metaTitle;

    @Column(name = "META_DESCRIPTION")
    private String metaDescription;

    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    @JoinColumn(name = "PARENT_CATEGORY_ID")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @JsonIgnore
    private Category parentCategory;

    @Column(name = "IS_DELETED")
    @ColumnDefault("'"+ Constant.STR_N +"'")
    private String isDeleted;
}

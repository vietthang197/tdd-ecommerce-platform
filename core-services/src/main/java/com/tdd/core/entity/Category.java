package com.tdd.core.entity;

import com.tdd.core.constant.Constant;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.UuidGenerator;

import java.io.Serializable;
/*Đại diện cho category*/
@Entity
@Table(name = Constant.TABLE.CATEGORY_TBL, indexes = {

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

    @Column(name = "NAME", nullable = false)
    private String name;

    @Column(name = "URL", length = 500)
    private String url;

    @Column(name = "OVERRIDE_GENERATED_URL", length = 500)
    private String overrideGeneratedUrl;
}

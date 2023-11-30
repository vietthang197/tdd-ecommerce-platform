package com.tdd.core.entity;

import com.tdd.core.constant.Constant;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.UuidGenerator;

import java.util.Set;

@Entity
@Table(name = Constant.TABLE.DATA_DRIVEN_ENUM_TBL, indexes = {

})
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DataDrivenEnum extends SuperEntity {

    @Id
    @Column(name = "DATA_DRIVEN_ENUM_ID")
    @UuidGenerator
    private String dataDrivenEnumId;

    // nhận các giá trị BRAND / MERCHANDISING_TYPE / TARGET_DEMOGRAPHIC
    @Column(name = "TYPE")
    private String type;

    @Column(name = "VALUE")
    private String value;

    @Column(name = "DISPLAY_VALUE")
    private String displayValue;

    @OneToMany(cascade= CascadeType.ALL, mappedBy="merchandisingType", fetch = FetchType.LAZY)
    public Set<Product> products;
}

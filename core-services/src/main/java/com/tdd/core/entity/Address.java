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

// Bảng lưu thông tin địa chỉ ship hàng của đơn hàng, map giữa đơn hàng và địa chỉ là fulfilment_group
@Entity
@Table(name = Constant.TABLE.ADDRESS_TBL, indexes = {
        @Index(name = "TDD_VARIANT_PRODUCT_ID_INDEX", columnList = "PRODUCT_ID")
})
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Address extends SuperEntity {
    @Id
    @Column(name = "ADDRESS_ID")
    @UuidGenerator
    private String categoryId;

    @Column(name = "ADDRESS_LINE", length = 2000)
    private String addressLine;

    @Column(name = "CITY", nullable = false)
    private String city;

    // Dành cho trường hợp sản phẩm bán online qua email như key phần mềm, ...
    @Column(name = "EMAIL_ADDRESS")
    private String emailAddress;

    // tên của
    @Column(name = "FIRST_NAME")
    private String firstName;

    @Column(name = "LAST_NAME")
    private String lastName;

    @Column(name = "FULL_NAME")
    private String fullName;

    @Column(name = "ACTIVE", nullable = false)  //Y/ N
    private String active;

    @Column(name = "STATE_PROVINCE")
    private String stateProvince;

    @Column(name = "PHONE")
    private String phone;
}

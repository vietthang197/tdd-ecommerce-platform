package com.tdd.core.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tdd.core.constant.Constant;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.UuidGenerator;

import java.util.Set;

// Bảng lưu thông tin địa chỉ ship hàng của đơn hàng, map giữa đơn hàng và địa chỉ là fulfilment_group
@Entity
@Table(name = Constant.TABLE.ADDRESS_TBL, indexes = {
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
    private String addressId;

    @Column(name = "ADDRESS_LINE", length = 2000)
    private String addressLine;

    // huyện
    @Column(name = "DISTRICT")
    private String district;

    // Tỉnh, Thành phố
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

    @Column(name = "PHONE")
    private String phone;
}

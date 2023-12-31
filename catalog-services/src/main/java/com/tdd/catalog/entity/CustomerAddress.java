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
@Table(name = Constant.TABLE.CUSTOMER_ADDRESS_TBL, indexes = {
        @Index(name = "TDD_CUSTOMER_ADDRESS_ADDRESS_ID_INDEX", columnList = "ADDRESS_ID"),
        @Index(name = "TDD_CUSTOMER_ADDRESS_CUSTOMER_ID_INDEX", columnList = "CUSTOMER_ID"),
})
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomerAddress extends SuperEntity {

    @Id
    @Column(name = "CUSTOMER_ADDRESS_ID")
    @UuidGenerator
    private String customerAddressId;

    @Column(name = "IS_DEFAULT_BILLING")
    private String isDefaultBilling;

    @Column(name = "IS_DEFAULT_SHIPPING")
    private String isDefaultShipping;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CUSTOMER_ID")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @JsonIgnore
    private Customer customer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ADDRESS_ID")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @JsonIgnore
    private Address address;

}

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

@Entity
@Table(name = Constant.TABLE.CUSTOMER_TBL, indexes = {

})
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Customer extends SuperEntity {

    @Id
    @Column(name = "CUSTOMER_ID")
    @UuidGenerator
    private String customerId;

    @Column(name = "EMAIL", nullable = false)
    private String email;

    @Column(name = "FIRST_NAME", nullable = false)
    private String firstName;

    @Column(name = "FULL_NAME", nullable = false)
    private String fullName;

    @Column(name = "LAST_NAME", nullable = false)
    private String lastName;

    // Y / N
    @Column(name = "IS_REGISTERED", nullable = false)
    private String isRegistered;

    // id của keycloak
    @Column(name = "EXTERNAL_ID")
    private String externalId;

    @Column(name = "USERNAME")
    private String username;
}

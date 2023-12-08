package com.tdd.core.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.UuidGenerator;

import java.util.Set;

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

    // KH đã đăng ký tài khoản hay chưa Y / N
    @Column(name = "IS_REGISTERED", nullable = false)
    private String isRegistered;

    // user id của keycloak
    @Column(name = "EXTERNAL_ID")
    private String externalId;

    // username của keycloak
    @Column(name = "USERNAME")
    private String username;
}

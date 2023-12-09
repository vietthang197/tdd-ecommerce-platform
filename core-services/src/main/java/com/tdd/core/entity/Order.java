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
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.UuidGenerator;
import org.hibernate.query.sql.internal.ParameterRecognizerImpl;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = Constant.TABLE.ORDER_TBL, indexes = {
        @Index(name = "TDD_ORDER_CART_ID_INDEX", columnList = "CART_ID"),
        @Index(name = "TDD_ORDER_CUSTOMER_ID_INDEX", columnList = "CUSTOMER_ID"),
        @Index(name = "TDD_ORDER_ORDER_NUMBER_INDEX", columnList = "ORDER_NUMBER"),
        @Index(name = "TDD_ORDER_SUBMIT_DATE_INDEX", columnList = "SUBMIT_DATE"),
})
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Order extends SuperEntity {
    @Id
    @Column(name = "ORDER_ID")
    @UuidGenerator
    private String orderId;

    // mã giỏ hàng của cart-services
    @Column(name = "CART_ID", nullable = false)
    private String cartId;

    @Column(name = "NAME")
    private String name;

    @Column(name = "STATUS")
    private String status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CUSTOMER_ID", nullable = false)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @JsonIgnore
    private Customer customer;

//    @Column(name = "CUSTOMER_NAME")
//    private String customerName;

    @Column(name = "EMAIL_ADDRESS")
    private String emailAddress;

    @Column(name = "ORDER_NUMBER", unique = true, nullable = false)
    private String orderNumber;

    @Column(name = "SUBMIT_DATE", nullable = false)
    private LocalDateTime submitDate;

    @Column(name = "CURRENCY", nullable = false)
    private String currency;

    @Column(name = "TOTAL_TAX")
    private BigDecimal totalTax;

    @Column(name = "TOTAL_SHIPPING")
    private BigDecimal totalShipping;

    @Column(name = "SUB_TOTAL")
    private BigDecimal subTotal;

    @Column(name = "ADJUSTMENTS_TOTAL")
    private BigDecimal adjustmentTotal;

    @Column(name = "FEES_TOTAL")
    private BigDecimal feesTotal;

    @Column(name = "TOTAL")
    private BigDecimal total;

    @Column(name = "ATTRIBUTES")
    @Lob
    private String attributes;

    @OneToMany(cascade= CascadeType.ALL, mappedBy="order", fetch = FetchType.LAZY)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @JsonIgnore
    private Set<OrderItem> orderItems;
}

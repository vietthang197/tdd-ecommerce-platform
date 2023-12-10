package com.tdd.cartservices.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Document
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class Cart implements Serializable {
    @Id
    private String carId;

    // Tên giỏ hàng
    private String name;

    // Trạng thái giỏ hàng https://developer.broadleafcommerce.com/services/cart-services/data-model
    private String status;

    // có thể null nếu như là guest cust, cust này được lưu ở core services
    private String customerId;

    private String username;

    private String fullName;

    // KH đã đăng ký acc hay chưa Y/ N
    private String isRegistered;

    // chắc mấy thông tin trên sau insert vào order luôn
    private String emailAddress;

    // mã order
    private String orderNumber;

    // ngày submit cart
    private LocalDateTime approvalRequestedDate;

    private String approverEmail;

    // ngày tạo cart, cart có thể tạo trước ngày approval
    private LocalDateTime createdDate;

    private LocalDateTime submitDate;

    private String currency;

    private BigDecimal totalTax;

    private BigDecimal totalShipping;

    private BigDecimal subTotal;

    private BigDecimal adjustmentTotal;

    private BigDecimal feeTotal;
}

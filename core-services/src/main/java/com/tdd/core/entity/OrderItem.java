package com.tdd.core.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tdd.core.constant.Constant;
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
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.UuidGenerator;

@Entity
@Table(name = Constant.TABLE.ORDER_ITEM_TBL, indexes = {
        @Index(name = "TDD_ORDER_ITEM_ORDER_ID_INDEX", columnList = "ORDER_ID")
})
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderItem extends SuperEntity {
    @Id
    @Column(name = "ORDER_ITEM_ID")
    @UuidGenerator
    private String orderId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ORDER_ID")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @JsonIgnore
    private Order order;
}

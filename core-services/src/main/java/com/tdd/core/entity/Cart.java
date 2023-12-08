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
@Table(name = Constant.TABLE.CART_TBL, indexes = {

})
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Cart extends SuperEntity {
    @Id
    @Column(name = "CART_ID")
    @UuidGenerator
    private String cartId;


}

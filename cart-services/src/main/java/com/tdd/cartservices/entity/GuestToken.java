package com.tdd.cartservices.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.HashIndexed;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.time.LocalDateTime;
/* Khi checkout cart thì sẽ sinh ra một guest token để còn làm việc với payment và bảo mật*/

@Document
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class GuestToken implements Serializable {
    @Id
    @HashIndexed
    private String tokenId;

    @Indexed
    private String cardId;

    @Indexed
    private LocalDateTime expireDate;

    private LocalDateTime createdDate;
}

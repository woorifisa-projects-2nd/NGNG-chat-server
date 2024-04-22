package com.ngng.chat.product.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TransactionRequestDTO {
    Long productId;
    Long sellerId;
    Long buyerId;
    Long price;
    Timestamp createdAt;
    Timestamp updatedAt;
    Boolean isAccepted;

}

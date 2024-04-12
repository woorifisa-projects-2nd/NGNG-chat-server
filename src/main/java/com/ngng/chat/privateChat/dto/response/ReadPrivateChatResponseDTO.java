package com.ngng.chat.privateChat.dto.response;

import lombok.*;

import java.sql.Timestamp;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class ReadPrivateChatResponseDTO {
    Long id;
    Long productId;
    String productName;
    String transactionStatus;
    String recentMessage;
    String thumbnailUrl;
    Long buyerId;
    String buyerName;
    String buyerNickname;
    Long sellerId;
    String sellerName;
    String sellerNickname;
    Timestamp createdAt;
    Timestamp updatedAt;

}

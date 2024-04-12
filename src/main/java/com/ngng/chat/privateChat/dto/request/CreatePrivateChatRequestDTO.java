package com.ngng.chat.privateChat.dto.request;

import lombok.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class CreatePrivateChatRequestDTO {
    Long sellerId;
    Long buyerId;
}

package com.ngng.chat.privateChat.dto.response;

import lombok.*;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class ReadAllPrivateMessageDTO {
    Long chatRoomId;
    ReadPrivateChatProductResponseDTO product;
    List<PrivateChatMessageDTO> messages;
}

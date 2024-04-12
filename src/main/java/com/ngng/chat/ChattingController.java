//package com.ngng.chat;
//
//import lombok.RequiredArgsConstructor;
//import org.springframework.messaging.handler.annotation.DestinationVariable;
//import org.springframework.messaging.handler.annotation.MessageMapping;
//import org.springframework.messaging.handler.annotation.SendTo;
//import org.springframework.stereotype.Controller;
//
//@Controller
//@RequiredArgsConstructor
//public class ChattingController {
//
//	private final ChatRepository chatRepository;
//	@MessageMapping("/{productId}")
//	@SendTo("/product/{productId}")
//	public Chat chatting(@DestinationVariable Long productId, Chat chat) throws Exception {
//		System.out.println(chat.getMessage());
//		System.out.println("product Id = " + productId);
//
//		return chatRepository.save(new Chat(productId, chat.getUserId(), chat.getMessage(), chat.getVisible()));
//	}
//
//}

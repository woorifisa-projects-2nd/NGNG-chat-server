package com.ngng.chat.privateChat.controller;

import com.ngng.chat.privateChat.dto.request.CreatePrivateChatRequestDTO;
import com.ngng.chat.privateChat.dto.response.ReadPrivateChatResponseDTO;
import com.ngng.chat.privateChat.entity.PrivateChat;
import com.ngng.chat.privateChat.service.PrivateChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/private-chats")
public class PrivateChatController {
    private final PrivateChatService privateChatService;

    @PostMapping(path = "/{productId}")
    public ResponseEntity<Long> create(@PathVariable Long productId, @RequestBody CreatePrivateChatRequestDTO reqeust){
        return ResponseEntity.ok(privateChatService.create(productId, reqeust));
    }

    @GetMapping(path = "/{userId}")
    public ResponseEntity<List<ReadPrivateChatResponseDTO>> readAllByUserId(@PathVariable Long userId){
        return ResponseEntity.ok(privateChatService.readAllByUserId(userId));
    }
}

package com.ngng.chat.publicChat.controller;

import com.ngng.chat.publicChat.dto.request.CreatePublicChatRequestDTO;
import com.ngng.chat.publicChat.dto.request.UpdatePublicChatRequestDTO;
import com.ngng.chat.publicChat.dto.response.ReadPublicChatResponseDTO;
import com.ngng.chat.publicChat.service.PublicChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/public-chats")
@RequiredArgsConstructor
public class PublicChatController {

    private final PublicChatService publicChatService;

    @MessageMapping("/{productId}")
    @SendTo("/product/{productId}")
    public ResponseEntity<ReadPublicChatResponseDTO> create(@DestinationVariable Long productId, CreatePublicChatRequestDTO request){
        return ResponseEntity.ok(publicChatService.create(productId, request));
    }

    @GetMapping(path = "/{productId}")
    public ResponseEntity<List<ReadPublicChatResponseDTO>> read(@PathVariable Long productId){
        return ResponseEntity.ok(publicChatService.readAllByProductId(productId));
    }

    @PutMapping(path = "/{productId}")
    public ResponseEntity<Long> update(@PathVariable Long productId, @RequestBody UpdatePublicChatRequestDTO request){
        return ResponseEntity.ok(publicChatService.update(productId, request));
    }

    @DeleteMapping(path = "/{productId}")
    public ResponseEntity delete(@PathVariable Long productId){
        return ResponseEntity.ok(publicChatService.delete(productId));
    }
}

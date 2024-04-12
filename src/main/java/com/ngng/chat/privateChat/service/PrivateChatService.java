package com.ngng.chat.privateChat.service;

import com.ngng.chat.product.entity.Product;
import com.ngng.chat.product.service.ProductService;
import com.ngng.chat.thumbnail.service.ThumbnailService;
import com.ngng.chat.user.entity.User;
import com.ngng.chat.privateChat.dto.request.CreatePrivateChatRequestDTO;
import com.ngng.chat.privateChat.dto.response.ReadPrivateChatResponseDTO;
import com.ngng.chat.privateChat.entity.PrivateChat;
import com.ngng.chat.privateChat.repository.PrivateChatRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PrivateChatService {
    private final PrivateChatRepository privateChatRepository;
    private final ThumbnailService thumbnailService;
    private final ProductService productService;

    public Long create(Long productId, CreatePrivateChatRequestDTO request){
        return privateChatRepository.save(PrivateChat
                        .builder()
                        .productId(productId)
                        .seller(User.builder().id(request.getSellerId()).build())
                        .buyer(User.builder().id(request.getBuyerId()).build())
                        .build())
                .getId();
    }

    public List<ReadPrivateChatResponseDTO> readAllByUserId(Long userId){
        return privateChatRepository.findAllByBuyerIdOrSellerId(userId, userId).orElseThrow()
                .stream().map(chat -> {
                    Product product = productService.read(chat.getProductId());
                    return ReadPrivateChatResponseDTO
                            .builder()
                            .id(chat.getId())
                            .sellerId(chat.getSeller().getId())
                            .sellerName(chat.getSeller().getName())
                            .sellerNickname(chat.getSeller().getNickname())
                            .buyerId(chat.getBuyer().getId())
                            .buyerName(chat.getBuyer().getName())
                            .buyerNickname(chat.getBuyer().getNickname())
                            .thumbnailUrl(thumbnailService.read(chat.getProductId()).getThumbnailUrl())
                            .productId(product.getId())
                            .productName(product.getTitle())
//                            .transactionStatus()
//                            .recentMessage()
                            .createdAt(chat.getCreatedAt())
                            .updatedAt(chat.getUpdatedAt())
                            .build();
                })
                .toList();
    }
}

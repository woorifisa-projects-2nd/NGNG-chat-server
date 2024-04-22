package com.ngng.chat.privateChat.service;

import com.ngng.chat.privateChat.dto.request.CreatePrivateChatMessageRequestDTO;
import com.ngng.chat.privateChat.dto.response.*;
import com.ngng.chat.privateChat.entity.PrivateChatMessage;
import com.ngng.chat.privateChat.entity.PrivateChatRoom;
import com.ngng.chat.privateChat.respository.PrivateChatMessageRepository;
import com.ngng.chat.privateChat.respository.PrivateChatRoomRepository;
import com.ngng.chat.product.dto.TransactionRequestDTO;
import com.ngng.chat.transaction.entity.TransactionDetails;
import com.ngng.chat.transaction.entity.repository.TransactionDetailsRepository;
import com.ngng.chat.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PrivateChatMessageService {
    private final PrivateChatMessageRepository privateChatMessageRepository;
    private final PrivateChatMessageReadService privateChatMessageReadService;
    private final PrivateChatRoomRepository privateChatRoomRepository;
    private final TransactionDetailsRepository transactionDetailsRepository;

    public PrivateChatMessageDTO create(CreatePrivateChatMessageRequestDTO message){

        PrivateChatMessage newMessage =  privateChatMessageRepository.save(
                PrivateChatMessage.builder()
                        .user(User.builder().id(message.getUserId()).build())
                        .message(message.getMessage())
                        .privateChatRoom(PrivateChatRoom.builder().id(message.getPrivateChatRoomId()).build())
                        .contentType(message.getContentType())
                        .build()
        );
        // 작성한 메시지까지 읽었다고 업데이트
        privateChatMessageReadService.update(message.getUserId(),message.getPrivateChatRoomId(), newMessage.getPrivateChatId());

        return PrivateChatMessageDTO.builder()
                .chatId(newMessage.getPrivateChatId())
                .message(newMessage.getMessage())
                .createdAt(newMessage.getCreatedAt())
                .user(UserDTO.builder()
                        .name(newMessage.getUser().getName())
                        .accountNumber(newMessage.getUser().getAccountNumber())
                        .accountBank(newMessage.getUser().getAccountBank())
                        .address(newMessage.getUser().getAddress())
                        .id(newMessage.getUser().getId())
                        .nickname(newMessage.getUser().getNickname())
                        .build())
                .contentType(newMessage.getContentType())
                .build();
    }

    public ReadAllPrivateMessageDTO readAllByPrivateChatRoomId(Long privateChatRoomId, Long userId){

        List<PrivateChatMessage> result = privateChatMessageRepository.findByPrivateChatRoomIdAndVisible(privateChatRoomId, true);
        PrivateChatMessage first = result.isEmpty() ? null :result.get(0);
        PrivateChatRoom chatRoom = privateChatRoomRepository.findById(privateChatRoomId).orElse(null);
        TransactionDetails details = transactionDetailsRepository.findByProductIdAndConsumerId(chatRoom.getProduct().getId(), chatRoom.getBuyer().getId()).orElse(null);
        ReadChatRoomTransactionDetails transactionDetails = details == null ? null : ReadChatRoomTransactionDetails.builder()
                .status(details.getStatus())
                .id(details.getId())
                .address(details.getAddress())
                .build();

        ReadPrivateChatProductResponseDTO product = chatRoom == null ? null :ReadPrivateChatProductResponseDTO.builder()
                .requests(chatRoom.getProduct().getRequestList().stream().map(transactionRequest -> TransactionRequestDTO.builder()
                        .buyerId(transactionRequest.getBuyer().getId())
                        .productId(transactionRequest.getProduct().getId())
                        .isAccepted(transactionRequest.getIsAccepted())
                        .updatedAt(transactionRequest.getUpdatedAt())
                        .createdAt(transactionRequest.getCreatedAt())
                        .price(transactionRequest.getPrice())
                        .sellerId(transactionRequest.getSeller().getId())
                        .build()).toList())
                .productId(chatRoom.getProduct().getId())
                .productTitle(chatRoom.getProduct().getTitle())
                .price(chatRoom.getProduct().getPrice())
                .isEscrow(chatRoom.getProduct().getIsEscrow())
                .discountable(chatRoom.getProduct().getDiscountable())
                .thumbnailUrl(chatRoom.getProduct().getThumbnail().getThumbnailUrl())
                .transactionDetails(transactionDetails)
                .seller(UserDTO.builder()
                        .id(chatRoom.getSeller().getId())
                        .nickname(chatRoom.getSeller().getNickname())
                        .address(chatRoom.getSeller().getAddress())
                        .accountBank(chatRoom.getSeller().getAccountBank())
                        .accountNumber(chatRoom.getSeller().getAccountNumber())
                        .name(chatRoom.getSeller().getName())
                        .build())
                .buyer(UserDTO.builder()
                        .id(chatRoom.getBuyer().getId())
                        .nickname(chatRoom.getBuyer().getNickname())
                        .address(chatRoom.getBuyer().getAddress())
                        .accountBank(chatRoom.getBuyer().getAccountBank())
                        .accountNumber(chatRoom.getBuyer().getAccountNumber())
                        .name(chatRoom.getBuyer().getName())
                        .build())
                .build();
        List<PrivateChatMessageDTO> data =  result.stream().map(msg ->
             PrivateChatMessageDTO.builder()
                    .chatId(msg.getPrivateChatId())
                    .message(msg.getMessage())
                    .contentType(msg.getContentType())
                    .createdAt(msg.getCreatedAt())
                    .user(UserDTO.builder()
                            .accountBank(msg.getUser().getAccountBank())
                            .accountNumber(msg.getUser().getAccountNumber())
                            .name(msg.getUser().getName())
                            .address(msg.getUser().getAddress())
                            .nickname(msg.getUser().getNickname())
                            .id(msg.getUser().getId())
                            .build())
                    .build()
        ).toList();
        if(!result.isEmpty()){
            PrivateChatMessage recentMessage = result.stream().max(Comparator.comparingLong(PrivateChatMessage::getPrivateChatId)).get();
            // 지금 가져온 메시지까지 읽었다고 업데이트 해주기
            privateChatMessageReadService.update(userId,privateChatRoomId, recentMessage.getPrivateChatId());

        }
      return ReadAllPrivateMessageDTO.builder()
              .chatRoomId(privateChatRoomId)
              .product(product).messages(data).build();

 }

    Long updateMessageVisibility (Long privateChatMessageId){
        PrivateChatMessage target = privateChatMessageRepository.findById(privateChatMessageId).orElse(null);
        if(target != null){
            target.setVisible(false);
            return privateChatMessageRepository.save(target).getPrivateChatId();
        }else{
            return -1L;
        }
 }
}

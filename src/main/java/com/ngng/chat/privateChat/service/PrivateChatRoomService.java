package com.ngng.chat.privateChat.service;

import com.ngng.chat.privateChat.dto.request.CreatePrivateChatRoomRequestDTO;
import com.ngng.chat.privateChat.dto.response.*;
import com.ngng.chat.privateChat.entity.PrivateChatMessage;
import com.ngng.chat.privateChat.entity.PrivateChatMessageRead;
import com.ngng.chat.privateChat.entity.PrivateChatRoom;
import com.ngng.chat.privateChat.respository.PrivateChatMessageReadRepository;
import com.ngng.chat.privateChat.respository.PrivateChatMessageRepository;
import com.ngng.chat.privateChat.respository.PrivateChatRoomRepository;
import com.ngng.chat.product.entity.Product;
import com.ngng.chat.transaction.entity.TransactionDetails;
import com.ngng.chat.user.entity.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class PrivateChatRoomService {
    private final PrivateChatRoomRepository privateChatRoomRepository;
    private final PrivateChatMessageRepository privateChatMessageRepository;
    private final PrivateChatMessageReadRepository privateChatMessageReadRepository;
    public Long create(CreatePrivateChatRoomRequestDTO request){

        Long chatRoomId =  privateChatRoomRepository.save(PrivateChatRoom.builder()
                        .product(Product.builder().id(request.getProductId()).build())
                        .seller(User.builder().id(request.getSellerId()).build())
                        .buyer(User.builder().id(request.getBuyerId()).build())
                .build()).getId();
        // 메시지 얼마나 읽었는지 기록 추가: 구매자
        privateChatMessageReadRepository.save(PrivateChatMessageRead.builder()
                        .privateChatRoom(PrivateChatRoom.builder().id(chatRoomId).build())
                        .user(User.builder().id(request.getSellerId()).build())
                .build());
        // 메시지 얼마나 읽었는지 기록 추가: 판매자
        privateChatMessageReadRepository.save(PrivateChatMessageRead.builder()
                .privateChatRoom(PrivateChatRoom.builder().id(chatRoomId).build())
                .user(User.builder().id(request.getBuyerId()).build())
                .build());
        return chatRoomId;
    }

    public Long findByProductIdAndBuyerId(Long productId, Long buyerId){
        PrivateChatRoom result = privateChatRoomRepository.findByProductIdAndBuyerId(productId, buyerId).orElse(null);
        if(result == null ){
            return -1L;
        }else{
            return result.getId();
        }
    }

    public List<ReadPrivateChatRoomResponseDTO> readAllChatRoom(Long userId){
        return privateChatRoomRepository.findAllBySellerIdOrBuyerId(userId, userId).stream().map(data -> {
            // 해당 채팅방에서 안 읽은 메시지가 몇 개인지 구하는 함수 호출
            Long unreadMessageCount;
            RecentMessageDTO recentMessage;
            PrivateChatMessage readMessage = privateChatMessageReadRepository.findByPrivateChatRoomIdAndUserId(data.getId(), userId).getPrivateChatMessage();
            List<PrivateChatMessage> recentMessages;

            if(readMessage == null) {
                log.info("일긍메시지 null");
                recentMessages= privateChatMessageRepository.findAllByPrivateChatRoomIdAndPrivateChatIdGreaterThanEqual(data.getId(), -1L);
                unreadMessageCount = 0L;
               
            }else{
                log.info("PrivateChatRoomId " +data.getId() +" PrivateChatId "+ readMessage.getPrivateChatId());


                recentMessages = privateChatMessageRepository.findAllByPrivateChatRoomIdAndPrivateChatIdGreaterThanEqual(data.getId(), readMessage.getPrivateChatId() ==null?-1L:readMessage.getPrivateChatId());
                unreadMessageCount = Long.parseLong(Integer.toString(recentMessages.size()));
            }
            log.info("recentMessag", recentMessages);
            PrivateChatMessage temp = recentMessages.stream().max(Comparator.comparingLong(PrivateChatMessage::getPrivateChatId)).orElse(null);
            log.info("temp", temp);
            if(temp == null) {
                recentMessage = null;
            }else{
                recentMessage = RecentMessageDTO.builder()
                        .message(temp.getMessage())
                        .createdAt(temp.getCreatedAt())
                        .contentType(temp.getContentType())
                        .build();
            }
            return ReadPrivateChatRoomResponseDTO.builder()
                    .recentMessage(recentMessage)
                    .product(ChatRoomProductDTO.builder()
                            .productId(data.getProduct().getId())
                            .productThumbnailUrl(data.getProduct().getThumbnail().getThumbnailUrl())
                            .productTitle(data.getProduct().getTitle())
                            .build())
                    .privateChatRoomId(data.getId())
                    .createdAt(data.getCreatedAt())
                    .buyer(ReadChatRoomUserResponseDTO.builder()
                            .id(data.getBuyer().getId())
                            .name(data.getBuyer().getName())
                            .nickname(data.getBuyer().getNickname())
                            .build())
                    .seller(ReadChatRoomUserResponseDTO.builder()
                            .id(data.getSeller().getId())
                            .name(data.getSeller().getName())
                            .nickname(data.getSeller().getNickname())
                            .build())
                    .unreadMessageCount(unreadMessageCount)
                    .build();
        }).toList();
    }
}
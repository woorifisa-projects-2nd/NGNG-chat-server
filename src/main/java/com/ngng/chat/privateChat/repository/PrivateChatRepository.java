package com.ngng.chat.privateChat.repository;

import com.ngng.chat.privateChat.entity.PrivateChat;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

public interface PrivateChatRepository extends JpaRepository<PrivateChat, Long> {

    public Optional<List<PrivateChat>> findAllByBuyerIdOrSellerId(Long buyerId, Long sellerId);
}

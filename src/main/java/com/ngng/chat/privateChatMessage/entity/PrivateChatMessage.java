package com.ngng.chat.privateChatMessage.entity;

import com.ngng.chat.user.entity.User;
import com.ngng.chat.privateChat.entity.PrivateChat;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;

import java.sql.Timestamp;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name = "private_chat_message")
@DynamicInsert
public class PrivateChatMessage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "chat_id")
    private Long id;


    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "message")
    private String message;

    @ColumnDefault("true")
    private Boolean visible;

    @ManyToOne
    @JoinColumn(name = "private_chat_id")
    private PrivateChat privateChatId;

    @CreationTimestamp
    @Column(name = "created_at")
    private Timestamp createdAt;

    @Column(name = "updated_at")
    private Timestamp updatedAt;
}

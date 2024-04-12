//package com.ngng.chat;
//
//import jakarta.persistence.*;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import org.antlr.v4.runtime.misc.NotNull;
//import org.hibernate.annotations.ColumnDefault;
//import org.hibernate.annotations.CreationTimestamp;
//import org.hibernate.annotations.DynamicInsert;
//
//import java.sql.Timestamp;
//
//@NoArgsConstructor
//@Getter
//@Entity
//@Table(name="public_chat")
//@DynamicInsert
//public class Chat {
//    @Id
//    @Column(name = "public_chat_id")
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    @Column(name = "product_id")
//    @NotNull
//    private Long productId;
//
//    @Column(name = "user_id")
//    @NotNull
//    private Long userId;
//
//    @Column(name = "message")
//    @ColumnDefault("")
//    @NotNull
//    private String message;
//
//    @ColumnDefault("true")
//    @NotNull
//    private Boolean visible;
//
//    @Column(name = "created_at")
//    @CreationTimestamp
//    private Timestamp createdAt;
//
//    public Chat(Long productId, Long userId, String message, Boolean visible){
//        this.productId = productId;
//        this.userId = userId;
//        this.message = message;
//        this.visible = visible;
//
//    }
//
//}

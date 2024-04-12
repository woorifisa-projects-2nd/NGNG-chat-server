package com.ngng.chat.product.entity;

import com.ngng.chat.thumbnail.entity.Thumbnail;
import com.ngng.chat.user.entity.User;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;
import java.sql.Timestamp;
import jakarta.persistence.Table;


@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name = "product")
@DynamicInsert
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Long id;
    private String title;
    private String content;
    private Long price;

    @ColumnDefault("true")
    @Column(name = "is_escrow")
    private Boolean isEscrow;

    @ColumnDefault("false")
    private Boolean discountable;

    @Column(name = "purchase_at")
    private String purchaseAt;

    @ColumnDefault("true")
    @Column(name = "for_sale")
    private Boolean forSale;

    @ColumnDefault("true")
    private Boolean visible;

    @ColumnDefault("false")
    @Column(name = "free_shipping")
    private Boolean freeShipping;

    @Column(name = "refreshed_at")
    private Timestamp refreshedAt;

    @CreationTimestamp
    @Column(name = "created_at")
    private Timestamp createdAt;

    @Column(name = "updated_at")
    private Timestamp updatedAt;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

    @OneToOne(mappedBy = "product")
    private Thumbnail thumbnail;

}

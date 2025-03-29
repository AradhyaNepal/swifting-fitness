package com.a2.swifting_fitness.features.notification.entity;

import com.a2.swifting_fitness.features.auth.entity.Users;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "UserNotification")
public class UserNotification {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String image;
    private String title;
    private  String description;
    private  String onClickedGoTo;
    private  String onClickedGoToData;
    private boolean onClickGoToWeb;
    private String body;
    private String topic;
    private Instant createdAt;
   private  boolean seen;


    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private Users userId;



    @ManyToOne
    @JoinColumn(name = "ui_navigation_id", nullable = false)
    private UINavigationPath uiNavigationPath;

}

package cmc.cmc15th_hackathon.domain.review.entity;

import cmc.cmc15th_hackathon.domain.user.entity.User;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private User user;
    private Double latitude;
    private Double longitude;
    private String locationName;
    private String address;
    private Double score;
    private String content;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;




}

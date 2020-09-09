package dev.euchigere.facebookclonewithspring.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

/**
 * post model
 * Has many to one relationship with user
 * Has one to many relationship with comment and postLike
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String text;
    private int noOfLikes = 0;
    private int noOfComments = 0;
    private LocalDateTime createdAt;

    @ManyToOne
    private User user;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> comments;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PostLike> postLikes;
}

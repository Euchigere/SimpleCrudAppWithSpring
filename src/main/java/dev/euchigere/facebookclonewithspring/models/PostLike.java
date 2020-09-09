package dev.euchigere.facebookclonewithspring.models;

import lombok.Data;

import javax.persistence.*;

/**
 * postLike model
 * Has many to one relationship with post and user
 */
@Data
@Entity
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"post_id", "user_id"})})
public class PostLike {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "post_id", nullable = false)
    private Post post;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}

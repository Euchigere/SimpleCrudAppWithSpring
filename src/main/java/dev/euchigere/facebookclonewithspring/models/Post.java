package dev.euchigere.facebookclonewithspring.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
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
    private LocalDateTime createdAt;

    @ManyToOne
    private User user;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> comments;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PostLike> postLikes;

    public String getDuration() {
        if (createdAt == null) {
            return "";
        }

        long duration;
        if ((duration = ChronoUnit.YEARS.between(createdAt, LocalDateTime.now())) > 1) {
            return duration + " years ago";
        } else if ((duration = ChronoUnit.YEARS.between(createdAt, LocalDateTime.now())) == 1) {
            return "a year ago";
        } else if ((duration = ChronoUnit.MONTHS.between(createdAt, LocalDateTime.now())) > 1) {
            return duration + " months ago";
        } else if ((duration = ChronoUnit.MONTHS.between(createdAt, LocalDateTime.now())) == 1) {
            return "a month ago";
        } else if ((duration = ChronoUnit.DAYS.between(createdAt, LocalDateTime.now())) > 1) {
            return  duration + " days ago";
        } else if ((duration = ChronoUnit.DAYS.between(createdAt, LocalDateTime.now())) == 1) {
            return "a day ago";
        } else if ((duration = ChronoUnit.HOURS.between(createdAt, LocalDateTime.now())) > 1) {
            return duration + " hours ago";
        } else if ((duration = ChronoUnit.HOURS.between(createdAt, LocalDateTime.now())) == 1) {
            return "an hour ago";
        } else if ((duration = ChronoUnit.MINUTES.between(createdAt, LocalDateTime.now())) > 1) {
            return duration + " minutes ago";
        } else {
            return "a minute ago";
        }
    }
}

package dev.euchigere.facebookclonewithspring.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@Entity(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank(message = "First name is mandatory")
    private String firstName;

    @NotBlank(message = "Last name is mandatory")
    private String lastName;

    @NotBlank(message = "Gender is mandatory")
    private String gender;

    @NotBlank(message = "Email is mandatory")
    @Column(unique = true)
    private String email;

    @NotBlank(message = "Birth date is mandatory")
    private LocalDate dob;

    @NotBlank(message = "Password is mandatory")
    private String password;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Post> posts;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PostLike> postLikes;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> comments;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CommentLike> commentLikes;

    public User(String firstName, String lastName, String gender, String dob, String email, String password) {
        this.firstName = firstName.toLowerCase();
        this.lastName = lastName.toLowerCase();
        this.gender = gender.toLowerCase();
        this.dob = LocalDate.parse(dob);
        this.email = email.toLowerCase();
        this.password = password;
    }

    public void setDob(String dob) {
        this.dob = LocalDate.parse(dob);
    }
}

package ua.duikt.learning.java.pro.spring.individualfourthsprint.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mykyta Sirobaba on 20.01.2026.
 * email mykyta.sirobaba@gmail.com
 */
@Data
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;

    // TODO: Add the @OneToMany annotation
    // Set mappedBy = "author" and cascade = CascadeType.ALL
    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL)
    @ToString.Exclude
    private List<Post> posts = new ArrayList<>();

    public void addPost(Post post) {
        posts.add(post);
        post.setAuthor(this);
    }

    public User(String username) {
        this.username = username;
    }
}


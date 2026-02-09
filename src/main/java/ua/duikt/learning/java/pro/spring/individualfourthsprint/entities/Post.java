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
@Table(name = "posts")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;

    // TODO: Add @ManyToOne and @JoinColumn(name = "user_id")
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User author;

    // TODO: Add @ManyToMany
    // TODO: Add @JoinTable with name "post_tags"
    @ManyToMany
    @JoinTable(
            name = "post_tags",
            joinColumns = @JoinColumn(name = "post_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id")
    )
    private List<Tag> tags = new ArrayList<>();

    public void addTag(Tag tag) {
        tags.add(tag);
        tag.getPosts().add(this);
    }

    public Post(String title) {
        this.title = title;
    }
}


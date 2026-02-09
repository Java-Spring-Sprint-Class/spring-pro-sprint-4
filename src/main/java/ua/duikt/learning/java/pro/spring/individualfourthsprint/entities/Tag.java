package ua.duikt.learning.java.pro.spring.individualfourthsprint.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mykyta Sirobaba on 20.01.2026.
 * email mykyta.sirobaba@gmail.com
 */
@Data
@NoArgsConstructor
@Entity
@Table(name = "tags")
public class Tag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    // TODO: Add @ManyToMany(mappedBy = "tags")
    @ManyToMany(mappedBy = "tags")
    private List<Post> posts = new ArrayList<>();

    public Tag(String name) {
        this.name = name;
    }
}

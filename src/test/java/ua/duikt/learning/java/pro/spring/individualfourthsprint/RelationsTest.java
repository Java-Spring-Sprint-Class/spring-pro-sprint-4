package ua.duikt.learning.java.pro.spring.individualfourthsprint;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.transaction.annotation.Transactional;
import ua.duikt.learning.java.pro.spring.individualfourthsprint.entities.Post;
import ua.duikt.learning.java.pro.spring.individualfourthsprint.entities.Tag;
import ua.duikt.learning.java.pro.spring.individualfourthsprint.entities.User;
import ua.duikt.learning.java.pro.spring.individualfourthsprint.repositories.PostRepository;
import ua.duikt.learning.java.pro.spring.individualfourthsprint.repositories.TagRepository;
import ua.duikt.learning.java.pro.spring.individualfourthsprint.repositories.UserRepository;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by Mykyta Sirobaba on 20.01.2026.
 * email: mykyta.sirobaba@gmail.com
 */
@DataJpaTest
@Transactional
class RelationsTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private TagRepository tagRepository;

    @Test
    @DisplayName("1. One-to-Many: Author saves posts successfully")
    void testOneToManyCascade() {
        User user = new User("Alice");
        Post post1 = new Post("Spring Data Intro");
        Post post2 = new Post("Hibernate Tips");

        user.addPost(post1);
        user.addPost(post2);

        userRepository.save(user);

        List<Post> posts = postRepository.findAll();
        assertEquals(2, posts.size(), "Posts should be saved cascade together with the user");
        assertNotNull(posts.get(0).getAuthor(), "Each post should have an author assigned");
    }

    @Test
    @DisplayName("2. Many-to-One: Post knows its author")
    void testManyToOne() {
        User user = new User("Bob");
        userRepository.save(user);

        Post post = new Post("JPA Relations");
        post.setAuthor(user);
        postRepository.save(post);

        Post fetchedPost = postRepository.findById(post.getId()).orElseThrow();
        assertEquals("Bob", fetchedPost.getAuthor().getUsername(), "Post should reference the correct author");
    }

    @Test
    @DisplayName("3. Many-to-Many: Posts and Tags linkage")
    void testManyToMany() {
        Post post = new Post("Advanced Java");
        Tag tag1 = new Tag("Coding");
        Tag tag2 = new Tag("Education");

        tagRepository.save(tag1);
        tagRepository.save(tag2);

        post.addTag(tag1);
        post.addTag(tag2);
        postRepository.save(post);

        Post fetchedPost = postRepository.findById(post.getId()).orElseThrow();
        assertEquals(2, fetchedPost.getTags().size(), "Post should have 2 tags");

        Tag fetchedTag = tagRepository.findById(tag1.getId()).orElseThrow();
        assertEquals(1, fetchedTag.getPosts().size(), "Tag should know which post it belongs to");
        assertEquals("Advanced Java", fetchedTag.getPosts().get(0).getTitle());
    }
}

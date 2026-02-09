# Homework: Spring Data JPA Relations

## Task Description
In this assignment, you will create a data model for a blogging platform. You need to configure relationships between entities using JPA annotations.

## Goals
- Implement a **One-to-Many** relationship (one user can have many posts).  
- Implement a **Many-to-One** relationship (many posts belong to one author).  
- Implement a **Many-to-Many** relationship (a post can have many tags, and a tag can belong to many posts).  
- Understand the `cascade` and `mappedBy` parameters.  

## Technical Requirements

### 1. User Entity (Author)
- Fields: `id`, `username`.  
- List of posts: `List<Post> posts`.  
- Task: Add the `@OneToMany` annotation. Use `mappedBy` to indicate that the owner of the relationship is the `Post` class.  
- Set `cascade = CascadeType.ALL` so that when a user is deleted, all their posts are also deleted.  

### 2. Post Entity
- Fields: `id`, `title`, `content`.  
- Author field: `User author`.  
  - Task: Add `@ManyToOne` and `@JoinColumn`.  
- List of tags: `List<Tag> tags`.  
  - Task: Add `@ManyToMany`. Configure the join table using `@JoinTable`.  

### 3. Tag Entity
- Fields: `id`, `name`.  
- List of posts: `List<Post> posts`.  
- Task: Add `@ManyToMany`. Don’t forget `mappedBy` since the relationship is already configured in `Post`.  

## Important: toString() Problem
When generating `toString()` methods (manually or using Lombok) for **bidirectional relationships**, an infinite recursion can occur:

```

User prints posts -> Posts print User -> ... -> StackOverflowError

```

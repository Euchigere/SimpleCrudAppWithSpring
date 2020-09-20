package dev.euchigere.facebookclonewithspring.service;

import dev.euchigere.facebookclonewithspring.dto.ResponseObject;
import dev.euchigere.facebookclonewithspring.models.Post;
import dev.euchigere.facebookclonewithspring.models.User;

import java.util.List;

public interface PostService {
    List<Post> getAllPosts();

    void deletePost(Long id);

    ResponseObject<List<Post>> getPosts();

    Post findPostById(Long id);

    ResponseObject<Post> getPostById(Long id);

    void insertPost(User user, String postText);

    void updatePost(User user, Long id, String postText);
}

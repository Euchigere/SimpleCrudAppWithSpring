package dev.euchigere.facebookclonewithspring.service.impl;

import dev.euchigere.facebookclonewithspring.dto.ResponseObject;
import dev.euchigere.facebookclonewithspring.models.Post;
import dev.euchigere.facebookclonewithspring.models.User;
import dev.euchigere.facebookclonewithspring.repos.PostRepo;
import dev.euchigere.facebookclonewithspring.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PostServiceImpl implements PostService {
    @Autowired
    PostRepo postRepo;

    public List<Post> getAllPosts() {
        return postRepo.findAll();
    }

    public Post findPostById(Long id) {
        return postRepo.findById(id).orElse(null);
    }

    public ResponseObject<List<Post>> getPosts() {
        ResponseObject<List<Post>> responseObject = new ResponseObject<>();
        responseObject.setObject(getAllPosts());
        return responseObject;
    }

    public ResponseObject<Post> getPostById(Long id) {
        ResponseObject<Post> responseObject = new ResponseObject<>();
        responseObject.setObject(findPostById(id));
        return responseObject;
    }

    public void insertPost(User user, String postText) {
        Post post = new Post();
        post.setCreatedAt(LocalDateTime.now());
        post.setText(postText);
        post.setUser(user);
        postRepo.save(post);
    }

    public void updatePost(User user, Long id, String postText) {
        Post post = findPostById(id);
        post.setText(postText);
        postRepo.save(post);

    }

    public void deletePost(Long id) {
        postRepo.deleteById(id);
    }
}

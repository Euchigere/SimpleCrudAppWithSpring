package dev.euchigere.facebookclonewithspring.service.impl;

import dev.euchigere.facebookclonewithspring.models.*;
import dev.euchigere.facebookclonewithspring.repos.PostLikeRepo;
import dev.euchigere.facebookclonewithspring.service.PostLikeService;
import dev.euchigere.facebookclonewithspring.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

import static dev.euchigere.facebookclonewithspring.controller.IndexController.userPostLikesList;
import static dev.euchigere.facebookclonewithspring.controller.LoginController.currentUser;

@Service
public class PostLikeServiceImpl implements PostLikeService {
    @Autowired
    PostLikeRepo postLikeRepo;
    @Autowired
    PostService postService;

    public void findAllPostIdUserLiked() {
        userPostLikesList = new ArrayList<>();
        userPostLikesList.addAll(
                postLikeRepo.findAllPostLikeIdByUserId(currentUser.getId())
        );
    }

    public void insertUserLike(User user, Long postId) {
        PostLike postLike = new PostLike();
        Post post = postService.findPostById(postId);
        postLike.setPost(post);
        postLike.setUser(user);
        postLikeRepo.save(postLike);
    }

    public void removeUserLike(User user, Long postId) {
        postLikeRepo.deleteByUserAndPostId(user.getId(), postId);
    }
}

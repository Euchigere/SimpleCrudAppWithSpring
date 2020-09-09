package dev.euchigere.facebookclonewithspring.controller;


import dev.euchigere.facebookclonewithspring.service.PostLikeService;
import dev.euchigere.facebookclonewithspring.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import static dev.euchigere.facebookclonewithspring.controller.LoginController.currentUser;

@Controller
@RequestMapping("/post/")
public class PostController {
    @Autowired
    PostService postService;
    @Autowired
    PostLikeService postLikeService;

    @PostMapping("create_post")
    public String createPost(
            @RequestParam(value = "post-text") String postText,
            @RequestHeader(value = "referer") String referer
    ) {
        if (currentUser == null) {
            return "redirect:/auth/login";
        }

        referer = referer.substring(referer.lastIndexOf("0/") + 1);
        if (!(postText == null || postText.isBlank())) {
            postService.insertPost(currentUser, postText);
        }
        return "redirect:" + referer;
    }

    @PostMapping("edit_post/{id}")
    public String editPost(
            @PathVariable("id") Long id,
            @RequestParam(value="post-text") String postText,
            @RequestHeader(value = "referer") String referer
    ) {
        if (currentUser == null) {
            return "redirect:/auth/login";
        }

        referer = referer.substring(referer.lastIndexOf("0/") + 1);
        if (!(postText == null || postText.isBlank())) {
            postService.updatePost(currentUser, id, postText);
        }
        return "redirect:" + referer;
    }

    @PostMapping("delete_post/{id}")
    public String deletePost(
            @PathVariable("id") Long id,
            @RequestHeader(value = "referer") String referer
    ) {
        if (currentUser == null) {
            return "redirect:/auth/login";
        }

        referer = referer.substring(referer.lastIndexOf("0/") + 1);
        postService.deletePost(id);
        return "redirect:" + referer;
    }


    @GetMapping("unlike_post/{id}")
    public String unlikePost(
            @PathVariable("id") Long postId,
            @RequestHeader(value = "referer") String referer
    ) {
        if (currentUser == null) {
            return "redirect:/auth/login";
        }

        referer = referer.substring(referer.lastIndexOf("0/") + 1);
        postLikeService.removeUserLike(currentUser, postId);
        return "redirect:" + referer;
    }

    @GetMapping("like_post/{id}")
    public String likePost(
            @PathVariable("id") Long postId,
            @RequestHeader(value = "referer") String referer
    ) {
        if (currentUser == null) {
            return "redirect:/auth/login";
        }

        referer = referer.substring(referer.lastIndexOf("0/") + 1);
        postLikeService.insertUserLike(currentUser, postId);
        return "redirect:" + referer;
    }
}

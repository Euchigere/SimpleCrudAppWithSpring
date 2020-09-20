package dev.euchigere.facebookclonewithspring.controller;


import dev.euchigere.facebookclonewithspring.service.PostLikeService;
import dev.euchigere.facebookclonewithspring.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import static dev.euchigere.facebookclonewithspring.controller.LoginController.currentUser;

/**
 * Post Controller
 * Handles all the request to create, edit and delete post
 * Handles all the request to like and unlike a post
 */
@Controller
@RequestMapping("/post/")
public class PostController {
    @Autowired
    PostService postService;
    @Autowired
    PostLikeService postLikeService;

    /**
     * Method to handle create post request
     * @param postText
     * @param referer
     * @return
     */
    @PostMapping("create_post")
    public String createPost(
            @RequestParam(value = "post-text") String postText,
            @RequestHeader(value = "referer") String referer
    ) {
        // validates session
        if (currentUser == null) {
            return "redirect:/auth/login";
        }

        referer = referer.substring(referer.lastIndexOf("0/") + 1);
        if (!(postText == null || postText.isBlank())) {
            postService.insertPost(currentUser, postText);
        }
        return "redirect:" + referer;
    }

    /**
     * Method to handle edit post request
     * @param id  id of post to edit
     * @param postText  text body to update post with
     * @param referer   the referer page where the request came from
     * @return
     */
    @PostMapping("edit_post/{id}")
    public String editPost(
            @PathVariable("id") Long id,
            @RequestParam(value="post-text") String postText,
            @RequestHeader(value = "referer") String referer
    ) {
        // validate session
        if (currentUser == null) {
            return "redirect:/auth/login";
        }

        referer = referer.substring(referer.lastIndexOf("0/") + 1);
        if (!(postText == null || postText.isBlank())) {
            postService.updatePost(currentUser, id, postText);
        }
        return "redirect:" + referer;
    }

    /**
     * Method to handle delete post request
     * @param id  id of post to delete
     * @param referer   the referer page where the request came from
     * @return
     */
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

    /**
     * Method to handle request to unlike a post
     * @param postId  id of post to unlike
     * @param referer  the referer page where the request came from
     * @return
     */
    @GetMapping("unlike_post/{id}")
    public String unlikePost(
            @PathVariable("id") Long postId,
            @RequestHeader(value = "referer") String referer
    ) {
        // validate session
        if (currentUser == null) {
            return "redirect:/auth/login";
        }

        referer = referer.substring(referer.lastIndexOf("0/") + 1);
        postLikeService.removeUserLike(currentUser, postId);
        return "redirect:" + referer;
    }

    /**
     * Method to handle request to like post
     * @param postId  id of post to like
     * @param referer   the referer page where the request came from
     * @return
     */
    @GetMapping("like_post/{id}")
    public String likePost(
            @PathVariable("id") Long postId,
            @RequestHeader(value = "referer") String referer
    ) {
        // validate session
        if (currentUser == null) {
            return "redirect:/auth/login";
        }

        referer = referer.substring(referer.lastIndexOf("0/") + 1);
        postLikeService.insertUserLike(currentUser, postId);
        return "redirect:" + referer;
    }
}

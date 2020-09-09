package dev.euchigere.facebookclonewithspring.controller;


import dev.euchigere.facebookclonewithspring.service.CommentLikeService;
import dev.euchigere.facebookclonewithspring.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import static dev.euchigere.facebookclonewithspring.controller.LoginController.currentUser;

/**
 * Comment controller
 * Handles all the request that relates to comment and comment likes
 */
@Controller
@RequestMapping("/comment/")
public class CommentController {
    @Autowired
    CommentService commentService;
    @Autowired
    CommentLikeService commentLikeService;

    /**
     * calls the service methods to create a comment and reroutes to the refer page
     * @param postId
     * @param commentText
     * @param referer
     * @return
     */
    @PostMapping("create_comment/{id}")
    public String createComment(
            @PathVariable("id") Long postId,
            @RequestParam(value="comment-text") String commentText,
            @RequestHeader(value = "referer") String referer
            ) {

        // validate the session
        if (currentUser == null) {
            return "redirect:/auth/login";
        }

        referer = referer.substring(referer.lastIndexOf("0/") + 1);
        if (!(commentText == null || commentText.isBlank())) {
            commentService.insertComment(currentUser, postId, commentText);
        }
        System.out.println(referer);
        return "redirect:" + referer;
    }

    /**
     * calls the service method to edit a post and update record in data base
     * @param commentId
     * @param commentText
     * @param referer
     * @return redirects to the referer page
     */
    @PostMapping("edit_comment/{id}")
    public String editComment(
            @PathVariable("id") Long commentId,
            @RequestParam(value="comment-text") String commentText,
            @RequestHeader(value = "referer") String referer
    ) {

        // validate the session
        if (currentUser == null) {
            return "redirect:/auth/login";
        }

        referer = referer.substring(referer.lastIndexOf("0/") + 1);
        if (!(commentText == null || commentText.isBlank())) {
            commentService.updateComment(currentUser, commentId, commentText);
        }
        return "redirect:" + referer;
    }

    /**
     * method to delete a comment record
     * @param commentId
     * @param referer
     * @return
     */
    @PostMapping("delete_comment/{id}")
    public String deleteComment(
            @PathVariable("id") Long commentId,
            @RequestHeader(value = "referer") String referer
    ) {

        // validate session
        if (currentUser == null) {
            return "redirect:/auth/login";
        }

        referer = referer.substring(referer.lastIndexOf("0/") + 1);
        commentService.deleteComment(commentId);
        return "redirect:" + referer;
    }

    /**
     * calls the service method to remove user comment like
     * @param commentId
     * @param referer
     * @return
     */
    @GetMapping("unlike_comment/{id}")
    public String unlikeComment(
            @PathVariable("id") Long commentId,
            @RequestHeader(value = "referer") String referer
    ) {
        // validate session
        if (currentUser == null) {
            return "redirect:/auth/login";
        }

        referer = referer.substring(referer.lastIndexOf("0/") + 1);
        commentLikeService.removeUserLike(currentUser, commentId);
        return "redirect:" + referer;
    }

    /**
     * calls the service method to insert user comment like
     * @param commentId
     * @param referer
     * @return
     */
    @GetMapping("like_comment/{id}")
    public String likeComment(
            @PathVariable("id") Long commentId,
            @RequestHeader(value = "referer") String referer
    ) {
        if (currentUser == null) {
            return "redirect:/auth/login";
        }

        referer = referer.substring(referer.lastIndexOf("0/") + 1);
        commentLikeService.insertUserLike(currentUser, commentId);
        return "redirect:" + referer;
    }
}

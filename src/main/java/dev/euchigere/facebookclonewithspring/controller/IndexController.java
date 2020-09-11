package dev.euchigere.facebookclonewithspring.controller;

import dev.euchigere.facebookclonewithspring.dto.ResponseObject;
import dev.euchigere.facebookclonewithspring.models.Post;
import dev.euchigere.facebookclonewithspring.models.User;
import dev.euchigere.facebookclonewithspring.service.CommentLikeService;
import dev.euchigere.facebookclonewithspring.service.PostLikeService;
import dev.euchigere.facebookclonewithspring.service.PostService;
import dev.euchigere.facebookclonewithspring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.List;
import static dev.euchigere.facebookclonewithspring.controller.LoginController.currentUser;

/**
 * Index Controller serves all the pages in the application
 * except the login and signup page
 */
@Controller
public class IndexController {
    @Autowired
    UserService userService;
    @Autowired
    PostService postService;
    @Autowired
    PostLikeService postLikeService;
    @Autowired
    CommentLikeService commentLikeService;

    public static List<Long> userPostLikesList;
    public static List<Long> userCommentLikesList;

    /**
     * Directs user to home page
     * @param model
     * @return
     */
    @GetMapping("/")
    public String home(Model model) {
        if (currentUser == null) {
            return "redirect:/auth/login";
        }
        ResponseObject<List<Post>> responseObject = postService.getPosts();
        postLikeService.findAllPostIdUserLiked();

        model.addAttribute("posts", responseObject.getObject());
        model.addAttribute("currentUser", currentUser);
        model.addAttribute("userPostLikesList", userPostLikesList);
        return "index";
    }

    /**
     * Directs user to page with a post and its comments
     * @param postId
     * @param model
     * @return
     */
    @GetMapping("view_comments/{id}")
    public String displayComments(
            @PathVariable("id") Long postId,
            Model model
    ) {
        if (currentUser == null) {
            return "redirect:/auth/login";
        }
        ResponseObject<Post> responseObject = postService.getPostById(postId);
        postLikeService.findAllPostIdUserLiked();
        commentLikeService.findAllCommentIdUserLiked();

        model.addAttribute("post", responseObject.getObject());
        model.addAttribute("currentUser", currentUser);
        model.addAttribute("userPostLikesList", userPostLikesList);
        model.addAttribute("userCommentLikesList", userCommentLikesList);

        return "comments-and-post";
    }

    /**
     * Directs user to profile page of user with the userId in param
     * @param userId
     * @return
     */
    @GetMapping("profile/{id}")
    public String displayUserProfile(
            @PathVariable("id") Long userId,
            Model model
    ) {
        if (currentUser == null) {
            return "redirect:/auth/login";
        }
        postLikeService.findAllPostIdUserLiked();
        model.addAttribute("user", userService.findUserById(userId));
        model.addAttribute("userPostLikesList", userPostLikesList);
        model.addAttribute("id", currentUser.getId());

        return "profile";
    }

    /**
     * Directs to the profile page of the current user
     * @return
     */
    @GetMapping("profile")
    public String displayCurrentUserProfile(Model model) {
        if (currentUser == null) {
            return "redirect:/auth/login";
        }
        postLikeService.findAllPostIdUserLiked();
        model.addAttribute("user", userService.findUserById(currentUser.getId()));
        model.addAttribute("userPostLikesList", userPostLikesList);
        model.addAttribute("id", currentUser.getId());

        return "profile";
    }

    /**
     * Logs out user
     * @return
     */
    @GetMapping("logout")
    public String logout() {
        currentUser = null;
        return "redirect:/auth/login";
    }
}

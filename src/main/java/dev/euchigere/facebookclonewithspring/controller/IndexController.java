package dev.euchigere.facebookclonewithspring.controller;

import dev.euchigere.facebookclonewithspring.dto.ResponseObject;
import dev.euchigere.facebookclonewithspring.models.Post;
import dev.euchigere.facebookclonewithspring.models.PostLike;
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

    @GetMapping("profile/{id}")
    public String displayUserProfile(
            @PathVariable("id") Long userId
    ) {


        // Todo displayProfile
        return "redirect:/";
    }

    @GetMapping("profile")
    public String displayCurrentUserProfile() {


        // Todo displayProfile
        return "redirect:/";
    }

    @GetMapping("logout")
    public String logout() {
        currentUser = null;
        return "redirect:/auth/login";
    }
}

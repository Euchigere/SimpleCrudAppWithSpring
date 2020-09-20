package dev.euchigere.facebookclonewithspring.service.impl;

import dev.euchigere.facebookclonewithspring.models.Comment;
import dev.euchigere.facebookclonewithspring.models.CommentLike;
import dev.euchigere.facebookclonewithspring.models.User;
import dev.euchigere.facebookclonewithspring.repos.CommentLikeRepo;
import dev.euchigere.facebookclonewithspring.service.CommentLikeService;
import dev.euchigere.facebookclonewithspring.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import static dev.euchigere.facebookclonewithspring.controller.IndexController.userCommentLikesList;
import static dev.euchigere.facebookclonewithspring.controller.LoginController.currentUser;

/**
 * CommentLikeServiceImpl
 * implement commentLikeService
 */
@Service
public class CommentLikeServiceImpl implements CommentLikeService {
    @Autowired
    CommentLikeRepo commentLikeRepo;
    @Autowired
    CommentService commentService;

    // implements method to find all commentId of comments user liked
    public void findAllCommentIdUserLiked() {
        userCommentLikesList = new ArrayList<>();
        // the Ids are added to userCommentLikesList
        userCommentLikesList.addAll(
                commentLikeRepo.findAllCommentLikeIdByUserId(currentUser.getId())
        );
    }

    public void insertUserLike(User user, Long commentId) {
        CommentLike commentLike = new CommentLike();
        Comment comment = commentService.findCommentById(commentId);
        commentLike.setComment(comment);
        commentLike.setUser(user);
        commentLikeRepo.save(commentLike);
    }

    public void removeUserLike(User user, Long commentId) {
        commentLikeRepo.deleteByUserAndCommentId(user.getId(), commentId);
    }
}

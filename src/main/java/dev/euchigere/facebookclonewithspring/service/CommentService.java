package dev.euchigere.facebookclonewithspring.service;

import dev.euchigere.facebookclonewithspring.dto.ResponseObject;
import dev.euchigere.facebookclonewithspring.models.Comment;
import dev.euchigere.facebookclonewithspring.models.User;

import java.util.List;

public interface CommentService {
    List<Comment> getAllComments();

    Comment findCommentById(Long id);

    ResponseObject<List<Comment>> getComments();

    ResponseObject<Comment> getCommentById(Long id);

    void insertComment(User user, Long postId, String commentText);

    void updateComment(User user, Long id, String commentText);

    void deleteComment(Long id);
}

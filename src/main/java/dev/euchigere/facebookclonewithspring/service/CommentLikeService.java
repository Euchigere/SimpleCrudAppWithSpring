package dev.euchigere.facebookclonewithspring.service;

import dev.euchigere.facebookclonewithspring.models.User;

public interface CommentLikeService {
    void findAllCommentIdUserLiked();

    void insertUserLike(User user, Long commentId);

    void removeUserLike(User user, Long commentId);
}

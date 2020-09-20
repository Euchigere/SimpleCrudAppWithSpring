package dev.euchigere.facebookclonewithspring.service;

import dev.euchigere.facebookclonewithspring.models.User;

public interface PostLikeService {
    void findAllPostIdUserLiked();

    void insertUserLike(User user, Long postId);

    void removeUserLike(User user, Long postId);
}

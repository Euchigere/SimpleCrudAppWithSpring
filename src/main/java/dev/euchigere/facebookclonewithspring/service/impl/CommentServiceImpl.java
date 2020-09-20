package dev.euchigere.facebookclonewithspring.service.impl;


import dev.euchigere.facebookclonewithspring.dto.ResponseObject;
import dev.euchigere.facebookclonewithspring.models.Comment;
import dev.euchigere.facebookclonewithspring.models.Post;
import dev.euchigere.facebookclonewithspring.models.User;
import dev.euchigere.facebookclonewithspring.repos.CommentRepo;
import dev.euchigere.facebookclonewithspring.service.CommentService;
import dev.euchigere.facebookclonewithspring.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    CommentRepo commentRepo;
    @Autowired
    PostService postService;

    public List<Comment> getAllComments() {
        return commentRepo.findAll();
    }

    public Comment findCommentById(Long id) {
        return commentRepo.findById(id).orElse(null);
    }

    public ResponseObject<List<Comment>> getComments() {
        ResponseObject<List<Comment>> responseObject = new ResponseObject<>();
        responseObject.setObject(getAllComments());
        return responseObject;
    }

    public ResponseObject<Comment> getCommentById(Long id) {
        ResponseObject<Comment> responseObject = new ResponseObject<>();
        responseObject.setObject(findCommentById(id));
        return responseObject;
    }

    public void insertComment(User user, Long postId, String commentText) {
        Post post = postService.findPostById(postId);
        Comment comment = new Comment();
        comment.setCreatedAt(LocalDateTime.now());
        comment.setText(commentText);
        comment.setUser(user);
        comment.setPost(post);
        commentRepo.save(comment);
    }

    public void updateComment(User user, Long id, String commentText) {
        Comment comment = findCommentById(id);
        comment.setText(commentText);
        commentRepo.save(comment);

    }

    public void deleteComment(Long id) {
        commentRepo.deleteById(id);
    }
}

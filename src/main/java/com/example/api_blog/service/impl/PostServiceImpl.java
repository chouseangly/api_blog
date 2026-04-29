package com.example.api_blog.service.impl;

import com.example.api_blog.model.entity.Auth;
import com.example.api_blog.model.entity.Post;
import com.example.api_blog.model.entity.PostImage;
import com.example.api_blog.model.request.PostRequest;
import com.example.api_blog.model.response.PostResponse;
import com.example.api_blog.repository.AuthRepo;
import com.example.api_blog.repository.PostImageRepo;
import com.example.api_blog.repository.PostRepo;
import com.example.api_blog.service.AuthService;
import com.example.api_blog.service.PinataService;
import com.example.api_blog.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {
    private final PostRepo postRepo;
    private final PinataService pinataService;
    private final PostImageRepo postImageRepo;
    private final AuthRepo authRepo;

    @Override
    @Transactional
    public PostResponse addPost(PostRequest postRequest, MultipartFile[] files) {
        Post post = new Post();
        BeanUtils.copyProperties(postRequest,post);
        // insert into post table
        postRepo.addPost(post);

        // get full information of user

        Auth auth = authRepo.findByUserId(postRequest.getUserId());

        // get generate post id
        long postId = post.getPostId();

        // upload image to pinata

        List<PostImage> images = new ArrayList<>();

        for(MultipartFile file: files){
            String url = pinataService.uploadFile(file);
            PostImage postImage = new PostImage();
            postImage.setPostId(postId);
            postImage.setImageUrl(url);
            images.add(postImage);
        }

        // insert post image into post image talbe
        if(!images.isEmpty()){
            postImageRepo.insertImage(images);
        }


        return PostResponse.builder()
                .postId(postId)
                .title(postRequest.getTitle())
                .description(postRequest.getDescription())
                .user(auth)
                .images(images)
                .build();

    }
}

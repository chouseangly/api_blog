package com.example.api_blog.controller;

import com.example.api_blog.model.entity.Post;
import com.example.api_blog.model.request.PostRequest;
import com.example.api_blog.model.response.ApiResponse;
import com.example.api_blog.model.response.PostResponse;
import com.example.api_blog.service.PostService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/posts")
@SecurityRequirement(name = "bearerAuth")
public class PostController {
    private final PostService postService;
    @SecurityRequirement(name = "bearerAuth")
    @PostMapping(value = "/add-post",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ApiResponse<PostResponse>> addPost(
            @RequestPart("data")PostRequest postRequest,
            @RequestPart("files")MultipartFile[] files
            ) {

      PostResponse post  = postService.addPost(postRequest,files);

        return ResponseEntity.status(HttpStatus.OK).body(
                new ApiResponse<>("Add post successfully", post ,HttpStatus.OK.value(), LocalDateTime.now())
        );
    }
}

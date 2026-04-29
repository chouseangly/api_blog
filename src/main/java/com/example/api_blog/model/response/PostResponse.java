package com.example.api_blog.model.response;

import com.example.api_blog.model.entity.Auth;
import com.example.api_blog.model.entity.PostImage;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class PostResponse {
    private long postId;
    private String title;
    private String description;
    private Auth user;
    private List<PostImage> images;
}

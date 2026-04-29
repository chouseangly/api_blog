package com.example.api_blog.model.request;

import com.example.api_blog.model.entity.Auth;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class PostRequest {
    private String title;
    private String description;
    private long userId;
}

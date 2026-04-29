package com.example.api_blog.repository;

import com.example.api_blog.model.entity.PostImage;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.springframework.core.env.PropertyResolver;

import java.util.List;

@Mapper
public interface PostImageRepo {


    @Insert("""
insert into post_image(post_id,image_url) values(#{postId},#{imageUrl})
""")
    @Results(id = "postImageMapper",value = {
            @Result(property = "postId",column = "post_id"),
            @Result(property = "imageUrl",column = "image_url")
    })
    void insertImage(List<PostImage> images);
}

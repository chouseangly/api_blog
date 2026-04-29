package com.example.api_blog.repository;

import com.example.api_blog.model.entity.Post;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;

@Mapper
public interface PostRepo {
@Insert("insert into posts(title,description,user_id) values (#{title},#{description},#{userId})")

    @Results(id = "PostMapper",value = {
            @Result(property = "title",column = "title"),
            @Result(property = "description",column = "description"),
            @Result(property = "userId",column = "user_id")

    })
    void addPost(Post post);
}

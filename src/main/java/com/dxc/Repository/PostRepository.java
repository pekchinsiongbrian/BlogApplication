package com.dxc.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dxc.entity.Post;

public interface PostRepository extends JpaRepository<Post, Long> {

}

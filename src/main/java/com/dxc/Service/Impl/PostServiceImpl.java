package com.dxc.Service.Impl;

import com.dxc.Payload.PostDTO;
import com.dxc.Payload.PostResponse;
import com.dxc.Repository.PostRepository;
import com.dxc.Service.PostServiceInterface;
import com.dxc.entity.Post;
import com.dxc.exception.ResourceNotFoundException;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class PostServiceImpl implements PostServiceInterface {
	
	@Autowired
	private PostRepository postRepository;
	
	// convert entity to DTO (for security and protocol reasons)
	private PostDTO mapToDto(Post post) {
		PostDTO postDto = new PostDTO();
		postDto.setId(post.getId());
		postDto.setTitle(post.getTitle());
		postDto.setDescription(post.getDescription());
		postDto.setContent(post.getContent());
		
		return postDto;
	}
	
	// convert DTO to entity
	public Post mapToEntity(PostDTO postDto) {
		Post post = new Post();
		post.setTitle(postDto.getTitle());
		post.setDescription(postDto.getDescription());
		post.setContent(postDto.getContent());
		
		return post;
	}
	
	// implementing create blog post
	public PostDTO createPost(PostDTO postDto) {
		// convert DTO to entity
		Post post = mapToEntity(postDto);
		Post newPost = postRepository.save(post);
		
		// convert entity to DTO
		PostDTO postResponse = mapToDto(newPost);
		return postResponse;
	}
	
	// implementing get blog post
	public PostResponse getAppPosts(int pageNo, int pageSize, String sortBy, String sortDir) {
		Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) 
				? Sort.by(sortBy).ascending()
				: Sort.by(sortBy).descending();
		Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
		Page<Post> posts = postRepository.findAll(pageable);
		
		// get content from page object
		List<Post> listOfPosts = posts.getContent();
		
		List<PostDTO> content = listOfPosts.stream().map(
				post -> mapToDto(post)).collect(Collectors.toList());
		
		PostResponse postResponse = new PostResponse();
		postResponse.setContent(content);
		postResponse.setPageNo(posts.getNumber());
		postResponse.setPageSize(posts.getSize());
		postResponse.setTotalElement(posts.getTotalElements());
		postResponse.setTotalPages(posts.getTotalPages());
		postResponse.setLast(posts.isLast());
		
		return postResponse;
	}
	
	// implementing get post by id
	public PostDTO getPostById(long id) {
		Post post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("POST", "id", id));
		
		return mapToDto(post);
	}
	
	//implementing update post
	public PostDTO updatePost(PostDTO postDto, long id) {
		Post post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("POST", "id", id));
		post.setTitle(postDto.getTitle());
		post.setDescription(postDto.getDescription());
		post.setContent(postDto.getContent());
		
		Post updatedPost = postRepository.save(post);
		
		return mapToDto(updatedPost);
	}
	
	// implementing delete post
	public void deletePost(long id) {
		Post post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("POST", "id", id));
		postRepository.delete(post);
	}
}

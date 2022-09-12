package com.dxc.PostController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dxc.Payload.PostDTO;
import com.dxc.Payload.PostResponse;
import com.dxc.Service.PostServiceInterface;
import com.dxc.utils.AppConst;

@RestController
@RequestMapping("api/posts")
public class PostController {
	@Autowired
	private PostServiceInterface postService;
	
	// create blog post
	@PostMapping
	public ResponseEntity<PostDTO> createPost(@RequestBody PostDTO postDto) {
		return new ResponseEntity<PostDTO>(postService.createPost(postDto), HttpStatus.CREATED);
	}
	
	// get all blogs
	@GetMapping
	public PostResponse getAllBlobs(
			@RequestParam(value="pageNo", defaultValue=AppConst.DEFAULT_PAGE_NUMBER, required=false) int pageNo,
			@RequestParam(value="pageSize", defaultValue=AppConst.DEFAULT_PAGE_SIZE, required=false) int pageSize,
			@RequestParam(value="sortBy", defaultValue=AppConst.DEFAULT_SORT_BY, required=false) String sortBy,
			@RequestParam(value="sortDir", defaultValue=AppConst.DEFAULT_SORT_DIRECTION, required=false) String sortDir
			) {
		 return postService.getAppPosts(pageNo, pageSize, sortBy, sortDir);
	}
	
	// get blog by id
	@GetMapping("/{id}")
	public ResponseEntity<PostDTO> getPostById(@PathVariable(name="id") long id) {
		return ResponseEntity.ok(postService.getPostById(id));
	}
	
	// update post by id
	@PutMapping("/{id}")
	public ResponseEntity<PostDTO> updatePost(@RequestBody PostDTO postDto, @PathVariable(name="id") long id) {
		PostDTO postResponse = postService.updatePost(postDto, id);
		return new ResponseEntity<PostDTO>(postResponse, HttpStatus.OK);
	}
	
	// delete post by id
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deletePost(@PathVariable(name="id") long id) {
		postService.deletePost(id);
		return new ResponseEntity<String>("Post entity deleted successfully", HttpStatus.OK);
	}
}

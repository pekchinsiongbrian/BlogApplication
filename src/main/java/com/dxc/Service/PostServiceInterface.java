package com.dxc.Service;

import com.dxc.Payload.PostDTO;
import com.dxc.Payload.PostResponse;

public interface PostServiceInterface {
	public PostDTO createPost(PostDTO postDto);
	
	public PostResponse getAppPosts(int pageNo, int pageSize, String sortBy, String sortDir);
	
	public PostDTO getPostById(long id);

	public PostDTO updatePost(PostDTO postDto, long id);
	
	public void deletePost(long id);
}

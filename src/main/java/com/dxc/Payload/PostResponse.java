package com.dxc.Payload;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostResponse {
	private List<PostDTO> content;
	private int pageNo;
	private int pageSize;
	private long totalElement;
	private int totalPages;
	private boolean isLast;
	
	public List<PostDTO> getContent() {
		return content;
	}
	
	public int getPageNo() {
		return pageNo;
	}
	
	public int getPageSize() {
		return pageSize;
	}
	
	public long getTotalElement() {
		return totalElement;
	}
	
	public int getTotalPages() {
		return totalPages;
	}
	
	public boolean isLast() {
		return isLast;
	}
	
	public void setContent(List<PostDTO> content) {
		this.content = content;
	}
	
	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}
	
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	
	public void setTotalElement(long totalElement) {
		this.totalElement = totalElement;
	}
	
	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}
	
	public void setLast(boolean isLast) {
		this.isLast = isLast;
	}
}

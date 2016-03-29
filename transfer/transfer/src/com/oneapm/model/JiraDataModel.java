package com.oneapm.model;

import java.util.List;

public class JiraDataModel {
	private String status;
	private String creator;
	private String repoter;
	private String createTime;
	private String issueKey;
	private String comment;
	private List<CommentModel> commentModel;
	

	public List<CommentModel> getCommentModel() {
		return commentModel;
	}
	public void setCommentModel(List<CommentModel> commentModel) {
		this.commentModel = commentModel;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getCreator() {
		return creator;
	}
	public void setCreator(String creator) {
		this.creator = creator;
	}
	public String getRepoter() {
		return repoter;
	}
	public void setRepoter(String repoter) {
		this.repoter = repoter;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getIssueKey() {
		return issueKey;
	}
	public void setIssueKey(String issueKey) {
		this.issueKey = issueKey;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	
	
}

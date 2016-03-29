package com.oneapm.dao;

import java.util.List;

import com.oneapm.model.CommentModel;

public interface ICommentDao {
	public List<CommentModel> selectCommentByKey(String issuekey);
	public int countComment(String issuekey);
	public void insertComment(CommentModel comment);
	public boolean isexists(String commentId);
}

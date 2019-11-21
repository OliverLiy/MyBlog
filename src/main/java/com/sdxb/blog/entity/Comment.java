package com.sdxb.blog.entity;

public class Comment {
    private int id;
    private int parent_id;
    private int type;
    private int commentor;
    private int like_count;
    private long createtime;
    private int commentcount;
    private String content;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getParent_id() {
        return parent_id;
    }

    public void setParent_id(int parent_id) {
        this.parent_id = parent_id;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getCommentor() {
        return commentor;
    }

    public void setCommentor(int commentor) {
        this.commentor = commentor;
    }

    public int getLike_count() {
        return like_count;
    }

    public void setLike_count(int like_count) {
        this.like_count = like_count;
    }

    public long getCreatetime() {
        return createtime;
    }

    public void setCreatetime(long createtime) {
        this.createtime = createtime;
    }

    public String getContent() {
        return content;
    }

    public int getCommentcount() {
        return commentcount;
    }

    public void setCommentcount(int commentcount) {
        this.commentcount = commentcount;
    }

    public void setContent(String content) {
        this.content = content;
    }
}

package com.sdxb.blog.dto;

import java.util.List;

public class TagDto {
    private String categoryname;
    private List<String> tags;

    public String getCategoryname() {
        return categoryname;
    }

    public void setCategoryname(String categoryname) {
        this.categoryname = categoryname;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }
}

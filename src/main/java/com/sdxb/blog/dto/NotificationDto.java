package com.sdxb.blog.dto;

import com.sdxb.blog.entity.User;

public class NotificationDto {
    private int id;
    private int receiver;
    private int type;
    private long createtime;
    private int status;
    private User notifier;
    private String outercontent;
    private int questionid;

    public int getQuestionid() {
        return questionid;
    }

    public void setQuestionid(int questionid) {
        this.questionid = questionid;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getReceiver() {
        return receiver;
    }

    public void setReceiver(int receiver) {
        this.receiver = receiver;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public long getCreatetime() {
        return createtime;
    }

    public void setCreatetime(long createtime) {
        this.createtime = createtime;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public User getNotifier() {
        return notifier;
    }

    public void setNotifier(User notifier) {
        this.notifier = notifier;
    }

    public String getOutercontent() {
        return outercontent;
    }

    public void setOutercontent(String outercontent) {
        this.outercontent = outercontent;
    }
}

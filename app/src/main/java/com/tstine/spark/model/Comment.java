
package com.tstine.spark.model;

import com.google.gson.annotations.Expose;

import org.apache.commons.lang3.builder.ToStringBuilder;


public class Comment {

    @Expose
    private User user;
    @Expose
    private String date;
    @Expose
    private String comment;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

}

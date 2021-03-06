
package com.tstine.spark.model;

import com.google.gson.annotations.Expose;

import org.apache.commons.lang3.builder.ToStringBuilder;


public class Forms {

    @Expose
    private Add_comment add_comment;
    @Expose
    private Like like;

    public Add_comment getAdd_comment() {
        return add_comment;
    }

    public void setAdd_comment(Add_comment add_comment) {
        this.add_comment = add_comment;
    }

    public Like getLike() {
        return like;
    }

    public void setLike(Like like) {
        this.like = like;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

}

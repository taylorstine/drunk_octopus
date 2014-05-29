
package com.tstine.spark.model;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import org.apache.commons.lang.builder.ToStringBuilder;

@Generated("org.jsonschema2pojo")
public class Product {

    @Expose
    private String _id;
    @Expose
    private Image image;
    @Expose
    private String title;
    @Expose
    private String description;
    @Expose
    private Variations variations;
    @Expose
    private Integer comment_count;
    @Expose
    private Integer like_count;
    @Expose
    private List<Comment> comments = new ArrayList<Comment>();
    @Expose
    private Forms forms;
    @Expose
    private String category;
    @Expose
    private Integer price;

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Variations getVariations() {
        return variations;
    }

    public void setVariations(Variations variations) {
        this.variations = variations;
    }

    public Integer getComment_count() {
        return comment_count;
    }

    public void setComment_count(Integer comment_count) {
        this.comment_count = comment_count;
    }

    public Integer getLike_count() {
        return like_count;
    }

    public void setLike_count(Integer like_count) {
        this.like_count = like_count;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public Forms getForms() {
        return forms;
    }

    public void setForms(Forms forms) {
        this.forms = forms;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

}

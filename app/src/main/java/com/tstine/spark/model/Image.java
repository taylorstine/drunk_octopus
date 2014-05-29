
package com.tstine.spark.model;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import org.apache.commons.lang.builder.ToStringBuilder;

@Generated("org.jsonschema2pojo")
public class Image {

    @Expose
    private Size size;
    @Expose
    private String url;
    @Expose
    private String holding_color;

    public Size getSize() {
        return size;
    }

    public void setSize(Size size) {
        this.size = size;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getHolding_color() {
        return holding_color;
    }

    public void setHolding_color(String holding_color) {
        this.holding_color = holding_color;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

}

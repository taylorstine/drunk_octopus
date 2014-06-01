
package com.tstine.spark.model;

import com.google.gson.annotations.Expose;
import org.apache.commons.lang3.builder.ToStringBuilder;


public class Size {

    @Expose
    private Integer width;
    @Expose
    private Integer height;

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

}

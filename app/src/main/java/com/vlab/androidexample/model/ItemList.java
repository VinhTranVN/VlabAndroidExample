package com.vlab.androidexample.model;

/**
 * Created by Vinh.Tran on 6/9/16.
 */
public abstract class ItemList {

    protected String title;
    protected String description;
    protected boolean isSelected;

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

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }
}

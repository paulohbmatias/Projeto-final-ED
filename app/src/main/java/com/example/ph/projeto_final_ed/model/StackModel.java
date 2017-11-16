package com.example.ph.projeto_final_ed.model;

/**
 * Created by ph on 23/10/17.
 */

public class StackModel {
    private int content;
    private boolean top;

    public StackModel(int content) {
        this.top = false;
        this.content = content;
    }

    public boolean isTop() {
        return top;
    }

    public void setTop(boolean top) {
        this.top = top;
    }

    public StackModel() {
    }

    public int getContent() {
        return content;
    }

    public void setContent(int content) {
        this.content = content;
    }
}

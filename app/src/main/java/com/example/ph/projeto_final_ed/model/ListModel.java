package com.example.ph.projeto_final_ed.model;

/**
 * Created by ph on 20/10/17.
 */

public class ListModel {
    private int position;
    private int content;

    public ListModel(int position, int content) {
        this.position = position;
        this.content = content;
    }

    public ListModel() {
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public int getContent() {
        return content;
    }

    public void setContent(int content) {
        this.content = content;
    }
}

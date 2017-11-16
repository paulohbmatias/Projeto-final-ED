package com.example.ph.projeto_final_ed.model;

/**
 * Created by ph on 23/10/17.
 */

public class QueueModel {
    private int content;
    private boolean top;
    private boolean end;

    public QueueModel(int content) {
        this.end = false;
        this.top = false;
        this.content = content;
    }

    public boolean isEnd() {
        return end;
    }

    public void setEnd(boolean end) {
        this.end = end;
    }

    public boolean isTop() {
        return top;
    }

    public void setTop(boolean top) {
        this.top = top;
    }

    public QueueModel() {
    }

    public int getContent() {
        return content;
    }

    public void setContent(int content) {
        this.content = content;
    }
}

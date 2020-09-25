package com.example.prototype2.databases.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class FaceEntity {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "faceEntity_id")
    private int id;

    @ColumnInfo(name = "faceEntity_left")
    int left;
    @ColumnInfo(name = "faceEntity_top")
    int top;
    @ColumnInfo(name = "faceEntity_right")
    int right;
    @ColumnInfo(name = "faceEntity_bottom")
    int bottom;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getLeft() {
        return left;
    }

    public void setLeft(int left) {
        this.left = left;
    }

    public int getTop() {
        return top;
    }

    public void setTop(int top) {
        this.top = top;
    }

    public int getRight() {
        return right;
    }

    public void setRight(int right) {
        this.right = right;
    }

    public int getBottom() {
        return bottom;
    }

    public void setBottom(int bottom) {
        this.bottom = bottom;
    }
}

package com.example.prototype2.databases.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class FaceEntity {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "faceEntity_id")
    private int id;

    @ColumnInfo(name = "faceEntity_image")
    int image;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}

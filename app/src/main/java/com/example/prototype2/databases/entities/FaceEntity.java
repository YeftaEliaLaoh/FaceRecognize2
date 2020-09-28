package com.example.prototype2.databases.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class FaceEntity {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "faceEntity_id")
    private int id;

    @ColumnInfo(name = "faceEntity_image", typeAffinity = ColumnInfo.BLOB)
    public byte[] image;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }
}

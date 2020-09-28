package com.example.prototype2.databases.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class FaceEntity {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "faceEntity_id")
    private int id;

    @ColumnInfo(name = "faceEntity_headEularAngleX")
    public float headEulerAngleX;

    @ColumnInfo(name = "faceEntity_headEularAngleY")
    public float headEulerAngleY;

    @ColumnInfo(name = "faceEntity_headEularAngleZ")
    public float headEulerAngleZ;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getHeadEulerAngleX() {
        return headEulerAngleX;
    }

    public void setHeadEulerAngleX(float headEulerAngleX) {
        this.headEulerAngleX = headEulerAngleX;
    }

    public float getHeadEulerAngleY() {
        return headEulerAngleY;
    }

    public void setHeadEulerAngleY(float headEulerAngleY) {
        this.headEulerAngleY = headEulerAngleY;
    }

    public float getHeadEulerAngleZ() {
        return headEulerAngleZ;
    }

    public void setHeadEulerAngleZ(float headEulerAngleZ) {
        this.headEulerAngleZ = headEulerAngleZ;
    }
}

package com.example.prototype2.databases.daos;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.prototype2.databases.entities.FaceEntity;

import java.util.List;

@Dao
public interface FaceDao {
    @Insert
    long insertNewEntry(FaceEntity faceEntity);

    @Query("SELECT * FROM FaceEntity WHERE faceEntity_headEularAngleX = :headEularAngleX and faceEntity_headEularAngleY = :headEularAngleY " +
            "and faceEntity_headEularAngleZ = :headEularAngleZ ;")
    List<FaceEntity> getAllByHeadEularAngle(float headEularAngleX, float headEularAngleY, float headEularAngleZ);
}
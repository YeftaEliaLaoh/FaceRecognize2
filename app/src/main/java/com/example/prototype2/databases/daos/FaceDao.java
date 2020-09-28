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

    @Query("SELECT * FROM FaceEntity ;")
    List<FaceEntity> getAllData();
}

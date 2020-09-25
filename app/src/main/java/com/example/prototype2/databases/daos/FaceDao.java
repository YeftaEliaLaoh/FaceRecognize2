package com.example.prototype2.databases.daos;

import androidx.room.Dao;
import androidx.room.Insert;

import com.example.prototype2.databases.entities.FaceEntity;

@Dao
public interface FaceDao {
    @Insert
    long insertNewEntry(FaceEntity faceEntity);
}

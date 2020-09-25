package com.example.prototype2.databases;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.prototype2.databases.daos.FaceDao;
import com.example.prototype2.databases.entities.FaceEntity;

@Database(entities = {FaceEntity.class}, version = 1, exportSchema = false)
public abstract class AppDatabases extends RoomDatabase {
    public abstract FaceDao faceDao();
}

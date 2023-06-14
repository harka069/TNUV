package com.example.sparovcek2;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(
        entities = {Transaction.class},
        version = 1
)
public abstract class AppDatabase extends RoomDatabase {
    public abstract DAO_class Dao();
}


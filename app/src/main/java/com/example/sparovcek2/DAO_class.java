package com.example.sparovcek2;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface DAO_class {
    @Query("SELECT * FROM [transaction]")
    List<Transaction> getAll();

    @Query("SELECT * FROM [transaction] WHERE date >= :monthstart")
    List<Transaction> getMonthData(long monthstart);

    @Insert
    void insertRecord(Transaction trans);
    @Query("DELETE FROM [transaction]")
    void deleteAll();

}

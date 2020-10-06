package com.aplikasisertifikasi.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface HistoryDao {
    //menambahkan data ke database
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long addToHistory(History history);

    //mengambil data dari database
    @Query("SELECT * FROM tb_history ORDER BY id DESC")
    History[] getAllHistory();

}

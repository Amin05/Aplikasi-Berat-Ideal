package com.aplikasisertifikasi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.os.Bundle;

import com.aplikasisertifikasi.adapter.HistoryAdapter;
import com.aplikasisertifikasi.db.AppDatabase;
import com.aplikasisertifikasi.db.History;

import java.util.ArrayList;
import java.util.Arrays;

public class HistoryActivity extends AppCompatActivity {
    private AppDatabase db;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "tb_history").allowMainThreadQueries().build();

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        loadData();
    }

    //method untuk memanggil data dari database & memasang adapter ke recyclerview
    private void loadData(){
        ArrayList<History> historyArrayList = new ArrayList<>(Arrays.asList(db.historyDao().getAllHistory()));
        RecyclerView.Adapter adapter = new HistoryAdapter(historyArrayList);
        recyclerView.setAdapter(adapter);
    }
}

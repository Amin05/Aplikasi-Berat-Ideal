package com.aplikasisertifikasi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.aplikasisertifikasi.db.AppDatabase;
import com.aplikasisertifikasi.db.History;
import com.aplikasisertifikasi.model.DataUser;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText etNama, etBerat, etTinggi;
    private CheckBox cbPria, cbWanita;
    private Button btnHitung, btnUlang, btnSimpan;
    private TextView tvHasil;
    private DataUser data;
    private LinearLayout linearLayout;
    private AppDatabase db;
    private long status;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "tb_history").allowMainThreadQueries().build();

        etNama = findViewById(R.id.etNama);
        etBerat = findViewById(R.id.etBerat);
        etTinggi = findViewById(R.id.etTinggi);
        btnHitung = findViewById(R.id.btnHitung);
        btnSimpan = findViewById(R.id.btnSimpan);
        btnUlang = findViewById(R.id.btnUlang);
        cbPria = findViewById(R.id.cbPria);
        cbWanita = findViewById(R.id.cbWanita);
        tvHasil = findViewById(R.id.tvHasil);
        linearLayout = findViewById(R.id.llButton);

        btnHitung.setOnClickListener(this);
        btnSimpan.setOnClickListener(this);
        btnUlang.setOnClickListener(this);
        cbPria.setOnClickListener(this);
        cbWanita.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btnHitung) {
            if (cekNotNull()) {
                tvHasil.setText(data.getDataToShow());
                linearLayout.setVisibility(View.VISIBLE);
            }
        } else if (v.getId() == R.id.cbPria) {
            cbWanita.setChecked(false);
        } else if (v.getId() == R.id.cbWanita) {
            cbPria.setChecked(false);
        } else if (v.getId() == R.id.btnUlang){
            linearLayout.setVisibility(View.INVISIBLE);
            refreshValue();
        } else if (v.getId() == R.id.btnSimpan){
            final Calendar c = Calendar.getInstance();
            int mYear = c.get(Calendar.YEAR);
            int mMonth = c.get(Calendar.MONTH);
            int mDay = c.get(Calendar.DAY_OF_MONTH);

            String dateNow = mDay + "/" + (mMonth + 1) + "/" + mYear;

            History history = new History();
            history.setTanggal(dateNow);
            history.setNama(data.getNama());
            history.setJenis_kelamin(data.getJenisKelamin());
            history.setBerat(data.getBerat());
            history.setTinggi(data.getTinggi());
            history.setKeterangan(data.getKeterangan());

            addHistory(history);
        }
    }

    //cek input pada form
    private boolean cekNotNull() {
        String nama = etNama.getText().toString().trim();
        String berat = etBerat.getText().toString().trim();
        String tinggi = etTinggi.getText().toString().trim();
        String jenisKelamin;

        if (TextUtils.isEmpty(nama) || TextUtils.isEmpty(berat) || TextUtils.isEmpty(tinggi)) {
            Toast.makeText(MainActivity.this, "Lengkapi Field Yang Kosong !!!", Toast.LENGTH_SHORT).show();
            return false;
        } else {
            //cek jenis kelamin
            if (cbPria.isChecked()) {
                jenisKelamin = "pria";
            } else {
                jenisKelamin = "wanita";
            }

            data = new DataUser(nama, berat, tinggi, jenisKelamin);
            return true;
        }
    }

    //mereset form input
    @SuppressLint("SetTextI18n")
    private void refreshValue(){
        linearLayout.setVisibility(View.INVISIBLE);
        tvHasil.setText("Hasil Perhitungan");
        etNama.setText("");
        etTinggi.setText("");
        etBerat.setText("");
    }

    //proses menambahkan data ke database
    @SuppressLint("StaticFieldLeak")
    private void addHistory(final History history){

        new AsyncTask<Void, Void, Long>(){
            @Override
            protected Long doInBackground(Void... voids) {
                status = db.historyDao().addToHistory(history);
                return status;
            }

            @Override
            protected void onPostExecute(Long status) {
                Intent intent = new Intent(MainActivity.this, HistoryActivity.class);
                startActivity(intent);
            }
        }.execute();
    }

    //memasang option menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        super.onOptionsItemSelected(item);

        if (item.getItemId() == R.id.menu_history){
            Intent intent = new Intent(MainActivity.this, HistoryActivity.class);
            startActivity(intent);
        }
        return true;
    }

    @Override
    protected void onResume() {
        super.onResume();
        refreshValue();
    }
}

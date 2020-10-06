package com.aplikasisertifikasi.model;

public class DataUser {
    private String nama, berat, tinggi, jenisKelamin, dataToShow, keterangan;

    public DataUser(){}

    //konstruktor
    public DataUser(String nama, String berat, String tinggi, String jenisKelamin) {
        this.nama = nama;
        this.berat = berat;
        this.tinggi = tinggi;
        this.jenisKelamin = jenisKelamin;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getBerat() {
        return berat;
    }

    public void setBerat(String berat) {
        this.berat = berat;
    }

    public String getTinggi() {
        return tinggi;
    }

    public void setTinggi(String tinggi) {
        this.tinggi = tinggi;
    }

    public String getJenisKelamin() {
        return jenisKelamin;
    }

    public void setJenisKelamin(String jenisKelamin) {
        this.jenisKelamin = jenisKelamin;
    }

    public String getDataToShow() {
        return showData();
    }

    public String getKeterangan() {
        return keterangan;
    }

    private String showData(){
        cekIdeal();
        return dataToShow;
    }

    //menghitung berat ideal user
    //rumus berat badan ideal berdasarkan website halodoc.com dan gooddoctor.co.id
    private Double hitungBeratIdeal(){
        double hasil;
        if (jenisKelamin.equals("pria")){
            hasil = (Double.parseDouble(tinggi)-100)-((Double.parseDouble(tinggi)-100)*0.1);
        } else {
            hasil = (Double.parseDouble(tinggi)-100)-((Double.parseDouble(tinggi)-100)*0.15);
        }
        return hasil;
    }

    //membandingkan berat user dan berat ideal user
    private void cekIdeal(){
        double berat2 = Double.parseDouble(berat);
        double hasil2 = hitungBeratIdeal();
        if (berat2>hasil2){
            dataToShow = "Anda terlalu berat";
            this.keterangan = "terlalu berat";
        } else if (berat2==hasil2){
            dataToShow = "Berat anda ideal";
            this.keterangan = "ideal";
        } else if (berat2<hasil2) {
            dataToShow = "Anda terlalu kurus";
            this.keterangan = "terlalu kurus";
        }

        dataToShow = dataToShow + "\n Berat ideal anda = " + hasil2 +" Kg";
    }
}

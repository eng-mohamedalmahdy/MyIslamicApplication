package com.example.myisamlicapplication.data.pojo;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "quran")
public class Aya {

    @PrimaryKey
    private int id;

    private int jozz;
    private int sora;

    @NonNull
    private String sora_name_en;

    @NonNull
    private String sora_name_ar;

    private int page;
    private int line_start;
    private int line_end;
    private int aya_no;

    @NonNull
    private String aya_text;

    @NonNull
    private String aya_text_emlaey;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getJozz() {
        return jozz;
    }

    public void setJozz(int jozz) {
        this.jozz = jozz;
    }

    public int getSora() {
        return sora;
    }

    public void setSora(int sora) {
        this.sora = sora;
    }

    public String getSora_name_en() {
        return sora_name_en;
    }

    public void setSora_name_en(String sora_name_en) {
        this.sora_name_en = sora_name_en;
    }

    public String getSora_name_ar() {
        return sora_name_ar;
    }

    public void setSora_name_ar(String sora_name_ar) {
        this.sora_name_ar = sora_name_ar;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getLine_start() {
        return line_start;
    }

    public void setLine_start(int line_start) {
        this.line_start = line_start;
    }

    public int getLine_end() {
        return line_end;
    }

    public void setLine_end(int line_end) {
        this.line_end = line_end;
    }

    public int getAya_no() {
        return aya_no;
    }

    public void setAya_no(int aya_no) {
        this.aya_no = aya_no;
    }

    public String getAya_text() {
        return aya_text;
    }

    public void setAya_text(String aya_text) {
        this.aya_text = aya_text;
    }

    public String getAya_text_emlaey() {
        return aya_text_emlaey;
    }

    public void setAya_text_emlaey(String aya_text_emlaey) {
        this.aya_text_emlaey = aya_text_emlaey;
    }
}

package com.example.myisamlicapplication.data.pojo.azkar;

public class Zekr {


    private String zekr,category,description,reference,count;

    public Zekr() {
    }

    public Zekr(String zekr, String category, String description, String reference, String count) {
        this.zekr = zekr;
        this.category = category;
        this.description = description;
        this.reference = reference;
        this.count = count;
    }

    public String getZekr() {
        return zekr;
    }

    public void setZekr(String zekr) {
        this.zekr = zekr;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }
}

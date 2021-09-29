package com.example.myisamlicapplication.data.pojo.azkar;

import java.util.Objects;

public class ZekrType {

   private String zekrName;

   private int zekrImageId;

    public ZekrType(String zekrName) {
        this.zekrName = zekrName;
        zekrImageId = -1;
    }

    public ZekrType(String zekrName, int zekrImageId) {
        this.zekrName = zekrName;
        this.zekrImageId = zekrImageId;
    }

    public String getZekrName() {
        return zekrName;
    }

    public int getZekrImageId() {
        return zekrImageId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ZekrType zekrType = (ZekrType) o;
        return zekrImageId == zekrType.zekrImageId && zekrName.equals(zekrType.zekrName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(zekrName, zekrImageId);
    }
}

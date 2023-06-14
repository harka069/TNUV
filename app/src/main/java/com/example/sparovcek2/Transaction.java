package com.example.sparovcek2;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "transaction")
public class Transaction {
    @ColumnInfo(name = "id")
    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "type")
    private String type;

    @ColumnInfo(name = "date")
    private long date;

    @ColumnInfo(name = "category")
    private String category;

    @ColumnInfo(name = "sum")
    private float sum;

    @ColumnInfo(name = "description")
    private String description;


    public int getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public long getDate() {
        return date;
    }

    public String getCategory() {
        return category;
    }

    public float getSum() {
        return sum;
    }

    public String getDescription() {
        return description;
    }



    public void setId(int id) {
        this.id = id;
    }

    public void setType(String type) {this.type = type;}

    public void setDate(long date) {
        this.date = date;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setSum(float sum) {
        this.sum = sum;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}

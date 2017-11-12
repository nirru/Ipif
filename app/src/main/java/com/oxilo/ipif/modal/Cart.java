package com.oxilo.ipif.modal;

/**
 * Created by Harshita on 11/10/2017.
 */

public class Cart {
    private String item_type;
    private String size;
    private String count;
    private String cost;
    private int drawable_id;

    public Cart(String item_type, String size, String count, String cost, int drawable_id) {
        this.item_type = item_type;
        this.size = size;
        this.count = count;
        this.cost = cost;
        this.drawable_id = drawable_id;
    }

    public String getItem_type() {
        return item_type;
    }

    public void setItem_type(String item_type) {
        this.item_type = item_type;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    public int getDrawable_id() {
        return drawable_id;
    }

    public void setDrawable_id(int drawable_id) {
        this.drawable_id = drawable_id;
    }
}

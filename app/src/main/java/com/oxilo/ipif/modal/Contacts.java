package com.oxilo.ipif.modal;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Harshita on 11/19/2017.
 */

public class Contacts {

   public int id;
    public String username;
    public String phone;

    public Contacts(int id, String username, String phone) {
        this.id = id;
        this.username = username;
        this.phone = phone;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}

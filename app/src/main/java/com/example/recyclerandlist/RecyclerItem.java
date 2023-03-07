package com.example.recyclerandlist;

import android.graphics.drawable.Drawable;

public class RecyclerItem {
    String name;
    String email;
    String comment;
    Drawable avatar;

    public RecyclerItem(String name, String email, String comment) {
        this.name = name;
        this.email = email;
        this.comment = comment;
    }
}

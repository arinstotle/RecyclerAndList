package com.example.recyclerandlist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ListViewAdapter extends ArrayAdapter<JSONObject> {
    int listLayout;
    ArrayList<JSONObject> commentList;
    Context context;

    public ListViewAdapter(@NonNull Context context, int listLayout,
                           int field, ArrayList<JSONObject> commentList) {
        super(context, listLayout, field, commentList);
        this.context = context;
        this.listLayout = listLayout;
        this.commentList = commentList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater =
                (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View listViewItem = inflater.inflate(listLayout, null, false);
        TextView name = listViewItem.findViewById(R.id.name_text_rec);
        TextView email = listViewItem.findViewById(R.id.email_text);
        TextView comment = listViewItem.findViewById(R.id.message_text);
        ImageView avatar = listViewItem.findViewById(R.id.avatar_rec);

        try {
            name.setText(commentList.get(position).getString("name"));
            email.setText(commentList.get(position).getString("email"));
            comment.setText(commentList.get(position).getString("body"));
            CharSequence text = email.getText();
            if ((text.charAt(0) == 'A') || (text.charAt(0) == 'B')
                    || (text.charAt(0) == 'C') || (text.charAt(0) == 'D')
                    ) {
                avatar.setImageResource(R.drawable.icon1);
            } else if ((text.charAt(0) == 'E') || (text.charAt(0) == 'F')
                    || (text.charAt(0) == 'G') || (text.charAt(0) == 'H')
                    ) {
                avatar.setImageResource(R.drawable.icon2);
            } else if ((text.charAt(0) == 'I') || (text.charAt(0) == 'J')
                    || (text.charAt(0) == 'K'))
             {
                avatar.setImageResource(R.drawable.icon3);
            } else if ((text.charAt(0) == 'L') || (text.charAt(0) == 'M')
                    || (text.charAt(0) == 'N'))
            {
                avatar.setImageResource(R.drawable.icon4);
            } else if ((text.charAt(0) == 'O') || (text.charAt(0) == 'P')
                    || (text.charAt(0) == 'Q'))
            {
                avatar.setImageResource(R.drawable.icon5);
            } else if ((text.charAt(0) == 'R') || (text.charAt(0) == 'S')
                    || (text.charAt(0) == 'T'))
            {
                avatar.setImageResource(R.drawable.icon6);
            } else {
                avatar.setImageResource(R.drawable.icon7);
            }

        } catch (JSONException jsonException) {
            jsonException.printStackTrace();
        }
        return listViewItem;
    }
}

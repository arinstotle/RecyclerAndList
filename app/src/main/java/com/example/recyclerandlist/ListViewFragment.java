package com.example.recyclerandlist;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.recyclerandlist.databinding.ListViewFragmentBinding;
import com.example.recyclerandlist.databinding.MainFragmentBinding;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

public class ListViewFragment extends Fragment {

    ListView listView_;
    String TAG = "ListViewFragment";
    Context contextActivity;
    ListViewFragmentBinding listViewFragmentBinding;
    private static final String JSON_URL = "https://raw.githubusercontent.com/arinstotle/MyJSON/main/myjson.json";


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        listViewFragmentBinding = ListViewFragmentBinding.inflate(
                inflater, container, false
        );
        return listViewFragmentBinding.getRoot();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        contextActivity = context;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        listView_ = listViewFragmentBinding.listView;
        loadJSONFromURL(JSON_URL);
        listView_.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.i("Position: " + position, TAG);
                Toast.makeText(getContext(), "Position: " + position, Toast.LENGTH_LONG).show();
            }
        });
    }

    private void  loadJSONFromURL(String url){
        final ProgressBar progressBar = listViewFragmentBinding.progressBar;
        progressBar.setVisibility(ListView.VISIBLE);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
        new Response.Listener< String>() {
            @Override
            public void onResponse(String response) {
                progressBar.setVisibility(View.INVISIBLE);
                try {
                    JSONObject object = new JSONObject(EncodingToUTF8(response));
                    JSONArray jsonArray = object.getJSONArray("users");
                    ArrayList< JSONObject> listItems = getArrayListFromJSONArray(jsonArray);
                    ListAdapter adapter = new ListViewAdapter(getContext(), R.layout.items, R.id.name_text, listItems);
                    listView_.setAdapter(adapter);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        },
        new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(),error.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(contextActivity);
        requestQueue.add(stringRequest);
    }
    private ArrayList< JSONObject> getArrayListFromJSONArray(JSONArray jsonArray){
        ArrayList< JSONObject> aList = new ArrayList< JSONObject>();
        try {
            if (jsonArray!= null) {
                for (int i = 0; i < jsonArray.length(); i++) {
                    aList.add(jsonArray.getJSONObject(i));
                }
            }
        } catch (JSONException jsonException){
            jsonException.printStackTrace();
        }
        return aList;
    }
    public  static  String EncodingToUTF8(String response){
        try {
            byte[] code = response.toString().getBytes("ISO-8859-1");
            response = new String(code, "UTF-8");
        } catch (UnsupportedEncodingException unsupportedEncodingException){
            unsupportedEncodingException.printStackTrace();
            return null;
        }
        return response;
    }
}

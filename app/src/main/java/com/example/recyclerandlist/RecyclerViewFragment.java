package com.example.recyclerandlist;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.recyclerandlist.databinding.ListViewFragmentBinding;
import com.example.recyclerandlist.databinding.RecyclerViewFragmentBinding;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

public class RecyclerViewFragment extends Fragment {

    String TAG1 = "RecyclerViewFragment";
    RecyclerViewFragmentBinding recyclerViewFragmentBinding;
    RecyclerView recyclerView;
    ArrayList<RecyclerItem> recyclerItems;

    private static final String JSON_URL = "https://raw.githubusercontent.com/arinstotle/MyJSON/main/myjson.json";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        recyclerViewFragmentBinding = RecyclerViewFragmentBinding.inflate(
                inflater, container, false
        );
        return recyclerViewFragmentBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = recyclerViewFragmentBinding.recycler;
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerItems = new ArrayList<RecyclerItem>();
        loadJSONFromURL(JSON_URL);

    }

    private void  loadJSONFromURL(String url){
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener< String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONObject object = new JSONObject(EncodingToUTF8(response));
                            JSONArray jsonArray = object.getJSONArray("users");
                            ArrayList< JSONObject> listItems = getArrayListFromJSONArray(jsonArray);
                            for (JSONObject json : listItems) {
                                String _name = json.getString("name");
                                String _email = json.getString("email");
                                String _body = json.getString("body");
                                RecyclerItem recyclerItem = new RecyclerItem(_name,
                                        _email, _body);
                                Log.i("vfdl", "Item: " + recyclerItem.name + " " + recyclerItem.email);
                                recyclerItems.add(recyclerItem);
                            }
                            RecyclerViewAdapter recyclerViewAdapter = new RecyclerViewAdapter(recyclerItems, getContext());
                            recyclerView.setAdapter(recyclerViewAdapter);
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
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
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

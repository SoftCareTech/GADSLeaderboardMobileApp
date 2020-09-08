package com.example.gadsleaderboardmobileapp.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.gadsleaderboardmobileapp.Model.Leaders;
import com.example.gadsleaderboardmobileapp.Model.LeadersAdapter;
import com.example.gadsleaderboardmobileapp.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class LearningLeadersFragment extends Fragment {


    private RecyclerView mRecyclerView;
    private ArrayList<Leaders> leaders;
    private RequestQueue mRequestQueue;
    private LeadersAdapter leadersAdapter;
    private Context context;

    public LearningLeadersFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment


        View rootView = inflater.inflate(R.layout.leaders, container, false);

        return rootView;
    }
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        context = getContext();
        mRecyclerView = view.findViewById(R.id.list);
        mRecyclerView.hasFixedSize();
        mRecyclerView.setLayoutManager(new LinearLayoutManager(context));

        leaders = new ArrayList<>();
        mRequestQueue = Volley.newRequestQueue(context);

        //call the JSON method
        parseJsonData();


    }
    public void parseJsonData() {
        String url = "https://gadsapi.herokuapp.com/api/hours";

       JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {

                        try {



                            for (int i = 0; i < response.length(); i++) {
                                JSONObject leader = response.getJSONObject(i);

                                String name = leader.getString("name");
                                int hours = leader.getInt("hours");
                                String country = leader.getString("country");
                                String badgeUrl = leader.getString("badgeUrl");

                                leaders.add(new Leaders( name,hours, country, badgeUrl));
                            }
                            leadersAdapter = new LeadersAdapter(context, leaders,false);
                            mRecyclerView.setAdapter(leadersAdapter);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                    }
                });

        mRequestQueue.add(request);

    }


}

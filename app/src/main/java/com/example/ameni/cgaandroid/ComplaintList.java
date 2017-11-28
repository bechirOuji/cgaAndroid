package com.example.ameni.cgaandroid;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ComplaintList extends android.app.Fragment {   public  static  int rounds;
    ListView listComplaint ;
    ArrayList<Complaint> complaints;
    FloatingActionButton addComplaint;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_contract_list , container, false);


        addComplaint = (FloatingActionButton) view.findViewById(R.id.addComplaint);
        addComplaint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Bundle bundle = new Bundle();
                addComplaint addFrt = new addComplaint();
                addFrt.setArguments(bundle);
                getFragmentManager().beginTransaction().replace(R.id.container, addFrt).addToBackStack(null).commit();

            }
        });




        listComplaint = (ListView)view.findViewById(R.id.complaintList);
        complaints= new ArrayList<>();
        RequestQueue mRequestQueue = Volley.newRequestQueue(getActivity());

        mRequestQueue.start();

        String url = "http://192.168.1.12:18080/cga-web/rest/com/affich";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        System.out.println(response);
                        try {

                            JSONArray array = new JSONArray(response);
                            for (int i = 0; i < array.length(); i++) {
                                JSONObject j = array.getJSONObject(i);
                                Complaint p = new Complaint(j.getInt("complaintId"),j.getString("description"),
                                        j.getString("response"));
                               complaints.add(p);
                                Log.i("complaint" , p.toString());
                                Log.i("sdffff",complaints.get(0).toString() );
                                System.out.println(p);



                            }
                            listComplaint.setAdapter(new ComplaintCustomAdapter(getActivity(), R.layout.one_contract, complaints));

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Handle error
                        System.out.println("Erreur "+error.getMessage());
                    }
                });

        mRequestQueue.add(stringRequest);

        listComplaint.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Complaint complaint = (Complaint) adapterView.getItemAtPosition(i);

                Bundle bundle  = new Bundle();
                bundle.putSerializable("complaint",complaint);
                DetailsFragment Info = new DetailsFragment();
                Info.setArguments(bundle);
                getFragmentManager().beginTransaction().replace(R.id.container,Info).addToBackStack(null).commit();


            }
        });



        return view;
    }
}

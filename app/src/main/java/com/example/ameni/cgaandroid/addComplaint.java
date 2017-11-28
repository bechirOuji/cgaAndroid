package com.example.ameni.cgaandroid;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.HashMap;


/**
 */
public class addComplaint extends Fragment {
    EditText  description, response;
    Button addComplaint;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_complaint, container, false);

        description = (EditText) view.findViewById(R.id.description);
        response = (EditText) view.findViewById(R.id.response);
        addComplaint = (Button) view.findViewById(R.id.addBtnAdd);
        Bundle bundle = getArguments();



        addComplaint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                RequestQueue mRequestQueue = Volley.newRequestQueue(getActivity());

                mRequestQueue.start();

                String URL = "http://192.168.1.12:18080/cga-web/rest/com" ;
                HashMap<String, String> params = new HashMap<String, String>();


                params.put("description", String.valueOf(description.getText()));
                params.put("response", String.valueOf(response.getText()));

                Log.i("jsonnn", (new JSONObject(params).toString()));


                JsonObjectRequest request_json = new JsonObjectRequest(Request.Method.POST,URL, new JSONObject(params),
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {

                                //Process os success response
                                Log.i("tessdfsdft", "test");
                                ComplaintList cmf = new ComplaintList();
                                Bundle bundle = new Bundle();

                                cmf.setArguments(bundle);
                                getFragmentManager().beginTransaction().replace(R.id.container, cmf).addToBackStack(null).commit();
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        VolleyLog.e("Error123: ", error.getMessage());
                        ComplaintList cmf = new ComplaintList();
                        Bundle bundle = new Bundle();

                        cmf.setArguments(bundle);
                        getFragmentManager().beginTransaction().replace(R.id.container, cmf).addToBackStack(null).commit();
                    }
                });
                mRequestQueue.add(request_json);


// add the request object to the queue to be executed


            }
        });

        return view;
    }

}

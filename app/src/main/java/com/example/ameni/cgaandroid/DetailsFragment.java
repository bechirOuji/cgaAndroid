package com.example.ameni.cgaandroid;

import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import static android.provider.ContactsContract.CommonDataKinds.Website.URL;


public class DetailsFragment extends Fragment {

    EditText  description, response;
    Button edit, delete;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, final Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.details_fragment, container, false);
        Bundle bundle = getArguments();
        final Complaint complaint = (Complaint) bundle.getSerializable("complaint");


        final RequestQueue mRequestQueue = Volley.newRequestQueue(getActivity());
        mRequestQueue.start();

        description = (EditText) view.findViewById(R.id.description);
        response = (EditText) view.findViewById(R.id.description);
        edit = (Button) view.findViewById(R.id.editComplaintBtn);
        delete = (Button) view.findViewById(R.id.deleteComplaintBtn);



        description.setText(complaint.getDescription());
        response.setText(complaint.getResponse());
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {






                Map<String, String> params = new HashMap<String, String>();


                params.put("description", String.valueOf(description.getText()));
                params.put("response", String.valueOf(response.getText()));


                Log.i("jsonnn", (new JSONObject(params).toString()));
                String url = "http://192.168.1.12:18080/cga-web/rest/com/" + complaint.getComplaintId();
                final JsonObjectRequest request_json = new JsonObjectRequest(Request.Method.PUT, url, new JSONObject(params),
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {

                                Toast.makeText(getActivity(), "Updated ", Toast.LENGTH_SHORT).show();
                                Log.i("hhh", URL);
                                ComplaintList cmf = new ComplaintList();
                                Bundle bundle = new Bundle();

                                cmf.setArguments(bundle);
                                getFragmentManager().beginTransaction().replace(R.id.container, cmf).addToBackStack(null).commit();
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getActivity(), "Updated ", Toast.LENGTH_SHORT).show();

                        Log.i("edited", URL);

                        error.getStackTrace();
                        ComplaintList cmf = new ComplaintList();
                        Bundle bundle = new Bundle();

                        cmf.setArguments(bundle);
                        getFragmentManager().beginTransaction().replace(R.id.container, cmf).addToBackStack(null).commit();
                    }


                });


// add the request object to the queue to be executed
                mRequestQueue.add(request_json);
                Log.i("mothod", String.valueOf(request_json.getMethod()));

            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = "http://192.168.1.12:18080/cga-web/rest/com/" + complaint.getComplaintId();
                StringRequest dr = new StringRequest(Request.Method.DELETE, url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                Toast.makeText(getActivity(), "Complaint Deleted", Toast.LENGTH_SHORT).show();
                                ComplaintList cmf = new ComplaintList();
                                Bundle bundle = new Bundle();

                                cmf.setArguments(bundle);
                                getFragmentManager().beginTransaction().replace(R.id.container, cmf).addToBackStack(null).commit();

                            }

                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Toast.makeText(getActivity(), "Complaint already deleted", Toast.LENGTH_SHORT).show();

                            }
                        }
                );

                mRequestQueue.add(dr);
                Log.i("mothod", String.valueOf(dr.getMethod()));

            }
        });


        return view;
    }
}

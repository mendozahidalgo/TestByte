package com.example.testbyte;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.testbyte.adapters.DetCuentaAdapter;
import com.example.testbyte.models.Statement;
import com.example.testbyte.utils.VolleyS;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link StatementFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class StatementFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private RecyclerView recDetalleCuenta;
    private VolleyS volley;
    private RequestQueue fRequestQueue;
    private Gson gson;


    public StatementFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment StatementFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static StatementFragment newInstance(String param1, String param2) {
        StatementFragment fragment = new StatementFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_statement, container, false);
        if (v != null){
            recDetalleCuenta = v.findViewById(R.id.recDetalleCuenta);
            LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
            layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            recDetalleCuenta.setHasFixedSize(true);
            recDetalleCuenta.setLayoutManager(layoutManager);
        }

        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        volley = VolleyS.getInstance(view.getContext());
        fRequestQueue = volley.getmRequestQueue();
        gson = new Gson();
        makeRequest();

    }

    private void makeRequest(){
        String url = "https://91f7b927-de7a-401b-8a73-f0fd9b35764d.mock.pstmn.io/user/statements";
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                System.out.println("++++ ejecutando onResponse ");
                parseResult(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println("++++ ejecutando onErrorResponse ");
                System.out.println(error.toString());
            }
        });

        request.setTag(this);
        request.setRetryPolicy(new DefaultRetryPolicy(60000,3,DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        fRequestQueue.add(request);
    }

    public void parseResult(JSONObject json){
        System.out.println("******************************"+json.toString());;

        try {
            JSONArray statements = json.getJSONArray("statement");
            List<Statement> lstStatements = new ArrayList<>();
            for (int i = 0 ; i < statements.length(); i++ ){
                JSONObject jsonObject = (JSONObject) statements.get(i);
                lstStatements.add(gson.fromJson(jsonObject.toString(),Statement.class));
            }
            loadDetCuenta(lstStatements);
        } catch (JSONException e) {
            e.printStackTrace();
        }


    }

    private void loadDetCuenta(List<Statement> lst){
        DetCuentaAdapter adapter = new DetCuentaAdapter(lst,getContext());
        recDetalleCuenta.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }
}
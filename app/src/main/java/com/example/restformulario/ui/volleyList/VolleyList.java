package com.example.restformulario.ui.volleyList;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.restformulario.R;
import com.example.restformulario.ui.sqlitle.WordListAdapter;
import com.example.restformulario.ui.sqlitle.WordViewModel;
import com.example.restformulario.ui.utilitario.VolleySingleton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class VolleyList extends Fragment {


    public VolleyList() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_volley_list, container, false);
        Button button = (Button) view.findViewById(R.id.button2);
        ListView listViewUsuarios = (ListView) view.findViewById(R.id.list_volley);



        button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

                //RequestQueue requestQueue = Volley.newRequestQueue(view.getContext());


                String url = "http://192.168.1.2:8081/listUsers";
                List<String> nombresUsuarios = new ArrayList<>();
                //String url ="http://www.google.com";

                Log.i("server", url);

                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        //Log.i("JSONObject", response.toString());

                        try {
                            JSONArray jsonArray = response.getJSONArray("usuarios");

                            //Log.i("JSONArray", jsonArray.toString());
                            //jsonResponses.add(email);
                            for(int i = 0; i < jsonArray.length(); i++){
                                JSONObject jsonObject = jsonArray.getJSONObject(i);
                                String nombreUsuario = jsonObject.getString("nombre");
                                nombresUsuarios.add(nombreUsuario);
                            }

                            ArrayAdapter<String> usuariosAdapter = new ArrayAdapter<String>(
                                    getActivity(),
                                    R.layout.support_simple_spinner_dropdown_item,
                                    nombresUsuarios);

                            listViewUsuarios.setAdapter(usuariosAdapter);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                    }
                });

                VolleySingleton.getInstance(getContext()).addToRequestQueue(jsonObjectRequest);
                //VolleySingleton.getInstance(mContext.getApplicationContext()).addToRequestQueue(jsonObjectRequest);
                //mQueue.add(jsonObjectRequest);
                //requestQueue.add(jsonObjectRequest);

            }
        });

        listViewUsuarios.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedItem = (String) parent.getItemAtPosition(position);

                JSONObject usuario = new JSONObject();
                try {
                    usuario.put("name", selectedItem);
                    //usuario.put("job", "Software Engineer");

                } catch (JSONException e) {
                    e.printStackTrace();
                }

                String url = "http://192.168.1.2:8081/fotoUser";

                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, usuario, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        //Log.i("JSONObject", response.toString());

                        try {
                            String nombreUsuario = response.getString("nombre");
                            String edadUsuario = response.getString("edad");
                            String fotoUsuario = response.getString("foto");

                            byte[] decodedString = Base64.decode(fotoUsuario, Base64.DEFAULT);
                            Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);

                            Toast toast = new Toast(getContext());
                            ImageView view = new ImageView(getContext());
                            view.setImageBitmap(decodedByte);
                            toast.setView(view);
                            toast.show();


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        Log.i("respuesta", response.toString());
                        //Toast.makeText(view.getContext(),selectedItem,Toast.LENGTH_SHORT).show();

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                    }
                });

                VolleySingleton.getInstance(getContext()).addToRequestQueue(jsonObjectRequest);

                //Toast.makeText(view.getContext(),selectedItem,Toast.LENGTH_SHORT).show();

            }
        });




        return view;
    }
}
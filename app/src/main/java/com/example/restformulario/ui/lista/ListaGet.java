package com.example.restformulario.ui.lista;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import com.example.restformulario.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ListaGet extends Fragment {

    private ListaGetViewModel mViewModel;

    public static ListaGet newInstance() {
        return new ListaGet();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.lista_get_fragment, container, false);
        Button button = (Button) view.findViewById(R.id.button);
        Button buttonSingleton = (Button) view.findViewById(R.id.button_singleton);
        TextView server = (TextView) view.findViewById(R.id.editTextTextPersonName);
        ListView listViewUsuarios = (ListView) view.findViewById(R.id.lista_usuarios);

        button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

                //Toast.makeText(view.getContext(),"Hello Javatpoint",Toast.LENGTH_SHORT).show();

                RequestQueue requestQueue = Volley.newRequestQueue(view.getContext());

                String url =server.getText().toString();
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

                requestQueue.add(jsonObjectRequest);

            }
        });


        listViewUsuarios.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedItem = (String) parent.getItemAtPosition(position);
                Toast.makeText(view.getContext(),selectedItem,Toast.LENGTH_SHORT).show();

            }
        });

        buttonSingleton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

                Navigation.findNavController(view).navigate(R.id.action_navigation_lista_get_to_volleyList);
                Toast.makeText(view.getContext(),"OK",Toast.LENGTH_SHORT).show();
            }
         });


        return view;

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(ListaGetViewModel.class);
        // TODO: Use the ViewModel
    }

}
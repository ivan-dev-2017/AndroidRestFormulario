package com.example.restformulario.ui.imprimirA;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.restformulario.R;
import com.example.restformulario.ui.utilitario.SharedViewModel;




public class ImprimirFragmentA extends Fragment {

    private SharedViewModel viewModel;
    private EditText editText;

    public ImprimirFragmentA() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_imprimir_a, container, false);
        editText = v.findViewById(R.id.editTextTextPersonName2);
        Button button = v.findViewById(R.id.button3);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                viewModel.setText(editText.getText());
                Log.i("click A", ";)");
            }
        });
        return v;
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_imprimir_a, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        viewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);

        viewModel.getText().observe(getViewLifecycleOwner(), new Observer<CharSequence>() {
            @Override
            public void onChanged(@Nullable CharSequence charSequence) {
                Log.i("fragment A", charSequence.toString());
                editText.setText(charSequence);
            }
        });
    }



}
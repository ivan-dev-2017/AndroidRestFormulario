package com.example.restformulario.ui.imprimirB;

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


public class ImprimirFragmentB extends Fragment {

    private SharedViewModel viewModel;
    private EditText editText;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_imprimir_b, container, false);
        editText = v.findViewById(R.id.editTextTextPersonName3);
        Button button = v.findViewById(R.id.button4);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                viewModel.setText(editText.getText());
                Log.i("click B", ";)");
            }
        });
        return v;
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_imprimir_b, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        viewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);

        viewModel.getText().observe(getViewLifecycleOwner(), new Observer<CharSequence>() {
            @Override
            public void onChanged(@Nullable CharSequence charSequence) {
                Log.i("fragment B", charSequence.toString());
                editText.setText(charSequence);
            }
        });
    }


}
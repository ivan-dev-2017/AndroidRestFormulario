package com.example.restformulario.ui.fragmentPassData;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.restformulario.R;
import com.example.restformulario.ui.imprimirA.ImprimirFragmentA;
import com.example.restformulario.ui.imprimirB.ImprimirFragmentB;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class PassDataFragment extends Fragment {

    private PassDataViewModel mViewModel;

    public static PassDataFragment newInstance() {
        return new PassDataFragment();
    }

    //El Fragment ha sido creado
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivity().getSupportFragmentManager().beginTransaction()
                .add(R.id.container_a, new ImprimirFragmentA())
                .add(R.id.container_b, new ImprimirFragmentB())
                .commit();
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.pass_data_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(PassDataViewModel.class);
        // TODO: Use the ViewModel
    }

}
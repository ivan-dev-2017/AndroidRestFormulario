package com.example.restformulario.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.restformulario.MainActivity;
import com.example.restformulario.NewWordActivity;
import com.example.restformulario.R;
import com.example.restformulario.ui.dashboard.DashboardViewModel;

import static android.provider.AlarmClock.EXTRA_MESSAGE;

public class HomeFragment extends Fragment {



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_home, container, false);

        String[] favouritefoods = {"@@@", "!!!!", "...", "..."};

        //Build adapter
        ArrayAdapter<String> favefoodadapter = new ArrayAdapter<String>(
                getActivity(), //some context for the activity
                R.layout.support_simple_spinner_dropdown_item, //layout to be displayed(create)
                favouritefoods); //strings to be diplayed



        ListView listView = (ListView) rootView.findViewById(R.id.mainListView);
        listView.setAdapter(favefoodadapter);




        return rootView;
    }






}
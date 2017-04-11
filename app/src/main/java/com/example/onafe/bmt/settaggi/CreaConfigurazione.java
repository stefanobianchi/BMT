package com.example.onafe.bmt.settaggi;

import android.app.DialogFragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

import com.example.onafe.bmt.Configurazione;
import com.example.onafe.bmt.CreazioneConfigActivity;
import com.example.onafe.bmt.CustomAdapter;
import com.example.onafe.bmt.R;
import com.example.onafe.bmt.TimePicker;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by onafe on 29/03/2017.
 */

public class CreaConfigurazione extends Fragment {



    public CreaConfigurazione() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_configurazione, container, false);


        FloatingActionButton fab = (FloatingActionButton) v.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            Configurazione c = new Configurazione("",R.color.blue,"","");
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(
                        getActivity(),
                        CreazioneConfigActivity.class
                );
                Bundle extras = new Bundle();
                extras.putParcelable("Configurazione", c);
                intent.putExtras(extras);
                startActivity(intent);
            }
        });

        ListView listView = (ListView)v.findViewById(R.id.listViewConfigurazione);
        List list = new LinkedList();
        list.add(new Configurazione("Ordinario",R.color.blue,"08:00","09:00 - 18:00"));
        list.add(new Configurazione("Straoridinario 1 ora pomeriggio",R.color.orange,"09:00","09:00 - 19:00"));
        list.add(new Configurazione("Permesso 1 ora mattina",R.color.yellow,"07:00","10:00 - 18:00"));
        list.add(new Configurazione("Ferie",R.color.green,"",""));
        list.add(new Configurazione("Malattia",R.color.red,"",""));
        CustomAdapter adapter = new CustomAdapter( getActivity().getApplicationContext(), R.layout.row, list);
        AdapterView.OnItemClickListener clickListener = new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapter, View view,
                                    int position, long id) {
                Configurazione c = (Configurazione) adapter.getItemAtPosition(position);
                Intent intent = new Intent(
                        getActivity(),
                        CreazioneConfigActivity.class
                );
                Bundle extras = new Bundle();
                extras.putParcelable("Configurazione", c);
                intent.putExtras(extras);
                startActivity(intent);
            }
        };
        listView.setOnItemClickListener(clickListener);
        listView.setAdapter(adapter);
        return v;
    }


}

package com.example.onafe.bmt.settaggi;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.onafe.bmt.Configurazione;
import com.example.onafe.bmt.CreazioneConfigActivity;
import com.example.onafe.bmt.CustomAdapter;
import com.example.onafe.bmt.DbHelper;
import com.example.onafe.bmt.R;
import com.example.onafe.bmt.SendXml;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by onafe on 30/03/2017.
 */

public class FragmentRiepilogo extends Fragment {

    public FragmentRiepilogo() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_riepilogo, container, false);


        ListView listView = (ListView)v.findViewById(R.id.listViewRiepilogo);
        List list = new LinkedList();
        list.add(new Configurazione("Ordinario",R.color.blue,"08:00","09:00 - 18:00"));
        list.add(new Configurazione("Straoridinario 1 ora pomeriggio",R.color.orange,"09:00","09:00 - 19:00"));
        list.add(new Configurazione("Permesso 1 ora mattina",R.color.yellow,"07:00","10:00 - 18:00"));
        list.add(new Configurazione("Ferie",R.color.green,"",""));
        list.add(new Configurazione("Malattia",R.color.red,"",""));
        list.add(new Configurazione("Altro",R.color.white,"",""));
        CustomAdapter adapter = new CustomAdapter( getActivity().getApplicationContext(), R.layout.row, list);
        AdapterView.OnItemClickListener clickListener = new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapter, View view,
                                    int position, long id) {
                final View v2=view;

                DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        switch (which){
                            case DialogInterface.BUTTON_POSITIVE:
                                Toast.makeText(getActivity(), "Rapportino giornaliero inviato!", Toast.LENGTH_SHORT).show();
                                SendXml send = new SendXml();
                                try {
                                    send.sendXmlToServer();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                                break;

                            case DialogInterface.BUTTON_NEGATIVE:
                                Toast.makeText(getActivity(), "Rapportino giornaliero non inviato!", Toast.LENGTH_SHORT).show();
                                break;
                        }
                    }
                };

                Configurazione c = (Configurazione) adapter.getItemAtPosition(position);

                if(position==5){
                    Intent intent = new Intent(
                            getActivity(),
                            CreazioneConfigActivity.class
                    );
                    Bundle extras = new Bundle();
                    extras.putParcelable("Configurazione", c);
                    intent.putExtras(extras);
                    startActivity(intent);
                }else{
                    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                    builder.setMessage("Inviare "+c.getDenominazione()+" come orario giornaliero?").setPositiveButton("Sì", dialogClickListener)
                            .setNegativeButton("No", dialogClickListener).show();
                }


            }
        };


        listView.setOnItemClickListener(clickListener);
        listView.setAdapter(adapter);
        // Inflate the layout for this fragment
        return v;
    }

}

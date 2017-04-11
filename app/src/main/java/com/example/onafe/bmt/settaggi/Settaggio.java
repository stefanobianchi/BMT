package com.example.onafe.bmt.settaggi;

import android.app.DialogFragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Spinner;

import com.example.onafe.bmt.Configurazione;
import com.example.onafe.bmt.R;
import com.example.onafe.bmt.TimePicker;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by onafe on 23/03/2017.
 */

public class Settaggio extends Fragment {
  public  EditText denominazione,pausa;
  public  TextView entrata,uscita;
  public  Spinner assenze;
  public  ImageView cancel;

    public Settaggio() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.settings, container, false);
        denominazione = (EditText) v.findViewById(R.id.editTextDenominazione);
        entrata = (TextView) v.findViewById(R.id.idEntrata);
        pausa = (EditText) v.findViewById(R.id.editTextPausa);
        cancel = (ImageView) v.findViewById(R.id.imageViewCancel);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pausa.setText("");
            }
        });
        assenze = (Spinner) v.findViewById(R.id.spinnerAssenze);
        entrata.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               showTimePickerDialog(view);
           }
       }
        );
        uscita = (TextView) v.findViewById(R.id.idUscita);
        uscita.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               showTimePickerDialogUscita(view);
           }
       }
        );

        Intent intent = getActivity().getIntent();
        Bundle extras = intent.getExtras();
        Configurazione  config = extras.getParcelable("Configurazione");
        onChange(config);
        return v;
    }


    public void onChange(Configurazione config){
        denominazione.setText(config.getDenominazione());
    }

    public void showTimePickerDialog(View v) {
        FragmentManager fm = getActivity().getFragmentManager();
        TimePicker newFragment = new TimePicker(R.id.idEntrata);
        newFragment.show(fm, "timePicker");
    }

    public void showTimePickerDialogUscita(View v) {
        FragmentManager fm = getActivity().getFragmentManager();
        DialogFragment newFragment = new TimePicker(R.id.idUscita);
        newFragment.show(fm, "timePicker");
    }

    public Configurazione getValori(){
        Configurazione c = new Configurazione();
        try {
            c.setDenominazione(denominazione.getText().toString());
            c.setTempoOre(entrata.getText().toString()+" - "+uscita.getText().toString());
            DateFormat df = new SimpleDateFormat("hh:mm");
            Date date1 = df.parse(entrata.getText().toString());
            Date date2 = df.parse(uscita.getText().toString());
            long diff = date2.getTime() - date1.getTime();
            c.setNumeroOre(String.valueOf(diff));
            c.setEntrata(entrata.getText().toString());
            c.setUscita(uscita.getText().toString());
            c.setPausa(Integer.parseInt(pausa.getText().toString()));
            c.setAssenza(assenze.toString());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return c;
    }

    public String checkValori(){
        String msg="";
        if(denominazione.getText().toString()==""){
            msg="Inserire la denominazione";
        }else if (entrata.getText().toString()==""){
            msg="Inserire l'orario di entrata";
        }else if (uscita.getText().toString()==""){
            msg="Inserire l'orario di uscita";
        }
        return msg;
    }


}

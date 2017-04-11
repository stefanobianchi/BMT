package com.example.onafe.bmt;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by onafe on 21/03/2017.
 */

public class CustomAdapter extends ArrayAdapter<Configurazione> {

    public CustomAdapter(Context context, int textViewResourceId,
                         List objects) {
        super(context, textViewResourceId, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) getContext()
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(R.layout.row, null);
        TextView colore = (TextView)convertView.findViewById(R.id.textView);
        TextView descrizione = (TextView)convertView.findViewById(R.id.textView5);
        TextView descrizioneOre = (TextView)convertView.findViewById(R.id.textView4);
        TextView numeroOre = (TextView)convertView.findViewById(R.id.textView6);
        Configurazione c = getItem(position);
        colore.setBackgroundResource(c.getColore());
        descrizione.setText(c.getDenominazione());
        descrizioneOre.setText(c.getTempoOre());
        numeroOre.setText(c.getNumeroOre());
        return convertView;
    }
}

package com.example.onafe.bmt;

import android.app.DialogFragment;
import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.onafe.bmt.settaggi.Colori;
import com.example.onafe.bmt.settaggi.Settaggio;

import java.util.ArrayList;
import java.util.List;

public class CreazioneConfigActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private TabLayout tabLayout;
    private Configurazione config;
    public LinearLayout top;
    public TextView titolo,orarioConf,oraConf;
    public ImageButton creaConfig;
    private Settaggio settaggio= new Settaggio();
    DbHelper mydb;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creazione_config);
        mydb= new DbHelper(CreazioneConfigActivity.this);
        top = (LinearLayout)findViewById(R.id.topColor);
        titolo=(TextView)findViewById(R.id.textViewTitoloConf);
        creaConfig=(ImageButton)findViewById(R.id.imageViewCreaConfig);
        orarioConf=(TextView)findViewById(R.id.textViewOrarioConf);
        oraConf=(TextView)findViewById(R.id.textViewOreConf);
        Intent intent = getIntent();
        final Bundle extras = intent.getExtras();

        creaConfig.setOnClickListener(new View.OnClickListener()  {
            @Override
            public void onClick(View v) {
          /*     String msg=  settaggio.checkValori();
                if(msg != ""){
                    Toast.makeText(v.getContext(),msg, Toast.LENGTH_SHORT).show();
                }else{*/
                    Configurazione conf = settaggio.getValori();
                    mydb.insert(-1, conf.getDenominazione(),R.color.blue,conf.getEntrata(),conf.getUscita(),conf.getPausa(),conf.getAssenza(),conf.getOraAssenza());
                }
            //}
        });

        if (extras != null) {
            config= extras.getParcelable("Configurazione");
            top.setBackgroundColor(getResources().getColor(config.getColore()));
            titolo.setText(config.getDenominazione());
            orarioConf.setText(config.getTempoOre());
            oraConf.setText(config.getNumeroOre());
        }

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        ViewPagerAdapter adapterchange=  setupViewPager(viewPager);

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);



    }



    private ViewPagerAdapter setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new Settaggio(), "Settaggio");
        adapter.addFragment(new Colori(), "Colore");
        viewPager.setAdapter(adapter);
        return adapter;
    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);

        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }

    @Override
    protected void onStart(){
        super.onStart();
        mydb.openDb();
    }

    @Override
    protected void onStop(){
        super.onStop();
        mydb.close();
    }


}

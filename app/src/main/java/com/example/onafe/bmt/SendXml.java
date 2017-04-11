package com.example.onafe.bmt;

import android.os.AsyncTask;
import android.os.Environment;
import android.util.Log;
import android.util.Xml;

import org.xmlpull.v1.XmlSerializer;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by onafe on 05/04/2017.
 */

public class SendXml {


    public SendXml() {
    }

    public void sendXmlToServer() throws IOException {
        new MyTask().execute();

    }

      public void inviaFile(){


    }

    private class MyTask extends AsyncTask<Void, Void, Void> {

        String textResult;

        @Override
        protected Void doInBackground(Void... params) {

            HttpURLConnection conn =null;

            try {
                URL url = new URL("http://192.168.220.210:8080/TimesheetTest/services/TimesheetXml");
                conn= (HttpURLConnection) url.openConnection();
                conn.connect();
                conn.setReadTimeout(10000);
                conn.setConnectTimeout(15000);
                conn.setRequestMethod("POST");


                OutputStream output = new BufferedOutputStream(conn.getOutputStream());


                Calendar c = Calendar.getInstance();
                Date newDate = c.getTime();
                SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
                String date = format.format(newDate);


                //we have to bind the new file with a FileOutputStream

                //we create a XmlSerializer in order to write xml data
                XmlSerializer serializer = Xml.newSerializer();
                try {
                    //we set the FileOutputStream as output for the serializer, using UTF-8 encoding
                    serializer.setOutput(output, "UTF-8");
                    //Write <?xml declaration with encoding (if encoding not null) and standalone flag (if standalone not null)
                    serializer.startDocument(null, Boolean.valueOf(true));
                    //set indentation option
                    serializer.setFeature("http://xmlpull.org/v1/doc/features.html#indent-output", true);
                    //start a tag called "root"
                    serializer.startTag(null, "Dipendente");
                    serializer.startTag(null, "Nome");
                    serializer.text("Stefano");
                    serializer.endTag(null, "Nome");
                    serializer.startTag(null, "Cognome");
                    serializer.text("Bianchi");
                    serializer.endTag(null,"Cognome");
                    serializer.startTag(null, "CodFiscale");
                    serializer.text("BNCSFN92H20H501V");
                    serializer.endTag(null,"CodFiscale");
                    serializer.startTag(null, "Data");
                    serializer.text(date);
                    serializer.endTag(null,"Data");
                    serializer.startTag(null, "OreLavorate");
                    serializer.text("ORD H 8");
                    serializer.endTag(null,"OreLavorate");
                    serializer.endTag(null,"Dipendente");
                    serializer.endDocument();
                    //write xml data into the FileOutputStream
                    serializer.flush();
                    //finally we close the file stream
                } catch (Exception e) {
                    Log.e("Exception","error occurred while creating xml file");
                }

                output.flush();
            }catch(Exception e){
                Log.e("Exception","error occurred while creating xml file");
                conn.disconnect();
            }
            finally {
                conn.disconnect();
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {


        }
    }


}

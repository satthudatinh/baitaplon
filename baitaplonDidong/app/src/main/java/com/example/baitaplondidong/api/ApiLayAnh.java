package com.example.baitaplondidong.api;

import android.os.AsyncTask;

import com.example.baitaplondidong.interfaces.LayAnhVe;
import com.example.baitaplondidong.interfaces.LayTruyenVe;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import com.squareup.okhttp.ResponseBody;

import java.io.IOException;

public class ApiLayAnh extends AsyncTask<Void,Void,Void> {
    String data, idChap;
    LayAnhVe LayAnhVe;

    public ApiLayAnh(LayAnhVe LayAnhVe, String idChap) {
        this.LayAnhVe = LayAnhVe;
        this.idChap = idChap;
        this.LayAnhVe.batDau();
    }

    @Override
    protected Void doInBackground(Void... voids) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
//                .url("http://myjson.dit.upm.es/api/bins/33rn")
                .url("http://datalaptrinhungdungdidong.000webhostapp.com/layAnh.php?idChap="+idChap)
                .build();
        data = null;
        try {
            Response response = client.newCall(request).execute();
            ResponseBody body = response.body();
            data = body.string();
        }catch (IOException e){
            data = null;
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void unused) {
        if(data == null){
            this.LayAnhVe.biLoi();
        }
        else{
            this.LayAnhVe.ketThuc(data);
        }
    }
}
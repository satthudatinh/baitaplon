package com.example.baitaplondidong.api;

import android.os.AsyncTask;

import com.example.baitaplondidong.interfaces.LayChapVe;
import com.example.baitaplondidong.interfaces.LayTruyenVe;
import com.example.baitaplondidong.object.TruyenTranh;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import com.squareup.okhttp.ResponseBody;

import java.io.IOException;

public class ApiChapTruyen extends AsyncTask<Void,Void,Void> {
    String data, idTruyen;
    LayChapVe layChapVe;
    TruyenTranh truyenTranh;


    public ApiChapTruyen(TruyenTranh truyenTranh, String idTruyen) {
        this.truyenTranh = truyenTranh;
        this.idTruyen = idTruyen;
    }

    public ApiChapTruyen(LayChapVe layChapVe, String idTruyen) {
        this.layChapVe = layChapVe;
        this.layChapVe.batDau();
        this.idTruyen = idTruyen;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                //.url("http://myjson.dit.upm.es/api/bins/c0r7")
                .url("http://datalaptrinhungdungdidong.000webhostapp.com/layChap.php?id=" + idTruyen)
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
            this.layChapVe.biLoi();
        }
        else{
            this.layChapVe.ketThuc(data);
        }
    }
}

package com.elkin.challengeappgate.data.remote.service;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;

import javax.net.ssl.HttpsURLConnection;

public class HttpService {

    private String url;

    public Message requestPost(String url, HashMap<String, String> params) {
        this.url = url;
        return sendPost(params);
    }

    public Message requestGet(String url) {
        this.url = url;
        return sendGet();
    }

    private Message sendGet(){
        Message message = new Message();
        Bundle bundle = new Bundle();
        String result = "";
        try{
            URL uri = new URL(url);
            HttpsURLConnection connection = (HttpsURLConnection) uri.openConnection();
            connection.setRequestMethod("GET");

            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                InputStream inputStream = new BufferedInputStream(connection.getInputStream());
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
                StringBuilder stringBuilder = new StringBuilder();

                String line;

                while ((line = reader.readLine()) != null) {
                    stringBuilder.append(line);
                }

                result = stringBuilder.toString();

                inputStream.close();
                reader.close();
                connection.disconnect();

                bundle.putString("error", "false");
                bundle.putString("body", result);
            }else{
                bundle.putString("error", "true");
                bundle.putString("body", connection.getResponseMessage());
            }
            message.setData(bundle);
        }catch (Exception exception){
            bundle.putString("error", "true");
            bundle.putString("body", exception.getMessage());
            message.setData(bundle);
        }
        return message;
    }

    private Message sendPost(HashMap<String, String> params){
        Message message = new Message();
        Bundle bundle = new Bundle();
        String result = "";
        try {
            URL apiUrl = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) apiUrl.openConnection();
            connection.setRequestMethod("POST");
            connection.setDoOutput(true);

            OutputStream outputStream = connection.getOutputStream();
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
            writer.write(getPostDataString(params));

            writer.flush();
            writer.close();
            outputStream.close();
            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                InputStream inputStream = new BufferedInputStream(connection.getInputStream());
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
                StringBuilder stringBuilder = new StringBuilder();
                String line;

                while ((line = reader.readLine()) != null) {
                    stringBuilder.append(line);
                }

                result = stringBuilder.toString();

                inputStream.close();
                reader.close();
                connection.disconnect();

                bundle.putString("error", "false");
                bundle.putString("body", result);
            }else{
                bundle.putString("error", "true");
                bundle.putString("body", connection.getResponseMessage());
            }
            message.setData(bundle);
        } catch (Exception exception) {
            bundle.putString("error", "true");
            bundle.putString("body", exception.getMessage());
            message.setData(bundle);
        }

        return message;
    }

    private String getPostDataString(HashMap<String, String> postData) throws UnsupportedEncodingException {
        StringBuilder result = new StringBuilder();
        boolean first = true;

        for (HashMap.Entry<String, String> entry : postData.entrySet()) {
            if (first) {
                first = false;
            } else {
                result.append("&");
            }

            result.append(URLEncoder.encode(entry.getKey(), "UTF-8"));
            result.append("=");
            result.append(URLEncoder.encode(entry.getValue(), "UTF-8"));
        }

        return result.toString();
    }
}

package com.example.android.booklisting;

import android.util.Log;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public final class BookJSON {
    private static final String Log_tag = BookJSON.class.getSimpleName();

    private BookJSON() {
    }
    public static List<Book> fetchBookData(String requestUrl){
        URL url =createUrl(requestUrl);

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        String jsonResponse = null;
        try{
            jsonResponse=makeHTTPRequest(url);
        }catch (IOException e){
            //Log.e(Log_tag,"Error making HTTP request",e);
        }
        List<Book> books= extractBookDataJson(jsonResponse);
        return books;
    }

    public static URL createUrl(String stringUrl) {
        URL url = null;
        try {
            url = new URL(stringUrl);
        } catch (MalformedURLException e) {
            //Log.e(Log_tag, "Can't create the url", e);
        }
        return url;
    }

    private static String readFromStream(InputStream inputStream) throws IOException {
        StringBuilder output= new StringBuilder();
        if(inputStream !=null){
            InputStreamReader inputReader=new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader reader= new BufferedReader(inputReader);
            String line = reader.readLine();
            while (line != null){
                output.append(line);
                line= reader.readLine();
            }
        }
        return output.toString();
    }

    private static String makeHTTPRequest(URL url) throws IOException {
        String jsonResponse = "";
        if (url == null) {
            return jsonResponse;
        }
        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;
        try {
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.setReadTimeout(10400);
            urlConnection.setConnectTimeout(15000);
            urlConnection.connect();
            if (urlConnection.getResponseCode() == 200) {
                inputStream = urlConnection.getInputStream();
                jsonResponse = readFromStream(inputStream);
            } else {
                //Log.e(Log_tag, "Error response code" + urlConnection.getResponseCode());
            }
        } catch (IOException e) {
            //Log.e(Log_tag, "Error retrieving json", e);
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (inputStream != null) {
                inputStream.close();
            }
        }
        return jsonResponse;
    }

    private static List<Book> extractBookDataJson(String Json){

        List<Book> books=new ArrayList<>();
        try {
            JSONObject baseJsonResponse = new JSONObject(Json);
            JSONArray itemsArray = baseJsonResponse.getJSONArray("items");
            for(int i=0;i<itemsArray.length();i++){
                JSONObject book=itemsArray.getJSONObject(i);
                JSONObject volInfo=book.getJSONObject("volumeInfo");
                String title = volInfo.optString("title");
                JSONArray author= volInfo.optJSONArray("authors");
                String authors="";
                int k = 0;
                if(author != null)
                    k=author.length();
                for(int j=0;j<k;j++){
                    String s=author.optString(j);
                    authors +=s;
                    if(j<k-1){
                        authors=", ";
                    }
                }
                Book b = new Book(title,authors,"URL");
                books.add(b);
                Log.e(title,authors);
            }
        }catch (JSONException e){

            //Log.e(Log_tag,"Problem fetching results",e);
        }
        return books;

    }
}
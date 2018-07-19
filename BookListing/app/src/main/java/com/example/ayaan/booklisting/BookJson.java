package com.example.ayaan.booklisting;

import android.util.Log;

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

import static android.R.attr.author;

/**
 * Created by AYAAN on 4/9/2017.
 */

public final class BookJson{
    private static final String LOG_GET = BookJson.class.getSimpleName();
    private static URL createUrl(String urlstring){
        URL url = null;
        try {
            url = new URL(urlstring);
        } catch (MalformedURLException e) {
            Log.e(LOG_GET,"Cannot Fetch Url");
        }
        return url;
    }
    private static String readString(InputStream inputStream)throws IOException{
        StringBuilder stringBuilder = new StringBuilder();
        if (inputStream != null){
            InputStreamReader reader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader buffered = new BufferedReader(reader);
            String line = buffered.readLine();
            while (line!= null){
                stringBuilder.append(line);
                line = buffered.readLine();
            }
        }
        Log.e("Input","Stream");
        return stringBuilder.toString();
    }
    private static String httpRequest(URL url)throws IOException{
        String jsonResponse="";
        if (url == null){
            Log.e(LOG_GET,"URL is null");
            return jsonResponse;
        }
        HttpURLConnection connection = null;
        InputStream inputStream = null;
        try {
            connection = (HttpURLConnection)url.openConnection();
            connection.setRequestMethod("GET");
            connection.setReadTimeout(10000);
            connection.setConnectTimeout(20000);
            connection.connect();
            int response = connection.getResponseCode();
            if (response == 200){
                inputStream =connection.getInputStream();
                jsonResponse = readString(inputStream);
            }
            else {
                Log.e(LOG_GET,"Error code response or input stream null"+response);
            }
        }
        catch (IOException e){
            Log.e(LOG_GET,"Problem ");
        }
        finally {
            if (connection != null){
                connection.disconnect();
            }
            if (inputStream!=null){
                inputStream.close();
            }
        }
        Log.e("Read String","Before Json Result");
        return jsonResponse;
    }
    public static List<Book> extractBookData(String jsonResponse){
        Log.e("Extract Book Data","---");
        List<Book> book= new ArrayList<>();
        try {
            JSONObject base = new JSONObject(jsonResponse);
            JSONArray items = base.getJSONArray("items");
            for (int i=0;i<items.length();i++){
                JSONObject itemarray = items.optJSONObject(i);
                JSONObject volume = itemarray.optJSONObject("volumeInfo");
                String title = volume.optString("title");
                JSONArray authorarray = volume.getJSONArray("authors");
                String authors ="";
                int l=authorarray.length();
                for(int j=0;j<l;j++) {
                    String a = authorarray.getString(j);
                    authors +=a;
                    if(j<l-1){
                    authors+=", ";}
                }              // String authors = volume.optString("authors");
//                int l;
//                l= authors.length();
//                authors.substring(2,l-1);
//                l=0;
                //String url = itemarray.getString("webReaderLink");
                Book books = new Book(title,authors,"URL");
                book.add(books);
                Log.e(title,authors);
            }
        } catch (JSONException e) {
            Log.e(LOG_GET,"Problem in Json Results");
        }
        return book;
    }
        public static List<Book> fetchBookData(String requestUrl){
            //List<Book> books = new ArrayList<>();
            URL url = createUrl(requestUrl);
            String jsonResponse = null;
            try {
                jsonResponse = httpRequest(url);
            } catch (IOException e) {
                Log.e(LOG_GET,"Error making http request");
            }
            List<Book> book = extractBookData(jsonResponse);
            Log.e("Books returned","----");
            return book;
        }


}

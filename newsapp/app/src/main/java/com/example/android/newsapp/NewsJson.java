package com.example.android.newsapp;

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

public class NewsJson {
    private static final String LOG_TAG=NewsJson.class.getSimpleName();

    public static URL createUrl(String stringUrl){
        URL url= null;
        try{
            url = new URL(stringUrl);
        }
        catch (MalformedURLException e){
            Log.e(stringUrl,"url fetched");
        }
        Log.e("URL is : ", url.toString());
        return url;
    }

    public static List<News> fetchNewsData(String Loader_url){
        URL url = createUrl(Loader_url);
        String JsonResponse = null;
        try{
            JsonResponse=httpRequest(url);
            Log.e(JsonResponse,"JSON");
        }
        catch (IOException e){

        }

        List<News> news = extractNewsData(JsonResponse);
        return news;
    }

    public static String readString(InputStream inputStream)throws IOException{
        StringBuilder stringBuilder = new StringBuilder();
        if(inputStream!=null){
            InputStreamReader reader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader bufferedReader= new BufferedReader(reader);
            String b=bufferedReader.readLine();
            while(b!=null){
                stringBuilder.append(b);
                b=bufferedReader.readLine();
            }
        }
        return stringBuilder.toString();
    }
    public static String httpRequest(URL url) throws IOException{
        String JsonResponse="";
        if(JsonResponse==null){
            return JsonResponse;
        }
        HttpURLConnection connection = null;
        InputStream inputStream = null;
        try{
            connection=(HttpURLConnection)url.openConnection();
            connection.setRequestMethod("GET");
            connection.setReadTimeout(15000);
            connection.setConnectTimeout(20000);
            connection.connect();
            int responseCode;
            responseCode = connection.getResponseCode();
            Log.e("Response CODE", Integer.toString(responseCode));
            if(responseCode==200){
                inputStream = connection.getInputStream();
                JsonResponse=readString(inputStream);
                Log.e("input stream", JsonResponse);
            }
            else {
                Log.e(LOG_TAG, "Error response code" + connection.getResponseCode());
            }
        }
        catch (IOException e){
            Log.e(LOG_TAG, "Error retrieving json", e);
        }
        finally {
            if (connection != null) {
                connection.disconnect();
            }
            if (inputStream != null) {
                inputStream.close();
            }
        }
        return JsonResponse;
    }
    public static List<News> extractNewsData(String JsonResponse){
        List<News>news = new ArrayList<>();
        try{
            //Log.e(JsonResponse, "hi");
            JSONObject base= new JSONObject(JsonResponse);
            JSONObject response = base.getJSONObject("response");
            JSONArray array=response.getJSONArray("results");

            for(int i=0;i<array.length();i++){
                //Log.e(String.valueOf(array),"inside json array");
                JSONObject result=array.getJSONObject(i);
                String title=result.optString("webTitle");
                String url=result.optString("webUrl");
                String section=result.optString("sectionName");
                JSONArray arrayAuthor = result.getJSONArray("tags");
                String authorNames = "";
                for(int j=0;j<arrayAuthor.length();j++) {
                    JSONObject nameess = arrayAuthor.getJSONObject(j);
                    String firstName = nameess.optString("firstName");
                    String lastName = nameess.optString("lastName");
                    String name = firstName + " " + lastName;
                    authorNames = authorNames + name + ", ";

//                            if (j < arrayAuthor.length() - 1) {
//                               authorNames = ", ";
//                            }
                }
                authorNames = authorNames.replaceAll(", $", "");
                        //Log.e(name, authorNames.toString() );
                        News n = new News(section, title, url, authorNames);
                        news.add(n);
                }

        }
        catch(JSONException e){
            Log.e(e.toString(),"problem fetching results");
        }
        return news;
    }
}

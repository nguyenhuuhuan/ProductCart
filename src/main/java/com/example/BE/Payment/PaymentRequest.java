package com.example.BE.Payment;

import com.example.BE.utils.LogUtils;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.RestController;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class PaymentRequest {


    private static HttpURLConnection httpURLConnection;

    static BufferedReader bufferedReader;

    static String line;

    public PaymentRequest(){};

    public static String sendToMoMo(String endpoint, String payload) {
        StringBuffer responseBuffer = new StringBuffer();

        try {
            URL url = new URL(endpoint);

            httpURLConnection = (HttpURLConnection) url.openConnection();

            var postData = payload;

//            var data = URLEncoder.encode(postData, StandardCharsets.UTF_8);
            byte[] out = postData.toString().getBytes(StandardCharsets.UTF_8);

            httpURLConnection.setRequestProperty("Content-Type", "application/json; utf-8");

            httpURLConnection.setRequestMethod("POST");

            httpURLConnection.setDoOutput(true);

            httpURLConnection.setConnectTimeout(10000);

            httpURLConnection.setReadTimeout(10000);

            int status = httpURLConnection.getResponseCode();
            System.out.println(status);
//            try(OutputStream os = httpURLConnection.getOutputStream()) {
//                os.write(out);
//            }
            if(status > 299){
                bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getErrorStream()));
                while ((line = bufferedReader.readLine()) != null){
                    responseBuffer.append(payload);
                }
                bufferedReader.close();
            }else{
                bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
                while ((line = bufferedReader.readLine()) != null){
                    responseBuffer.append(payload);
                }

                bufferedReader.close();
            }
            return responseBuffer.toString();
        } catch (MalformedURLException e) {
            e.printStackTrace();
            LogUtils.error("[ExecuteSendToMoMo] "+ e);
        } catch (IOException e){
            e.printStackTrace();
            LogUtils.error("[ExecuteSendToMoMo] "+ e);
        } finally {
            httpURLConnection.disconnect();
        }

        return null;
    }
    public static String uploadToServer(String endpoint, String payload) throws IOException, JSONException, JSONException {

        URL url = new URL(endpoint);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setConnectTimeout(5000);
        conn.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
        conn.setDoOutput(true);
        conn.setDoInput(true);
        conn.setRequestMethod("POST");

        OutputStream os = conn.getOutputStream();
        os.write(payload.getBytes("UTF-8"));
        os.close();

        // read the response
        InputStream in = new BufferedInputStream(conn.getInputStream());
        String result = org.apache.commons.io.IOUtils.toString(in, "UTF-8");

        in.close();
        conn.disconnect();

        return result;
    }
}

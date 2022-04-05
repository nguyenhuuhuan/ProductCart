package com.example.BE.Service;

import com.example.BE.Config.PaymentConfig;
import com.example.BE.DTO.PaymentDTO;
import com.example.BE.Payment.PaymentRequest;
import com.example.BE.Security.Encoder;
import com.example.BE.Service.CartService;
import com.example.BE.utils.LogUtils;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.google.gson.Gson;
import netscape.javascript.JSObject;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.net.http.HttpClient;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class PaymentService implements PaymentServiceImp{

    private CartService cartService;

    private Encoder encoder;

    @Override
    public String Payment(){
        try {
            UUID uniqueKey = UUID.randomUUID();
            String endPoint = PaymentConfig.ENDPOINT;
            String hostname = PaymentConfig.HOSTNAME;
            String partnerCode = PaymentConfig.PARTNERCODE;
            String accessKey = PaymentConfig.ACCESSKEY;
            String secretKey = PaymentConfig.SECRETKEY;
            String orderInfo = PaymentConfig.ORDERINFO;
            String returnUrl = PaymentConfig.RETURNURL;
            String notifyUrl = PaymentConfig.NOTIFYURL;
            String amount = "20000";
            String orderId = uniqueKey.toString();
            String requestId = uniqueKey.toString();
            String extraData = "";

            String rawHash = "partnerCode="+
                    partnerCode + "&accessKey="+
                    accessKey+ "&requestId=" +
                    requestId+ "&amount=" +
                    amount + "&orderId="+
                    orderId + "&orderInfo="+
                    orderInfo + "&returnUrl="+
                    returnUrl + "&notifyUrl=" +
                    notifyUrl + "&extraData="+
                    extraData;

            String signature = Encoder.signHmacSHA256(rawHash, secretKey);

            PaymentDTO paymentDTO = new PaymentDTO(
                    partnerCode,
                    accessKey,
                    requestId,
                    amount,
                    orderId,
                    orderInfo,
                    returnUrl,
                    notifyUrl,
                    "captureMoMoWallet",
                    signature
            );

            Gson gson = new Gson();
            String message = gson.toJson(paymentDTO);

            String response = PaymentRequest.uploadToServer(endPoint, message.toString());
            JSONObject jsonObject = new JSONObject(response);
            System.out.println(jsonObject.get("payUrl").toString());

            return jsonObject.get("payUrl").toString();
        }catch (Exception e){
            e.getMessage();
            return e.getMessage();
        }
    }

     public String returnUrl(String url) throws UnsupportedEncodingException, NoSuchAlgorithmException, InvalidKeyException {

        url = url.substring(url.toString().indexOf("?")+1);

        url = url.substring(0, url.toString().indexOf("signature")-1);
         System.out.println(url);
        url = Encoder.decode64(url);
        String signature = Encoder.signHmacSHA256(url, PaymentConfig.SECRETKEY);
        return signature;
     }

}

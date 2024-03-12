package com.mkarani.stkpush.service;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.mkarani.stkpush.config.MpesaConfig;
import com.mkarani.stkpush.dto.AccessTokenResponse;
import com.mkarani.stkpush.utils.HelperUtility;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
@Service
@Slf4j
public class PaymentServiceImpl implements PaymentService{
    private final MpesaConfig mpesaConfig;
    private final OkHttpClient okHttpClient;
    private final ObjectMapper objectMapper;

    @Autowired
    public PaymentServiceImpl(MpesaConfig mpesaConfig, OkHttpClient okHttpClient, ObjectMapper objectMapper) {
        this.mpesaConfig = mpesaConfig;
        this.okHttpClient = okHttpClient;
        this.objectMapper = objectMapper;
    }

    @Override
    public AccessTokenResponse getAccessToken() throws IOException {
        String encodedCredentials = HelperUtility.toBase64String(String.format("%s:%s", mpesaConfig.getConsumerKey(),
                mpesaConfig.getConsumerSecret()));
        log.info("Sending request to OAuth endpoint: {}", mpesaConfig.getOauthEndpoint());
        Request request = new Request.Builder()
                .url(mpesaConfig.getOauthEndpoint())
                .method("GET", null)
                .addHeader("Authorization", "Basic cFJZcjZ6anEwaThMMXp6d1FETUxwWkIzeVBDa2hNc2M6UmYyMkJmWm9nMHFRR2xWOQ==")
                .build();
        Response response = okHttpClient.newCall(request).execute();
        log.info("Response Status Code: {}", response.code());
        String responseData = response.body().string();
        log.debug("Response Data: {}", responseData);
        return objectMapper.readValue(responseData, AccessTokenResponse.class);
    }
}



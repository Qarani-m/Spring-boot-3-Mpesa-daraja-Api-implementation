package com.mkarani.stkpush.service;

import com.mkarani.stkpush.dto.AccessTokenResponse;

import java.io.IOException;

public interface PaymentService {
    AccessTokenResponse getAccessToken() throws IOException;

}

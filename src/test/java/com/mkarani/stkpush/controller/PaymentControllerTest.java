package com.mkarani.stkpush.controller;

import com.mkarani.stkpush.dto.AccessTokenResponse;
import com.mkarani.stkpush.service.PaymentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.io.IOException;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.mockito.Mockito.*;

@WebMvcTest(PaymentController.class)
class PaymentControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PaymentService paymentService; // Mocking the service layer


    @BeforeEach
    void setUp() {
    }


    @Test
    void getAccessToken() throws Exception {
        // Mocked AccessTokenResponse
        AccessTokenResponse mockedResponse = new AccessTokenResponse();
        mockedResponse.setExpiresIn("3599");

        // Mock the behavior of the paymentService.getAccessToken() method
        when(paymentService.getAccessToken()).thenReturn(mockedResponse);

        // Perform GET request to the controller endpoint
        mockMvc.perform(get("/api/payment/token"))
                .andExpect(status().isOk()) // Assert HTTP status code 200
                .andExpect(jsonPath("$.expires_in").value("3599"));
    }

    }

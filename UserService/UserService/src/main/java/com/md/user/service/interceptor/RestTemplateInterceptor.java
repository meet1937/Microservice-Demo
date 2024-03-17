package com.md.user.service.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.security.oauth2.client.OAuth2AuthorizeRequest;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientManager;

import java.io.IOException;

public class RestTemplateInterceptor implements ClientHttpRequestInterceptor {

    private OAuth2AuthorizedClientManager manager;

    private Logger logger = LoggerFactory.getLogger(RestTemplateInterceptor.class);

    public RestTemplateInterceptor(OAuth2AuthorizedClientManager manager) {
        this.manager = manager;
    }

    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
        String token = manager.authorize(OAuth2AuthorizeRequest.withClientRegistrationId("my-internal-client").principal("internal").build()).getAccessToken().getTokenValue();

        logger.info("Rest Template interceptor: Token :  {} ", token);
        request.getHeaders().add("Authorization", "Bearer " + "eyJraWQiOiJNbmxmLVBRWFByR29wOXFfbkZ5bmlHa0VwR1Y2YWF4eVczZ21vWGZmSDVVIiwiYWxnIjoiUlMyNTYifQ.eyJ2ZXIiOjEsImp0aSI6IkFULnhPRHd3Z1B6ZWx3R1djbHJvd2RZUkJBbTkwSWlQN0owRGhibThNRFJVSk0ub2FyMXM4aXIzOWJzVTE1S1U1ZDciLCJpc3MiOiJodHRwczovL2Rldi0xMjg1ODEzMy5va3RhLmNvbS9vYXV0aDIvZGVmYXVsdCIsImF1ZCI6ImFwaTovL2RlZmF1bHQiLCJpYXQiOjE3MTA1ODEwMDAsImV4cCI6MTcxMDU4NDYwMCwiY2lkIjoiMG9hZnJ6dXJ5Y0JhNXpQMko1ZDciLCJ1aWQiOiIwMHVmcnp4d3gwYmdXMFRqUjVkNyIsInNjcCI6WyJvZmZsaW5lX2FjY2VzcyIsImVtYWlsIiwib3BlbmlkIiwicHJvZmlsZSJdLCJhdXRoX3RpbWUiOjE3MTA1NzQ0NzQsInN1YiI6Im1lZXQuZGhhbWlAZ21haWwuY29tIiwibXljbGFpbiI6WyJFdmVyeW9uZSIsIkFkbWluIl19.ljdyRDJ_MmngZrQujrU4hQUid3UwCkFwSJSil_zlw7GqicsbM3L7SyS42qbfhgvERpda8y-bGjZQ3MpUaHFjClMJyVTDRiqC1_ByRWvrhapXOznfXxxw0btfc4Tm3XAfeCFR7Dd1Cic-xApeSUw634UHjbheTilSU61-pL_8Q6bCYR6e5Nk2_5gWnJmDJ0hO8nvNhQY-aWxEFwbtVivtD4N1cCpkeqWmwz5GEIsPSaocenSfG8v6W-ZiVZ3STj2AfD6fvZks-KqUW9rCqkwd8qLg70IEvuX-6zJhsU05haX6YdAKCNZJ0vu_PrjKn6X5SjhpwDdpfCuxRFY569dI9g");
        logger.info(request.getHeaders().toString());
        logger.info(String.valueOf(execution.execute(request, body)));
        return execution.execute(request, body);
    }
}
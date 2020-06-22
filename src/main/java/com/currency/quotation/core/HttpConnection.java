package com.currency.quotation.core;

import com.currency.quotation.core.exceptions.ObjectNotFoundException;
import com.currency.quotation.core.exceptions.StandardException;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class HttpConnection {

    protected CloseableHttpResponse response;

    public String getUrlWithHeaders(String url, HashMap<String, String> headers) throws IOException {
        CloseableHttpClient client = HttpClients.custom().build();
        HttpUriRequest request = RequestBuilder.get()
                .setUri(url)
                .build();
        for (Map.Entry<String, String> entry : headers.entrySet()) {
            request.addHeader(entry.getKey(), entry.getValue());
        }
        this.response = client.execute(request);
        return this.parseResponseToString(this.response);
    }

    public String getUrl(String url) throws IOException {
        CloseableHttpClient client = HttpClients.createDefault();
        HttpUriRequest request = RequestBuilder.get()
                .setUri(url)
                .build();
        this.response = client.execute(request);
        return this.parseResponseToString(this.response);
    }

    public String postUrl(String url, List<NameValuePair> params) throws IOException {
        CloseableHttpClient client = HttpClients.custom().build();
        HttpUriRequest request = RequestBuilder.post()
                .setUri(url)
                .setEntity(new UrlEncodedFormEntity(params))
                .build();
        this.response = client.execute(request);
        return this.parseResponseToString(this.response);
    }

    public String postUrlWithHeaders(String url, List<NameValuePair> params, HashMap<String, String> headers) throws IOException {
        CloseableHttpClient client = HttpClients.custom().build();
        HttpUriRequest request = RequestBuilder.post()
                .setUri(url)
                .setEntity(new UrlEncodedFormEntity(params))
                .build();
        for (Map.Entry<String, String> entry : headers.entrySet()) {
            request.addHeader(entry.getKey(), entry.getValue());
        }
        this.response = client.execute(request);
        return this.parseResponseToString(this.response);
    }

    private String parseResponseToString(CloseableHttpResponse response) throws IOException {
        return EntityUtils.toString(response.getEntity(), StandardCharsets.UTF_8);
    }

    public CloseableHttpResponse getResponse() {
        return response;
    }

}

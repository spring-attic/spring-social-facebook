package org.springframework.social.facebook.security;

import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.security.crypto.codec.Hex;
import org.springframework.social.support.HttpRequestDecorator;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;

/*
 * Copyright 2002-2015 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

/**
 * @author Ariel Himmelstern
 */
public class FacebookAppSecretProofInterceptor implements ClientHttpRequestInterceptor {

    private final String appSecret;
    private final String appToken;
    private static final String algorithm = "HmacSHA256";

    public FacebookAppSecretProofInterceptor(String appToken, String appSecret) {
        this.appSecret = appSecret;
        this.appToken = appToken;
    }

    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
        HttpRequestDecorator protectedResourceRequest = new HttpRequestDecorator(request);

        String appSecreteProof = calculateAppSecretProof(appToken, appSecret);
        protectedResourceRequest.addParameter("appsecret_proof", appSecreteProof);
        return execution.execute(protectedResourceRequest, body);
    }

    private String calculateAppSecretProof(String token, String appSecret) {
        try {
            Mac mac = Mac.getInstance(algorithm);
            SecretKeySpec secretKey = new SecretKeySpec(appSecret.getBytes("UTF-8"), algorithm);
            mac.init(secretKey);
            byte[] digest = mac.doFinal(token.getBytes());
            return new String(Hex.encode(digest));
        } catch (Exception e) {
            return "";
        }

    }
}

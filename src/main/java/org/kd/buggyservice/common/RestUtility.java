package org.kd.buggyservice.common;

import org.kd.buggyservice.main.BuggyWebservice;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

@Component
@Import(BuggyserviceConfig.class)
public class RestUtility {

    private String errorResponseStatusCode;
    private String errorResponseBody;

    private String responseStatusCode;
    private String responseBody;
    private final Logger log = LoggerFactory.getLogger(BuggyWebservice.class);

    @Autowired
    private RestTemplate restTemplate;

    private void interceptErrorResponse(HttpStatusCodeException e) {

        var digitsRegex = "\\D+";
        errorResponseStatusCode = e.getStatusCode().toString().replaceAll(digitsRegex, "");
        errorResponseBody = e.getResponseBodyAsString();
    }

    public ResponseEntity<String> processHttpRequest(HttpMethod httpMethod, String request, String requestUrl, String contentType) {
        HttpEntity<String> entity = createRequestEntity(request, contentType);
        try {
            return restTemplate.exchange(requestUrl, httpMethod, entity, String.class);
        } catch (HttpStatusCodeException e) {
            interceptErrorResponse(e);
        }
        return null;
    }

    private HttpEntity<String> createRequestEntity(String request, String contentType) {
        var httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.valueOf(contentType));
        return new HttpEntity<>(request, httpHeaders);
    }

    public void retrieveResponseBodyAndStatusCode(ResponseEntity<String> response) {
        if (response != null) {
            int responseStatusCodeNumber = response.getStatusCodeValue();
            responseStatusCode = "" + responseStatusCodeNumber;
            responseBody = response.getBody();
            log.info("Status code: " + responseStatusCode);
        } else {
            responseBody = getErrorResponseBody();
            log.info("Status code: " + getErrorResponseStatusCode());
        }
    }

    public String getResponseBody() {
        return responseBody;
    }

    public String getResponseStatusCode() {
        return responseStatusCode;
    }

    public String getErrorResponseStatusCode() {
        return errorResponseStatusCode;
    }

    public String getErrorResponseBody() {
        return errorResponseBody;
    }
}


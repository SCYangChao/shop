package com.yqkj.http.service.imp;

import com.alibaba.fastjson.JSONObject;

import com.yqkj.http.callback.CallResponse;
import com.yqkj.http.dto.header.Header;
import com.yqkj.http.dto.request.BaseRequest;
import com.yqkj.http.dto.response.HttpResponse;
import com.yqkj.http.service.IHttpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;
import java.util.Set;

/**
 * class_name: HttpService
 * describe: do
 *
 * @author: yangchao.cool@gmail.com
 * creat_date: 下午3:37
 **/
@Service
@Slf4j
public class HttpService implements IHttpService {

    @Autowired
    @Qualifier("restTemplate")
    private RestTemplate restTemplate;

    @Override
    public HttpResponse post(String url, BaseRequest baseRequest, Header header, CallResponse callResponse) {
        HttpHeaders headers = initHeader(header);
        if (null == baseRequest) {
            log.info("input param:{};null", baseRequest);
            baseRequest = new BaseRequest();
        }

        HttpEntity<String> entity = new HttpEntity<String>(JSONObject.toJSONString(baseRequest), headers);

        ResponseEntity<String> json = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);

        String jsonData = json.getBody();

        return this.call_(jsonData, callResponse);
    }

    @Override
    public HttpResponse get(String url, Header header, CallResponse callResponse) {

        HttpHeaders headers = initHeader(header);

        HttpEntity<String> entity = new HttpEntity<String>(null, headers);

        ResponseEntity<String> json = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);

        String jsonData = json.getBody();

        if (null == callResponse) {
            HttpResponse<String> response = new HttpResponse<>();
            response.setData(jsonData);
            return response;
        }

        //调用
        return this.call_(jsonData, callResponse);
    }


    private HttpResponse call_(String jsonData, CallResponse callResponse) {
        if (null == callResponse) {
            HttpResponse<String> response = new HttpResponse<>();
            response.setData(jsonData);
            return response;
        }

        //调用
        HttpResponse response = null;
        try {
            response = callResponse.call(jsonData);
        } catch (Exception e) {
            response = new HttpResponse();
            response.setErrcode(HttpResponse.FAIL);
            response.setErrmsg(e.getMessage());
            log.error("callResponse {}", e.getMessage());
        }
        return response;
    }

    private HttpHeaders initHeader(Header header) {
        HttpHeaders headers = new HttpHeaders();
        if (null != header) {
            headers.setContentType(header.getMediaType());

            if (null != header.getHeader() && !header.getHeader().isEmpty()) {

                Set<Map.Entry<String, Object>> set = header.getHeader().entrySet();

                for (Map.Entry<String, Object> entry : set) {

                    headers.add(entry.getKey(), String.valueOf(entry.getValue()));

                }
            }
        }
        return headers;
    }
}

package com.shop.upms.api.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.shop.upms.api.config.IpConfig;
import com.shop.upms.api.config.WeatherConfig;
import com.yqkj.cache.RedisUtils;
import com.yqkj.http.callback.CallResponse;
import com.yqkj.http.dto.header.Header;
import com.yqkj.http.dto.response.HttpResponse;
import com.yqkj.http.service.IHttpService;
import com.yqkj.utile.IpUtile;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

@Service
@Slf4j
public class WeatheraskService implements IWeatheraskService {

    private String cron = null;

    @Autowired
    private IpConfig ipConfig;

    @Autowired
    private WeatherConfig weatherConfig;

    @Autowired
    private IHttpService httpService;

    Header HEADER = new Header();


    @Override
    public JSONObject excute(HttpServletRequest request) {

        String ipAddr = IpUtile.getIpAddr(request);

        String cityName= "成都";

        cityName = getIpinfo(ipAddr, cityName);

        //
        return getWeatherInfo(cityName);

    }

    private String getIpinfo(String ipAddr, String cityName) {
        if (!RedisUtils.exists(ipAddr)){

            HttpResponse<JSONObject> httpResponse = httpService.get(String.format(ipConfig.getUrl(), ipAddr), HEADER, new CallResponse<JSONObject>() {

                @Override
                public HttpResponse<JSONObject> call(String param) {
                    return new HttpResponse<>( JSONObject.parseObject(param));
                }
            });

            JSONObject data = httpResponse.getData();
            if (data.getInteger("resultcode") == 200 && data.getInteger("error_code")==0) {
                cityName=data.getJSONObject("result").getString("City");
            }
            RedisUtils.set(ipAddr ,cityName);
        }
        return cityName;
    }

    private JSONObject getWeatherInfo(String cityName) {
        if (!RedisUtils.exists(cityName)) {
            HttpResponse<JSONObject> httpResponse = httpService.get(String.format(weatherConfig.getUrl(), cityName), HEADER, new CallResponse<JSONObject>() {

                @Override
                public HttpResponse<JSONObject> call(String param) {
                    return new HttpResponse<>(JSONObject.parseObject(param));
                }
            });
            JSONObject data = httpResponse.getData();
            if (data.getInteger("error_code") == 0) {
                data = data.getJSONObject("result");
            }
            RedisUtils.set(cityName , data , weatherConfig.getTime());
            return data;
        }
        return  (JSONObject)RedisUtils.get(cityName);
    }


}

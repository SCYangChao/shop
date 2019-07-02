package com.shop.upms.api.service.impl;

import com.alibaba.fastjson.JSONObject;

import javax.servlet.http.HttpServletRequest;

public interface IWeatheraskService {

    JSONObject excute(HttpServletRequest request);

}

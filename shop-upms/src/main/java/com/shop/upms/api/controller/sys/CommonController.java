package com.shop.upms.api.controller.sys;

import com.alibaba.fastjson.JSONObject;
import com.shop.upms.api.config.SoftInfoConfig;
import com.shop.upms.api.config.UpFilePropertiesConfig;
import com.shop.upms.api.model.dto.response.common.SoftInfoResponse;
import com.shop.upms.api.model.dto.response.file.UploadFileResponse;
import com.shop.upms.api.service.impl.IWeatheraskService;
import com.yqkj.dto.Response;
import com.yqkj.log.WriteLog;
import com.yqkj.sys.MemoryTool;
import com.yqkj.utile.DateUtil;
import com.yqkj.utile.ResponseToole;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.UUID;

@RestController
@RequestMapping("/api/sys/v1.0/common")
@Api("商家活动")
public class CommonController {

    @Autowired
    private UpFilePropertiesConfig upFilePropertiesConfig;


    @Autowired
    private IWeatheraskService weatheraskService;

    @Autowired
    private SoftInfoConfig softInfoConfig;


    /**
     *
     * @param request
     * @return
     */
    @GetMapping(value = "/softInfo")
    @ApiOperation(value = "softInfo接口" )
    @WriteLog(isWrite = false)

    public Response<SoftInfoResponse> softInfo(HttpServletRequest request) {
        SoftInfoResponse softInfoResponse = new SoftInfoResponse();
        BeanUtils.copyProperties(softInfoConfig ,softInfoResponse);
        softInfoResponse.setMaxMemorySize(MemoryTool.getMaxMemorySize());
        softInfoResponse.setUsedMemorySize(MemoryTool.getUsedMemorySize());
        softInfoResponse.setTotalMemorySize(MemoryTool.getMaxMemorySize());
        return ResponseToole.success(softInfoResponse);
    }
   /**
     *
     * @param request
     * @return
     */
    @GetMapping(value = "/weather")
    @ApiOperation(value = "天气接口" )
    @WriteLog(isWrite = false)
    public Response<JSONObject> weather(HttpServletRequest request) {
        return ResponseToole.success(weatheraskService.excute(request));
    }

    /**
     *
     * @param file
     * @param request
     * @return
     */
    @PostMapping(value = "/upload",consumes = "multipartFile/*",headers = "content-type=multipart/form-data")
    @ApiOperation(value = "文件上传（1.1）" )
    @WriteLog(isWrite = false)
    public Response<UploadFileResponse> upload(@ApiParam(value = "上传的文件", required = true) MultipartFile file, HttpServletRequest request) {
        UploadFileResponse result= new UploadFileResponse();
        String originalFilename = file.getOriginalFilename();
        String basePath = mkdirFile() + File.separator + UUID.randomUUID().toString() + originalFilename.
                substring(originalFilename.lastIndexOf(".")).toLowerCase();
        String rmiFileName = upFilePropertiesConfig.getSavePah()+basePath;
        try {
            file.transferTo(new File(rmiFileName));
            result.setFileName(originalFilename);
            result.setUrl(upFilePropertiesConfig.getUrl()+basePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ResponseToole.success(result);
    }

    private String mkdirFile() {

        String path = DateUtil.getStringDate(new Date(),"yyyy/MM/dd");
        String filePath = upFilePropertiesConfig.getSavePah()+path;

        File file = new File(filePath);
        if (!file.exists()) {
            file.mkdirs();
        }
        return  path;
    }


}

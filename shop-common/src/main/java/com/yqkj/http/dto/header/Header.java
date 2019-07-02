package com.yqkj.http.dto.header;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.MediaType;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 *
  * class_name: Header
  * describe: do
  * @author: yangchao.cool@gmail.com
  * creat_date: 下午3:27
  *
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Header  implements Serializable{
    /**
     * 数据类型
     */
    private MediaType mediaType = MediaType.APPLICATION_JSON_UTF8;
    /**
     * 定制数据获取
     */
    private Map<String , Object> header = new HashMap<>(3);
}

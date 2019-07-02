package com.shop.upms.api.constant;

/**
 *
  * class_name: ShopEnum
  * describe: do
  * @author: yangchao.cool@gmail.com
  * creat_date: 下午9:52
  *
 **/
public interface ShopEnum {

    enum DateType implements ShopEnum {
        DAY,MONTH,WEKK,THREE,WISHES ,SUM;
    }
    enum Status implements ShopEnum {

        NORMAL(1 ,"正常"),DISABLE(0,"禁用");

        private Integer value;

        private String msg;

        Status(Integer value , String msg) {
            this.value=value;
            this.msg=msg;
        }
    }
    /**
     * 备份类型
     */
    enum DbBackType implements ShopEnum {
        AUTH("00","自动"),HAD("01" ,"手动");

        private String value;

        private String msg;
        DbBackType(String value , String msg) {
            this.value=value;
            this.msg=msg;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }
    }

}

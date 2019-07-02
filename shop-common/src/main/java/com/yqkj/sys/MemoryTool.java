package com.yqkj.sys;

import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.lang.management.MemoryUsage;

/**
 *
  * class_name: MemoryTool
  * describe: do
  * @author: yangchao.cool@gmail.com
  * creat_date: 下午10:44
  *
 **/
public class MemoryTool {


    private static  MemoryMXBean memoryMXBean = ManagementFactory.getMemoryMXBean();

    /**
     * 总初始的总内存
     * @return
     */
    public  static String getTotalMemorySize(){

        MemoryUsage memoryUsage = memoryMXBean.getHeapMemoryUsage();
        //椎内存使用情况
        long totalMemorySize = memoryUsage.getInit();
        //初始的总内存
        return totalMemorySize/(1024*1024)+"M";

    }
    /**
     * 最大可用内存
     * @return
     */
    public  static String getMaxMemorySize(){

        MemoryUsage memoryUsage = memoryMXBean.getHeapMemoryUsage();
        //椎内存使用情况
        long maxMemorySize = memoryUsage.getMax();
        //初始的总内存
        return maxMemorySize/(1024*1024)+"M";

    }

    /**
     * 最大可用内存
     * @return
     */
    public  static String getUsedMemorySize(){

        MemoryUsage memoryUsage = memoryMXBean.getHeapMemoryUsage();
        //椎内存使用情况
        long usedMemorySize = memoryUsage.getUsed();
        //初始的总内存
        return usedMemorySize/(1024*1024)+"M";

    }


}

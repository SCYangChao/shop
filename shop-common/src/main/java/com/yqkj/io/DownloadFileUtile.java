package com.yqkj.io;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;


/**
 *
  * class_name: DownloadFileDto
  * describe: do
  * @author: yangchao.cool@gmail.com
  * creat_date: 下午11:38
  *
 **/
public class DownloadFileUtile  {

    /**
     *
      * class_name: DownloadFileUtile
      * describe: do文件下载
      * @author: yangchao.cool@gmail.com
      * creat_date: 下午11:56
      *
     **/
    public static   void dounloadLocalFile(DownloadFileDto downloadFileDto) {

        FileInputStream fileInputStream = null;
        OutputStream outputStream = null;
        try {
            fileInputStream = new FileInputStream(downloadFileDto.getFilePath());
            outputStream = downloadFileDto.getResponse().getOutputStream();
            byte[] by = new byte[1024];
            String mt =downloadFileDto.getRequest().getServletContext().getMimeType(downloadFileDto.getFilePath());

            downloadFileDto.getResponse().setContentType(mt);
            int len = 0;

            while ((len = fileInputStream.read(by)) != -1) {

                outputStream.write(by ,0 ,len);

            }


        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if (null != outputStream ) {
                try {
                    outputStream.flush();
                    outputStream.close();

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (null != fileInputStream ) {
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

}

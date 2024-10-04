package com.hspedu.chapter21.FileUploadTest;

import java.io.*;

public class Utils {
    /*
     * 降输入流转为byte[]
     */
    public static byte[] streamToByteArray(InputStream is) throws IOException {
        ByteArrayOutputStream bos = new ByteArrayOutputStream(); // 创建输出流对象
        byte[] b = new byte[1024];
        int len;
        while ((len = is.read(b)) != -1) {
            bos.write(b, 0, len);
        }
        byte[] array = bos.toByteArray();
        bos.close();
        return array;
    }

    /*
     * 降InputStream转成String
     */
    public static String streamToString(InputStream is) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder builder = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            builder.append(line + "\r\n");
        }
        return builder.toString();
    }

}

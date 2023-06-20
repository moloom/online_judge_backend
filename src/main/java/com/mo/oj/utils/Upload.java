package com.mo.oj.utils;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * @author: mo
 * @date: 2022/4/12 下午10:50
 * @description:文件上传类
 */
public class Upload {

    private static String path = "/tem/picture";// 文件上传路径

    public static String upload(MultipartFile upload) {
        //  获取要上传文件的文件名
        String fileName = upload.getOriginalFilename();
        String newFileName = fileName.substring(0, fileName.lastIndexOf("."));
        if (newFileName.length() > 20) {
            newFileName = newFileName.substring(0, 20);
        }
        newFileName = newFileName + System.currentTimeMillis() + fileName.substring(fileName.lastIndexOf("."));
        //  判断路径是否存在，不存在则新建
        File file = new File(path);
        if (!file.exists()) {
            file.mkdir();
        }
        // 改名,避免重名,定义新文件的名字
        String newName = path + File.separator + newFileName;
        try {
            upload.transferTo(new File(newName));
            return newFileName;
        } catch (IOException e) {
            return null;
        }
    }
}

package com.school.mongolian;

import com.github.tobato.fastdfs.domain.fdfs.StorePath;
import com.github.tobato.fastdfs.domain.fdfs.ThumbImageConfig;
import com.github.tobato.fastdfs.domain.upload.ThumbImage;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import com.school.mongolian.config.FastDfsClientConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

@SpringBootTest
@RunWith(SpringRunner.class)
public class FastDFSTest {
    //fastDFS 存储客户端
    @Autowired
    private FastFileStorageClient storageClient;
    // 用于获取 fastDFS 图片缩略图
    @Autowired
    private ThumbImageConfig thumbImageConfig;

    @Test
    public void testUpload() throws FileNotFoundException{
        // 获取本机要上传的文件
        File file = new File("C:\\Users\\13606\\Pictures\\Saved Pictures\\wallhaven-70618.jpg");
        /**
         * 上传并保存图片
         *
         * 参数：
         * 1-上传的文件流
         * 2-文件的大小
         * 3-文件的后缀
         * 4-可以不管他
         */
        StorePath storePath = this.storageClient.uploadFile(
                new FileInputStream(file), file.length(), "jpg", null
        );
        // 带分组的路径
        System.out.println(storePath.getFullPath());
        //不带分组的路径
        System.out.println(storePath.getPath());
    }
    @Test
    public void testUploadAndCreateThumb() throws FileNotFoundException{
        File file = new File("C:\\Users\\13606\\Pictures\\Saved Pictures\\508518a4ae8e1.jpg");
        /**
         * 上传并保存图片并且生成缩略图
         *
         * 参数：
         * 1-上传的文件流
         * 2-文件的大小
         * 3-文件的后缀
         * 4-可以不管他
         */
        StorePath storePath = this.storageClient.uploadImageAndCrtThumbImage(
                new FileInputStream(file), file.length(), "png", null
        );
        // 带分组的路径
        System.out.println(storePath.getFullPath());

        // 不带分组的路径
        System.out.println(storePath.getPath());

        String path = thumbImageConfig.getThumbImagePath(storePath.getPath());
        System.out.println(path);

    }

}

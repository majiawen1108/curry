package com.example.curry.utils.file;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.FrameworkServlet;

import java.io.File;

/**
 * @author jw.ma
 * @title: FileUtil
 * @description: TODO
 * @date 2022/1/30 10:38
 */
public class FileUtil {
    public static String getWebPath(String path){
        WebApplicationContext webApplicationContext = null;
        FrameworkServlet frameworkServlet = new DispatcherServlet(webApplicationContext);
        WebApplicationContext wac = frameworkServlet.getWebApplicationContext();
        return wac.getServletContext().getRealPath(path.replace("\\","/"));
    }

    public static void mkdir(String templateDir) {
        File dirPath = new File(templateDir);
        if (!dirPath.exists()){
            dirPath.mkdirs();
        }
    }
}

package com.sebi.resolver;

import com.sebi.componentbeans.FileComponentBean;
import com.sebi.utility.FileReaderUtil;
import org.apache.log4j.Logger;

import java.io.File;

public class FileResolver {

    public FileComponentBean resolve(String path) {

        FileComponentBean componentBean = new FileComponentBean();
        File file;

        try {

            file = new File(path);

            if (file.exists()) {

                //get file name
                componentBean.setFileName(file.getName());

                //get file mimetype
                componentBean.setMimeType(FileReaderUtil.getMimeType(path));

                //get file size
                componentBean.setFileSize(FileReaderUtil.getFileSize(path));

                //get file extension
                componentBean.setFileExtension(FileReaderUtil.getFileExtension(path));

            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return componentBean;
    }

}

package com.sebi.utility;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.log4j.Logger;
import org.apache.tika.Tika;

import java.io.File;
import java.io.IOException;

public class FileReaderUtil {

    public static String getMimeType(String path) {

        File file;
        Tika tika = new Tika();

        String mimeType = "";

        try {
             file = new File(path);
             if (file.exists()) {
                 mimeType += tika.detect(file);
             }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return mimeType;

    }

    public static String getFileSize(String path) {
        File file = new File(path);
        long size = file.length();
        return FileUtils.byteCountToDisplaySize(size);
    }

    public static String getFileExtension(String path) {
        return FilenameUtils.getExtension(path);
    }

}

package com.sebi.junit;

import com.sebi.componentbeans.FileComponentBean;
import com.sebi.resolver.FileResolver;
import com.sebi.utility.FileReaderUtil;
import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class FileReaderUtilTests {

    private final static Logger LOG = Logger.getLogger(FileReaderUtil.class);

    private FileComponentBean componentBean;
    private FileResolver fileResolver;

    private final static String FILE_PATH = "register1.csv";
    private final static String FILE_NAME = "register1.csv";
    private final static String MIME_TYPE = "text/csv";
    private final static String FILE_SIZE = "26 bytes";
    private final static String FILE_EXTENSION = "csv";

    @Before
    public void setup() {
        componentBean = new FileComponentBean();
        fileResolver = new FileResolver();
    }

    @Test
    public void shouldReturnFileName() {
        componentBean = fileResolver.resolve(FILE_PATH);
        assertThat(componentBean.getFileName(), is(equalTo(FILE_NAME)));
    }

    @Test
    public void shouldReturnFileMimeType() {
        componentBean = fileResolver.resolve(FILE_PATH);
        assertThat(componentBean.getMimeType(), is(equalTo(MIME_TYPE)));
    }

    @Test
    public void shouldReturnFileSize() {
        componentBean = fileResolver.resolve(FILE_PATH);
        assertThat(componentBean.getFileSize(), is(FILE_SIZE));
    }

    @Test
    public void shouldReturnFileExtension() {
        componentBean = fileResolver.resolve(FILE_PATH);
        assertThat(componentBean.getFileExtension(), is(FILE_EXTENSION));
    }

    @Test
    public void shouldReturnNameMimeTypeSizeAndFileExtension_forMultipleFiles() {
        File folder = new File("files-to-read");
        List<String> files = new ArrayList<>();

        for (final File fileEntry : folder.listFiles()) {
            files.add(fileEntry.getPath());
        }
        int count = 1;
        for (String file : files) {
            componentBean = fileResolver.resolve(file);
            LOG.info("File " + count + " name is: " + componentBean.getFileName());
            LOG.info("File " + count + " mime type is: " + componentBean.getMimeType());
            LOG.info("File " + count + " size is: " + componentBean.getFileSize());
            LOG.info("File " + count + " extension is: " + componentBean.getFileExtension());
            count++;
        }
        assertThat(files.size(), is(equalTo(11)));
    }

}

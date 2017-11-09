package com.sebi.junit;

import com.sebi.componentbeans.FileComponentBean;
import com.sebi.resolver.FileResolver;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class FileReaderUtilTests {

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
    public void shouldReturnFileExtention() {
        componentBean = fileResolver.resolve(FILE_PATH);
        assertThat(componentBean.getFileExtension(), is(FILE_EXTENSION));
    }

}

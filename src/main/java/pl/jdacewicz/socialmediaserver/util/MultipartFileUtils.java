package pl.jdacewicz.socialmediaserver.util;

import org.apache.commons.io.FileUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

public class MultipartFileUtils {

    public static void saveFile(MultipartFile file, String path, String fileName) throws IOException {
        var inputStream = file.getInputStream();
        var directory = new File(path + "/" + fileName);
        FileUtils.copyInputStreamToFile(inputStream, directory);
    }

    public static void deleteFile() {
        throw new UnsupportedOperationException();
    }

    public static void deleteDirectory() {
        throw new UnsupportedOperationException();
    }
}

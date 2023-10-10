package pl.jdacewicz.socialmediaserver.utils;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

public class FileUtils {

    public static void uploadFile(String fileUrl, MultipartFile image) throws IOException {
        var directory = new File(fileUrl);
        org.apache.commons.io.FileUtils.copyInputStreamToFile(image.getInputStream(), directory);
    }

    public static void deleteDirectory(String directoryUrl) throws IOException {
        var directory = new File(directoryUrl);
        org.apache.commons.io.FileUtils.deleteDirectory(directory);
    }
}

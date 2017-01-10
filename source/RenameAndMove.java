package io.github.accessun.main;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import org.apache.commons.io.FilenameUtils;

public class RenameAndMove {
    
    static List<String> fileTypesToProcess;
    static String githubPrefix;
    
    static {
        fileTypesToProcess = Arrays.asList("png", "jpg", "jpeg");
        githubPrefix = "https://raw.githubusercontent.com/accessun/ResourceStorage/master/pictures/";
    }

    public void run() throws IOException {
        Path dir = Paths.get(System.getProperty("user.dir"));

        Files.list(dir).forEach(p -> {

            String parentDir = p.getParent().toString();
            String newName = UUID.randomUUID().toString();
            String ext = FilenameUtils.getExtension(p.getFileName().toString()).toLowerCase();

            if (Files.isRegularFile(p) && fileTypesToProcess.contains(ext)) {

                String picDirStr = parentDir + File.separator + "pictures";
                Path picDir = Paths.get(picDirStr);
                Path newPath = Paths.get(picDirStr + File.separator + newName + "." + ext);

                if (Files.exists(picDir) && Files.isDirectory(picDir)) {
                    try {
                        Files.move(p, newPath);
                        System.out.println("==> " + githubPrefix + newName + "." + ext);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

            }

        });
    }

    public static void main(String[] args) throws IOException {
        new RenameAndMove().run();
    }

}

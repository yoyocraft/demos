package org.example.demo;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

/**
 * @author yoyocraft
 * @date 2024/09/25
 */
public class CreateDirDemo {

    public static final String[] DIRS = {
            "/Users/youyi/Documents/Blog/source/_posts",
            "/Users/youyi/Downloads"
    };

    public static void main(String[] args) throws IOException {
        for (String dir : DIRS) {
            try (Stream<Path> pathStream = Files.walk(Paths.get(dir))) {
                // 过滤出所有 md 文件
                pathStream.filter(path -> path.toString().endsWith(".md"))
                        .forEach(path -> {
                            // 获取到文件名，去除文件后缀
                            String fileName = path.getFileName().toString().replace(".md", "");
                            // 校验是否有同名的文件夹
                            if (Files.exists(Paths.get(path.getParent() + "/" + fileName))) {
                                return;
                            }
                            // 创建和文件名同名的文件夹
                            try {
                                Files.createDirectory(Paths.get(path.getParent() + "/" + fileName));
                            } catch (IOException ignore) {
                            }
                        });
            }
        }
    }
}

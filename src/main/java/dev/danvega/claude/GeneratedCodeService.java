package dev.danvega.claude;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class GeneratedCodeService {

    @Value("${generated.files.path:src/main/resources/generated}")
    private String generatedFilesPath;

    public void writeToFile(String sourceCode) {
        String className = extractClassName(sourceCode);
        if (className == null) {
            throw new IllegalArgumentException("Source Code Does Not Contain a Class Name");
        }

        Path filePath = Paths.get(generatedFilesPath, className + ".java");

        try {
            Files.createDirectories(filePath.getParent());
            Files.writeString(filePath, sourceCode);
            System.out.printf("Class '%s' has been written to %s%n", className, filePath);
        } catch (IOException e) {
            System.err.printf("Error writing to file: %s%n", e.getMessage());
        }
    }

    private String extractClassName(String sourceCode) {
        Pattern pattern = Pattern.compile("class\\s+(\\w+)");
        Matcher matcher = pattern.matcher(sourceCode);
        if (matcher.find()) {
            return matcher.group(1);
        }
        return null;
    }

}

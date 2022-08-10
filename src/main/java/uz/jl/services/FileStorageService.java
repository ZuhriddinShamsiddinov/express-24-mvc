package uz.jl.services;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import uz.jl.domains.Uploads;
import uz.jl.repository.UploadsRepository;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;
import javax.ws.rs.NotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

/**
 * @author - 'Zuhriddin Shamsiddionov' at 12:57 PM 8/4/22 on Thursday in August
 */
@RequiredArgsConstructor
@Service
public class FileStorageService {
    private final Path rootPath = Path.of("/home/zuhriddin/IdeaProjects/spring/spring-mvc/spring-security-mvc/src/main/resources/static/uploads");
    private final UploadsRepository repository;


    @Transactional
    public Uploads upload(MultipartFile multipartFile) {
        try {
            String contentType = multipartFile.getContentType();
            String originalFilename = multipartFile.getOriginalFilename();
            long size = multipartFile.getSize();
            String filename = StringUtils.getFilename(originalFilename);
            String filenameExtension = StringUtils.getFilenameExtension(originalFilename);
            String generatedName = System.currentTimeMillis() + "." + filenameExtension;
            String path = "static/uploads/" + generatedName;
            Uploads uploads = Uploads
                    .builder()
                    .contentType(contentType)
                    .originalName(filename)
                    .size(size)
                    .generatedName(generatedName)
                    .path(path)
                    .build();
            Path uploadPath = rootPath.resolve(generatedName);
            repository.save(uploads);
            Files.copy(multipartFile.getInputStream(), uploadPath, StandardCopyOption.REPLACE_EXISTING);
            return uploads;
        } catch (IOException e) {
            throw new RuntimeException("Something wrong try again");
        }
    }


    public ResponseEntity<Resource> download(@NonNull String filename) {
        Path path = rootPath.resolve(filename);
        Resource resource = new FileSystemResource(path);
        Uploads uploads = repository.findByGeneratedName(filename).orElseThrow(() -> {
            throw new NotFoundException("File not found");
        });


        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(uploads.getContentType()))
                .contentLength(uploads.getSize())
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=\"" + uploads.getOriginalName() + "\""
                )
                .body(resource);
    }

}

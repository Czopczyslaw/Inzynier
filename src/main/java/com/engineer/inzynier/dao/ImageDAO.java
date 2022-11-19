package com.engineer.inzynier.dao;

import com.engineer.inzynier.entities.Image;
import com.engineer.inzynier.repositories.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ImageDAO {

    @Autowired
    private ImageRepository imageRepository;


    public void save(Image image){
        imageRepository.save(image);
    }

    public void saveImage(MultipartFile imageFile, Image image) throws IOException{
        Path currentPath = Paths.get(".");
        Path absolutePath = currentPath.toAbsolutePath();
        image.setPath(absolutePath + "/src/main/resources/images/");
        byte[] bytes = imageFile.getBytes();
        Path path = Paths.get(image.getPath() + imageFile.getOriginalFilename());
        Files.write(path, bytes);
    }
}

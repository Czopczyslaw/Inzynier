package com.engineer.inzynier.services;

import com.engineer.inzynier.entities.Image;
import com.engineer.inzynier.repositories.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Optional;

@Service
public class ImageService {

    @Autowired
    ImageRepository imageRepository;

    public void saveImage(Image image) throws IOException {
        try {
            if (image != null) {
                imageRepository.save(image);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public Optional<Image> getImageById(Long id){
        return imageRepository.findById(id);
    }
}

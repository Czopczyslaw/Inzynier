package com.engineer.inzynier.controllers;

import com.engineer.inzynier.entities.Image;
import com.engineer.inzynier.services.ImageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("image")
public class ImageController {
    public static String uploadDirectory = System.getProperty("user.dir") + "/images";
    @Autowired
    ImageService imageService;
    private final Logger logger = LoggerFactory.getLogger(UserController.class);

    @GetMapping("/index/display/{id}")
    @ResponseBody
    void showImage(@PathVariable("id") Long id, HttpServletResponse response, Optional<Image> image) throws IOException {
        logger.info("id = " + id);
        image = imageService.getImageById(id);
        response.setContentType(MediaType.IMAGE_JPEG_VALUE);
        response.getOutputStream().write(image.get().getImage());
        response.getOutputStream().close();
    }

    @PostMapping("/saveImage")
    public @ResponseBody ResponseEntity<?> saveImage(@RequestParam("name") String name, Map<String, Object> model, HttpServletRequest request, final @RequestParam("image") MultipartFile file) {
        try {

            logger.info("uploadDirectory:: " + uploadDirectory);
            String fileName = file.getOriginalFilename();
            String filePath = Paths.get(uploadDirectory, fileName).toString();
            logger.info("FileName: " + file.getOriginalFilename());
            if (fileName == null || fileName.contains("..")) {
                model.put("invalid", "Sorry! Filename contains invalid path sequence \" + fileName");
                return new ResponseEntity<>("Sorry! Filename contains invalid path sequence " + fileName, HttpStatus.BAD_REQUEST);
            }
            String[] names = name.split(",");
            Date createDate = new Date();
            try {
                File dir = new File(uploadDirectory);
                if (!dir.exists()) {
                    logger.info("Folder Created");
                    dir.mkdirs();
                }
                // Zapisanie obrazu lokalnie
                BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(filePath)));
                stream.write(file.getBytes());
                stream.close();
            } catch (Exception e) {
                logger.info("in catch");
                e.printStackTrace();
            }
            byte[] imageData = file.getBytes();
            Image image = new Image();
            image.setFileName(names[0]);
            image.setImage(imageData);
            image.setCreatedDate((Timestamp) createDate);
            imageService.saveImage(image);
            logger.info("HttpStatus===" + new ResponseEntity<>(HttpStatus.OK));
            return new ResponseEntity<>("Image Saved With File - " + fileName, HttpStatus.OK);

        } catch (Exception e) {
            e.printStackTrace();
            logger.info("Exception: " + e);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}



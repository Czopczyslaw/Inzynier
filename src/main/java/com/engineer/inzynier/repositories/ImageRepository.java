package com.engineer.inzynier.repositories;

import com.engineer.inzynier.entities.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<Image,Long> {

}

package com.engineer.inzynier.repositories;

import com.engineer.inzynier.entities.Device;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DeviceRepository extends JpaRepository<Device, Long> {

    Device findByUid(String uid);

    List<Device> findAllByUserUid(String userUid);
}

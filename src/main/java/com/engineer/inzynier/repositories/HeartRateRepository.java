package com.engineer.inzynier.repositories;

import com.engineer.inzynier.entities.HeartRateData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface HeartRateRepository extends JpaRepository<HeartRateData, Long> {
    List<HeartRateData> findAllByEntryTimeBetweenAndUserUID(Date entryTime, Date entryTime2, String userUID);

    HeartRateData findFirstByUserUIDOrderByEntryTimeDesc(String userUID);

    HeartRateData findFirstByEntryTimeBeforeAndAndUserUID(Date entryTimeBefore, String userUID);


}

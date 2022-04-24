package com.engineer.inzynier.repositories;

import com.engineer.inzynier.entities.StepsData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface StepsDataRepository extends JpaRepository<StepsData, Long> {
    List<StepsData> findAllByEntryTimeAfterAndEntryTimeBeforeAndUserUID(Date entryTime, Date entryTime2, String userUID);

    StepsData findFirstByUserUID(String userUID);

    StepsData findFirstByEntryTimeBeforeAndAndUserUID(Date entryTimeBefore, String userUID);


}

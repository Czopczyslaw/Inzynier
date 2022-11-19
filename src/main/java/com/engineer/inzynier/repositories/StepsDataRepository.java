package com.engineer.inzynier.repositories;

import com.engineer.inzynier.entities.StepsData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface StepsDataRepository extends JpaRepository<StepsData, Long> {
    List<StepsData> findAllByEntryTimeAfterAndEntryTimeBeforeAndUserUID(Date entryTime, Date entryTime2, String userUID);

    List<StepsData> findAllByEntryTimeBetweenAndUserUID(Date entryTime, Date entryTime2, String userUID);

    StepsData findFirstByUserUIDOrderByEntryTimeDesc(String userUID);

    StepsData findFirstByEntryTimeBeforeAndUserUID(Date entryTimeBefore, String userUID);
    @Query(value = "select * from steps_data s WHERE s.useruid = :userUID  AND s.entry_time >= :startOfDay AND s.entry_time <= :endOfDay ORDER BY s.entry_time desc limit 1", nativeQuery = true)
    StepsData findFirstForDayAndUserUID(@Param("userUID")String userUID,@Param("startOfDay") Date startOfDay, @Param("endOfDay") Date endOfDay);


}

package com.example.demo.record.repository;

import com.example.demo.record.entity.MusicRecord;
import org.springframework.data.jpa.repository.JpaRepository;

@org.springframework.stereotype.Repository
public interface RecordRepository extends JpaRepository<MusicRecord, Long> {

}

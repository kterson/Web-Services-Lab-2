package com.example.demo.record.service;

import com.example.demo.record.entity.MusicRecord;
import com.example.demo.record.repository.RecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RecordService {

    private RecordRepository repository;

    @Autowired
    public RecordService(RecordRepository repository) {
        this.repository = repository;
    }

    public MusicRecord create(MusicRecord musicRecord) {
        return repository.save(musicRecord);
    }

    public Optional<MusicRecord> find(Long id) {
        return repository.findById(id);
    }

    public List<MusicRecord> findAll() {
        return repository.findAll();
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    public void update(MusicRecord musicRecord) {
        repository.save(musicRecord);
    }

}

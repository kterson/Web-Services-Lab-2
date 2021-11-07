package com.example.demo.record.service;

import com.example.demo.record.entity.Genre;
import com.example.demo.record.entity.MusicRecord;
import com.example.demo.record.repository.GenreRepository;
import com.example.demo.record.repository.RecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GenreService {

    private GenreRepository repository;

    @Autowired
    public GenreService(GenreRepository repository) {
        this.repository = repository;
    }

    public Genre create(Genre genre) {
        return repository.save(genre);
    }

    public Optional<Genre> find(String name) {
        return repository.findById(name);
    }

    public List<Genre> findAll() {
        return repository.findAll();
    }

    public void delete(String name) {
        repository.deleteById(name);
    }

    public void update(Genre genre) {
        repository.save(genre);
    }

}

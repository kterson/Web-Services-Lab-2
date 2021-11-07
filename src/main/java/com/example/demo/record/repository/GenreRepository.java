package com.example.demo.record.repository;

import com.example.demo.record.entity.Genre;
import org.springframework.data.jpa.repository.JpaRepository;

@org.springframework.stereotype.Repository
public interface GenreRepository extends JpaRepository<Genre, String> {

}

package com.example.demo.record.controller;

import com.example.demo.record.dto.genre.CreateGenreRequest;
import com.example.demo.record.dto.genre.GetGenreResponse;
import com.example.demo.record.dto.genre.GetGenresResponse;
import com.example.demo.record.dto.genre.UpdateGenreRequest;
import com.example.demo.record.dto.record.CreateMusicRecordRequest;
import com.example.demo.record.dto.record.GetMusicRecordResponse;
import com.example.demo.record.dto.record.GetMusicRecordsResponse;
import com.example.demo.record.dto.record.UpdateMusicRecordRequest;
import com.example.demo.record.entity.Genre;
import com.example.demo.record.entity.MusicRecord;
import com.example.demo.record.service.GenreService;
import com.example.demo.record.service.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@RestController
@RequestMapping("genres")
public class GenreController {

    private GenreService genreService;
    private RecordService recordService;

    @Autowired
    public GenreController(GenreService genreService, RecordService recordService) {
        this.genreService = genreService;
        this.recordService = recordService;
    }

    @GetMapping
    public ResponseEntity<GetGenresResponse> getGenres() {
        List<Genre> genres = genreService.findAll();
        Function<Collection<Genre>, GetGenresResponse> mapper = GetGenresResponse.entityToDtoMapper();
        GetGenresResponse response = mapper.apply(genres);
        return ResponseEntity.ok(response);
    }

    @GetMapping("{name}")
    public ResponseEntity<GetGenreResponse> getGenre(@PathVariable("name") String name) {
        return genreService.find(name)
                .map(v -> ResponseEntity.ok(GetGenreResponse.entityToDtoMapper().apply(v)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Void> createGenre(@RequestBody CreateGenreRequest request, UriComponentsBuilder builder) {
        Genre genre = CreateGenreRequest
                .dtoToEntityMapper()
                .apply(request);
        genre = genreService.create(genre);
        return ResponseEntity.created(builder.pathSegment("genres", "{name}")
                .buildAndExpand(genre.getName()).toUri()).build();
    }

    @DeleteMapping("{name}")
    public ResponseEntity<Void> deleteGenre(@PathVariable("name") String name) {
        Optional<Genre> genre = genreService.find(name);
        if (genre.isPresent()) {
            Genre genreUnwrapped = genre.get();
            for(MusicRecord musicRecord : genreUnwrapped.getMusicRecords()){
                recordService.delete(musicRecord.getId());
            }
            genreService.delete(genre.get().getName());
            return ResponseEntity.accepted().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("{name}")
    public ResponseEntity<Void> updateGenre(@RequestBody UpdateGenreRequest request, @PathVariable("name") String name) {
        Optional<Genre> genre = genreService.find(name);
        if (genre.isPresent()) {
            UpdateGenreRequest.dtoToEntityUpdater().apply(genre.get(), request);
            genreService.update(genre.get());
            return ResponseEntity.accepted().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}

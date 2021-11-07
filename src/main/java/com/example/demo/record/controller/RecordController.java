package com.example.demo.record.controller;

import com.example.demo.record.dto.record.CreateMusicRecordRequest;
import com.example.demo.record.dto.record.GetMusicRecordResponse;
import com.example.demo.record.dto.record.GetMusicRecordsResponse;
import com.example.demo.record.dto.record.UpdateMusicRecordRequest;
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
@RequestMapping("records")
public class RecordController {

    private RecordService recordService;
    private GenreService genreService;

    @Autowired
    public RecordController(RecordService recordService, GenreService genreService) {
        this.recordService = recordService;
        this.genreService = genreService;
    }

    @GetMapping
    public ResponseEntity<GetMusicRecordsResponse> getMusicRecords() {
        List<MusicRecord> musicRecords = recordService.findAll();
        Function<Collection<MusicRecord>, GetMusicRecordsResponse> mapper = GetMusicRecordsResponse.entityToDtoMapper();
        GetMusicRecordsResponse response = mapper.apply(musicRecords);
        return ResponseEntity.ok(response);
    }

    @GetMapping("{id}")
    public ResponseEntity<GetMusicRecordResponse> getMusicRecord(@PathVariable("id") long id) {
        return recordService.find(id)
                .map(v -> ResponseEntity.ok(GetMusicRecordResponse.entityToDtoMapper().apply(v)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Void> createRecord(@RequestBody CreateMusicRecordRequest request, UriComponentsBuilder builder) {
        MusicRecord musicRecord = CreateMusicRecordRequest
                .dtoToEntityMapper(name -> genreService.find(name).orElseThrow())
                .apply(request);
        musicRecord = recordService.create(musicRecord);
        return ResponseEntity.created(builder.pathSegment("records", "{id}")
                .buildAndExpand(musicRecord.getId()).toUri()).build();
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteRecord(@PathVariable("id") long id) {
        Optional<MusicRecord> musicRecord = recordService.find(id);
        if (musicRecord.isPresent()) {
            recordService.delete(musicRecord.get().getId());
            return ResponseEntity.accepted().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("{id}")
    public ResponseEntity<Void> updateRecord(@RequestBody UpdateMusicRecordRequest request, @PathVariable("id") long id) {
        Optional<MusicRecord> musicRecord = recordService.find(id);
        if (musicRecord.isPresent()) {
            UpdateMusicRecordRequest.dtoToEntityUpdater().apply(musicRecord.get(), request);
            recordService.update(musicRecord.get());
            return ResponseEntity.accepted().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }





}

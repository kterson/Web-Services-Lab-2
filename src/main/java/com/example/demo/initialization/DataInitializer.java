package com.example.demo.initialization;

import com.example.demo.record.entity.Genre;
import com.example.demo.record.entity.MusicRecord;
import com.example.demo.record.service.GenreService;
import com.example.demo.record.service.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;

@Component
public class DataInitializer {
    private final RecordService recordService;
    private final GenreService genreService;

    @Autowired
    public DataInitializer(RecordService recordService, GenreService genreService) {
        this.recordService = recordService;
        this.genreService = genreService;
    }

    @PostConstruct
    private synchronized void init() {

        Genre pop = Genre.builder()
                .name("Pop")
                .popularity(10)
                .musicRecords(new ArrayList<>())
                .build();

        Genre electronic = Genre.builder()
                .name("Electronic")
                .popularity(5)
                .musicRecords(new ArrayList<>())
                .build();

        Genre hipHop = Genre.builder()
                .name("Hip Hop")
                .popularity(8)
                .musicRecords(new ArrayList<>())
                .build();

        genreService.create(pop);
        genreService.create(electronic);
        genreService.create(hipHop);

        MusicRecord houndsOfLove = MusicRecord.builder()
                .name("Hounds Of Love")
                .artist("Kate Bush")
                .genre(pop)
                .yearOfRelease(1985)
                .label("Noble And Brite")
                .build();

        MusicRecord illmatic = MusicRecord.builder()
                .name("Illmatic")
                .artist("Nas")
                .genre(hipHop)
                .yearOfRelease(1994)
                .label("Columbia Records")
                .build();

        MusicRecord untrue = MusicRecord.builder()
                .name("Untrue")
                .artist("Burial")
                .genre(electronic)
                .yearOfRelease(2007)
                .label("Hyperdub")
                .build();

        MusicRecord titanicRising = MusicRecord.builder()
                .name("Titanic Rising")
                .artist("Weyes Blood")
                .genre(pop)
                .yearOfRelease(2019)
                .label("Sub Pop Records")
                .build();

        MusicRecord madvillainy = MusicRecord.builder()
                .name("Madvillainy")
                .artist("Madvillain")
                .genre(hipHop)
                .yearOfRelease(2004)
                .label("Stones Throw Records")
                .build();

        recordService.create(houndsOfLove);
        recordService.create(illmatic);
        recordService.create(untrue);
        recordService.create(titanicRising);
        recordService.create(madvillainy);

    }
}

package com.example.demo.record.dto.record;

import com.example.demo.record.entity.Genre;
import com.example.demo.record.entity.MusicRecord;
import lombok.*;

import java.util.function.Function;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class CreateMusicRecordRequest {

    private String name;

    private String artist;
    private int yearOfRelease;
    private String label;
    private String genre;

    public static Function<CreateMusicRecordRequest, MusicRecord> dtoToEntityMapper(
            Function<String, Genre> genreFunction) {

        return request -> MusicRecord.builder()
                .name(request.getName())
                .artist(request.getArtist())
                .yearOfRelease(request.getYearOfRelease())
                .label(request.getLabel())
                .genre(genreFunction.apply(request.getGenre()))
                .build();

    }

}

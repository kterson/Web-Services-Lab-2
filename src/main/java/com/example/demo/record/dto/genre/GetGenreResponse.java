package com.example.demo.record.dto.genre;

import com.example.demo.record.dto.record.GetMusicRecordResponse;
import com.example.demo.record.entity.Genre;
import com.example.demo.record.entity.MusicRecord;
import lombok.*;

import java.util.List;
import java.util.function.Function;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class GetGenreResponse {

    private String name;
    private int popularity;
    private List<GetMusicRecordResponse> musicRecords;

    public static Function<Genre, GetGenreResponse> entityToDtoMapper() {
        return genre -> GetGenreResponse.builder()
                .name(genre.getName())
                .popularity(genre.getPopularity())
                .musicRecords(genre.getMusicRecordsDto())
                .build();
    }

}

package com.example.demo.record.dto.record;

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

public class GetMusicRecordResponse {

    private Long id;
    private String name;
    private String artist;
    private String genre;
    private int yearOfRelease;
    private String label;

    public static Function<MusicRecord, GetMusicRecordResponse> entityToDtoMapper() {
        return musicRecord -> GetMusicRecordResponse.builder()
                .id(musicRecord.getId())
                .name(musicRecord.getName())
                .artist(musicRecord.getArtist())
                .genre(musicRecord.getGenre().getName())
                .yearOfRelease(musicRecord.getYearOfRelease())
                .label(musicRecord.getLabel())
                .build();
    }

}

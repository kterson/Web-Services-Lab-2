package com.example.demo.record.dto.record;

import com.example.demo.record.entity.MusicRecord;
import lombok.*;

import java.util.function.BiFunction;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class UpdateMusicRecordRequest {

    private String name;
    private String artist;
    private int yearOfRelease;
    private String label;

    public static BiFunction<MusicRecord, UpdateMusicRecordRequest, MusicRecord> dtoToEntityUpdater() {
        return (musicRecord, request) -> {
            musicRecord.setName(request.getName());
            musicRecord.setArtist(request.getArtist());
            musicRecord.setYearOfRelease(request.getYearOfRelease());
            musicRecord.setLabel(request.getLabel());
            return musicRecord;
        };
    }
}

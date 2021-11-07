package com.example.demo.record.dto.record;

import lombok.*;

import java.util.Collection;
import java.util.List;
import java.util.function.Function;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class GetMusicRecordsResponse {

    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    @ToString
    @EqualsAndHashCode
    public static class MusicRecord {
        private Long id;
        private String name;
        private String artist;
    }

    @Singular
    private List<MusicRecord> musicRecords;

    public static Function<Collection<com.example.demo.record.entity.MusicRecord>,
            GetMusicRecordsResponse> entityToDtoMapper() {
        return musicRecords -> {
            GetMusicRecordsResponseBuilder response = GetMusicRecordsResponse.builder();
            musicRecords.stream()
                    .map(musicRecord -> MusicRecord.builder()
                            .id(musicRecord.getId())
                            .name(musicRecord.getName())
                            .artist(musicRecord.getArtist())
                            .build())
                    .forEach(response::musicRecord);
            return response.build();
        };
    }

}

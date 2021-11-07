package com.example.demo.record.dto.genre;

import com.example.demo.record.entity.Genre;
import com.example.demo.record.entity.MusicRecord;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class CreateGenreRequest {

    private String name;
    private int popularity;
    private List<MusicRecord> musicRecords;

    public static Function<CreateGenreRequest, Genre> dtoToEntityMapper() {
        return request -> Genre.builder()
                .name(request.getName())
                .popularity(request.getPopularity())
                .musicRecords(new ArrayList<>())
                .build();
    }

}

package com.example.demo.record.dto.genre;

import com.example.demo.record.entity.Genre;
import com.example.demo.record.entity.MusicRecord;
import lombok.*;

import java.util.List;
import java.util.function.BiFunction;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class UpdateGenreRequest {

    private String name;
    private int popularity;

    public static BiFunction<Genre, UpdateGenreRequest, Genre> dtoToEntityUpdater() {
        return (genre, request) -> {
            genre.setPopularity(request.getPopularity());
            return genre;
        };
    }
}

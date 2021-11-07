package com.example.demo.record.dto.genre;

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
public class GetGenresResponse {

    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    @ToString
    @EqualsAndHashCode
    public static class Genre {
        private String name;
    }

    @Singular
    private List<Genre> genres;

    public static Function<Collection<com.example.demo.record.entity.Genre>,
            GetGenresResponse> entityToDtoMapper() {
        return genres -> {
            GetGenresResponseBuilder response = GetGenresResponse.builder();
            genres.stream()
                    .map(genre -> Genre.builder()
                            .name(genre.getName())
                            .build())
                    .forEach(response::genre);
            return response.build();
        };
    }

}

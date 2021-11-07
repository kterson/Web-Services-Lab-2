package com.example.demo.record.entity;

import lombok.*;


import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
@Entity
@Table(name = "music_records")
public class MusicRecord implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private String artist;

    @ManyToOne
    @JoinColumn(name = "genre")
    private Genre genre;
    private int yearOfRelease;
    private String label;

}

package com.example.demo.record.entity;

import com.example.demo.record.dto.record.GetMusicRecordResponse;
import lombok.*;
import org.springframework.http.ResponseEntity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
@Entity
@Table(name = "genres")
public class Genre implements Serializable {

    @Id
    private String name;
    private int popularity;

    @OneToMany(mappedBy = "genre")
    private List<MusicRecord> musicRecords;

    public List<GetMusicRecordResponse> getMusicRecordsDto() {
        List<GetMusicRecordResponse> musicRecordsNew = new ArrayList<>();
        for (MusicRecord musicRecord : musicRecords) {

            musicRecordsNew.add(GetMusicRecordResponse.entityToDtoMapper().apply(musicRecord));
        }
        return musicRecordsNew;
    }

}

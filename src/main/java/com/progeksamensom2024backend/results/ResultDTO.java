package com.progeksamensom2024backend.results;

import com.progeksamensom2024backend.discipline.Discipline;
import com.progeksamensom2024backend.participants.Participant;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class ResultDTO {

    private int id;
    private int participantId;
    private int disciplineId;
    private String date;
    private String value;

    public ResultDTO() {
    }

    public ResultDTO(Result result) {
        this.id = result.getId();
        this.participantId = result.getParticipant().getId();
        this.disciplineId = result.getDiscipline().getId();
        this.date = result.getDate();
        this.value = result.getValue();
    }
}

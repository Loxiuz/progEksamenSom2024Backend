package com.progeksamensom2024backend.results;

import com.progeksamensom2024backend.discipline.Discipline;
import com.progeksamensom2024backend.participants.Participant;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
@Getter
@Setter
@Entity
public class Result {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    private Participant participant;
    @ManyToOne
    private Discipline discipline;
    private String date;
    private String value;
}

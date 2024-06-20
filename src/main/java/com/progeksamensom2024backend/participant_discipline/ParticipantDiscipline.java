package com.progeksamensom2024backend.participant_discipline;

import com.progeksamensom2024backend.discipline.Discipline;
import com.progeksamensom2024backend.participants.Participant;
import jakarta.persistence.*;

@Entity
public class ParticipantDiscipline {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    private Participant participant;
    @ManyToOne
    private Discipline discipline;
}

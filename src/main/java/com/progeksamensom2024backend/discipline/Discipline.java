package com.progeksamensom2024backend.discipline;

import com.progeksamensom2024backend.participants.Participant;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
public class Discipline {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String resultType;
    @ManyToMany(mappedBy = "disciplines")
    private Set<Participant> participants = new HashSet<>();
}

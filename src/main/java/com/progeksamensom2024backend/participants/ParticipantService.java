package com.progeksamensom2024backend.participants;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ParticipantService {

    private final ParticipantRepository participantRepository;

    public ParticipantService(ParticipantRepository participantRepository) {
        this.participantRepository = participantRepository;
    }

    public List<Participant> getParticipants() {
        return participantRepository.findAll();
    }

    public Participant getParticipantById(int id) {
        return participantRepository.findById(id).orElse(null);
    }

    public Participant addParticipant(Participant participant) {
        return participantRepository.save(participant);
    }

    public Participant updateParticipant(int id, Participant participant){
        Participant existingParticipant = getParticipantById(id);
        if (existingParticipant == null) {
            throw new IllegalArgumentException("Participant with id " + id + " not found");
        }
        existingParticipant.setFullName(participant.getFullName());
        existingParticipant.setEmail(participant.getEmail());
        existingParticipant.setGender(participant.getGender());
        existingParticipant.setBirthdate(participant.getBirthdate());
        existingParticipant.setClub(participant.getClub());
        return participantRepository.save(existingParticipant);
    }

    public void deleteParticipant(int id) {
        participantRepository.deleteById(id);
    }
}

package com.progeksamensom2024backend.results;

import com.progeksamensom2024backend.discipline.Discipline;
import com.progeksamensom2024backend.discipline.DisciplineRepository;
import com.progeksamensom2024backend.participants.Participant;
import com.progeksamensom2024backend.participants.ParticipantRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ResultService {

    private final ResultRepository resultRepository;
    private final ParticipantRepository participantRepository;
    private final DisciplineRepository disciplineRepository;

        public ResultService(ResultRepository resultRepository, ParticipantRepository participantRepository, DisciplineRepository disciplineRepository) {
            this.resultRepository = resultRepository;
            this.participantRepository = participantRepository;
            this.disciplineRepository = disciplineRepository;
        }

        public List<ResultDTO> getResults() {
            return resultRepository.findAll().stream().map(ResultDTO::new).toList();
        }

        public ResultDTO getResult(int id) {
            Result result = resultRepository.findById(id)
                    .orElseThrow(() ->
                            new IllegalArgumentException("Result with id " + id + " not found")
                    );
            return new ResultDTO(result);
        }

        public ResultDTO createResult(ResultDTO rDTO) {
            Result result = new Result();
            updateResult(result, rDTO);
            return new ResultDTO(resultRepository.save(result));
        }

        public void updateResult(Result original , ResultDTO rDTO) {
            if(rDTO.getParticipantId() != 0) {
                Optional<Participant> participant = participantRepository.findById(rDTO.getParticipantId());
                if(participant.isPresent()) {
                    original.setParticipant(participant.get());
                } else {
                    original.setParticipant(null);
                }
            }
            if(rDTO.getDisciplineId() != 0) {
                Optional<Discipline> discipline = disciplineRepository.findById(rDTO.getDisciplineId());
                if(discipline.isPresent()) {
                    original.setDiscipline(discipline.get());
                } else {
                    original.setDiscipline(null);
                }
            }
            original.setValue(rDTO.getValue());
            original.setDate(rDTO.getDate());
        }

        public ResultDTO editResult(int id, ResultDTO rDTO) {
            Result result = resultRepository.findById(id)
                    .orElseThrow(() ->
                            new IllegalArgumentException("Result with id " + id + " not found")
                    );
            updateResult(result, rDTO);
            return new ResultDTO(resultRepository.save(result));
        }

        public void deleteResult(int id) {
            resultRepository.deleteById(id);
        }
}

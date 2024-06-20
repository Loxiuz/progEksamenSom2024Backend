package com.progeksamensom2024backend.participants;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/participants")
public class ParticipantController {

        private final ParticipantService participantService;

        public ParticipantController(ParticipantService participantService) {
            this.participantService = participantService;
        }

        @GetMapping
        public ResponseEntity<List<Participant>> getParticipants() {
            return ResponseEntity.ok(participantService.getParticipants());
        }

        @GetMapping("/{id}")
        public ResponseEntity<Participant> getParticipantById(@PathVariable int id) {
            Participant participant = participantService.getParticipantById(id);
            if (participant == null) {
                return ResponseEntity.notFound().build();
            } else {
                return ResponseEntity.ok(participant);
            }
        }

        @PostMapping
        public ResponseEntity<Participant> addParticipant(Participant participant) {
            return new ResponseEntity<>(participantService.addParticipant(participant), HttpStatus.CREATED);
        }

        @PutMapping("/{id}")
        public ResponseEntity<Participant> updateParticipant(@PathVariable int id,@RequestBody Participant participant) {
            try {
                return ResponseEntity.ok(participantService.updateParticipant(id, participant));
            } catch (IllegalArgumentException e) {
                return ResponseEntity.notFound().build();
            }
        }

        @DeleteMapping("/{id}")
        public ResponseEntity<Void> deleteParticipant(@PathVariable int id) {
            Participant participantForDeletion = participantService.getParticipantById(id);
            if (participantForDeletion == null) {
                return ResponseEntity.notFound().build();
            } else {
                participantService.deleteParticipant(id);
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        }
}

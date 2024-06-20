package com.progeksamensom2024backend.participants;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/participants)")
public class ParticipantController {

        private final ParticipantService participantService;

        public ParticipantController(ParticipantService participantService) {
            this.participantService = participantService;
        }
}

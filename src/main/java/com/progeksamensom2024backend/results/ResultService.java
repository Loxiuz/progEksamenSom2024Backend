package com.progeksamensom2024backend.results;

import org.springframework.stereotype.Service;

@Service
public class ResultService {

    private final ResultRepository resultRepository;

        public ResultService(ResultRepository resultRepository) {
            this.resultRepository = resultRepository;
        }
}

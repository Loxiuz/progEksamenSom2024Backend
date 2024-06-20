package com.progeksamensom2024backend.results;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/results")
public class ResultController {

    private final ResultService resultService;

    public ResultController(ResultService resultService) {
        this.resultService = resultService;
    }

    @GetMapping
    public ResponseEntity<List<ResultDTO>> getResults() {
        return ResponseEntity.ok(resultService.getResults());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResultDTO> getResult(@PathVariable int id) {
        ResultDTO result = resultService.getResultById(id);
        if (result == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(result);
        }
    }

    @PostMapping
    public ResponseEntity<ResultDTO> createResult(@RequestBody ResultDTO result) {
       return new ResponseEntity<>(resultService.createResult(result), HttpStatus.CREATED);
    }

    @PostMapping("/{id}")
    public ResponseEntity<ResultDTO> editResult(@PathVariable int id, @RequestBody ResultDTO result) {
        try {
            return ResponseEntity.ok(resultService.editResult(id, result));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteResult(@PathVariable int id) {
        ResultDTO resultForDeletion = resultService.getResultById(id);
        if (resultForDeletion == null) {
            return ResponseEntity.notFound().build();
        } else {
            resultService.deleteResult(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }
}

package com.personal.portfolio_api.controller;

import com.personal.portfolio_api.dto.SummaryDTO;
import com.personal.portfolio_api.exception.ApiResponeUtils;
import com.personal.portfolio_api.mapper.SummaryMapper;
import com.personal.portfolio_api.model.Summary;
import com.personal.portfolio_api.repository.SummaryRepository;
import com.personal.portfolio_api.service.SummaryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/portfolio/summary")
@RequiredArgsConstructor
public class SummaryController {

    private final SummaryService summaryService;

    @PostMapping("")
    public ResponseEntity<?> createSummary (@RequestBody SummaryDTO summaryDTO) {
        Summary summary = summaryService.create(summaryDTO);
        return ResponseEntity.ok(ApiResponeUtils.successRespone(
                SummaryMapper.INSTANCE.mapToSummaryDTO(summary)
                ));
    }

    @GetMapping("")
    public ResponseEntity<?> getAllSummary () {
        List<Summary> summaries = summaryService.getAll();
        return ResponseEntity.ok(ApiResponeUtils.successRespone(summaries));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getSummaryById (@PathVariable Long id) {
        Summary summary = summaryService.getById(id);
        return ResponseEntity.ok(ApiResponeUtils.successRespone(
                SummaryMapper.INSTANCE.mapToSummaryDTO(summary)
                ));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateSummary (@PathVariable Long id, @RequestBody SummaryDTO summaryDTO) {
        Summary summary = summaryService.update(id, summaryDTO);
        return ResponseEntity.ok(ApiResponeUtils.successRespone(
                SummaryMapper.INSTANCE.mapToSummaryDTO(summary)
                ));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteSummary (@PathVariable Long id) {
        summaryService.delete(id);
        return ResponseEntity.ok(ApiResponeUtils.successRespone("Delete summary id "+ id +" successfully"));
    }
}

package com.personal.portfolio_api.service;

import com.personal.portfolio_api.dto.SummaryDTO;

public interface SummaryService {
    void updateSummary(Long id, SummaryDTO summaryDTO);
}

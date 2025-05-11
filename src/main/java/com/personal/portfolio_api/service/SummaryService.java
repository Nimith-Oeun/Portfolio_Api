package com.personal.portfolio_api.service;

import com.personal.portfolio_api.dto.SummaryDTO;
import com.personal.portfolio_api.model.Summary;

public interface SummaryService {
    Summary updateSummary(Long id, SummaryDTO summaryDTO);
}

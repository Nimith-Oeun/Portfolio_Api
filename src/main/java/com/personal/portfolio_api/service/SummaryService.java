package com.personal.portfolio_api.service;

import com.personal.portfolio_api.dto.SummaryDTO;
import com.personal.portfolio_api.model.Summary;

import java.util.List;

public interface SummaryService {

    Summary create (SummaryDTO summaryDTO);
    Summary update (Long id,SummaryDTO summaryDTO);
    Summary getById (Long id);
    List<Summary> getAll ();
    void delete (Long id);
}

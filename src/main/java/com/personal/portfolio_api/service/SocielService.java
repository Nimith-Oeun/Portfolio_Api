package com.personal.portfolio_api.service;

import com.personal.portfolio_api.dto.SocielDTO;
import com.personal.portfolio_api.model.Sociel;

import java.util.List;

public interface SocielService {

    Sociel create (SocielDTO socielDTO);
    Sociel update (Long id, SocielDTO socielDTO);
    Sociel getById (Long id);
    List<Sociel> getAll ();
    void delete (Long id);
}

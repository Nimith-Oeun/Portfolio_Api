package com.personal.portfolio_api.service.impl;

import com.personal.portfolio_api.dto.SocielDTO;
import com.personal.portfolio_api.exception.ResoureNoteFoundException;
import com.personal.portfolio_api.exceptionHandle.HandleText;
import com.personal.portfolio_api.mapper.SocielMapper;
import com.personal.portfolio_api.model.Sociel;
import com.personal.portfolio_api.repository.SocielRepository;
import com.personal.portfolio_api.service.SocielService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class SocielServiceImpl implements SocielService {

    private final SocielRepository socielRepository;
    private final SocielMapper socielMapper;
    private final HandleText handleText;

    @Override
    public Sociel create(SocielDTO socielDTO) {

        handleText.HandleText(socielDTO.getFacebook());
        handleText.HandleText(socielDTO.getInstagram());
        handleText.HandleText(socielDTO.getTwitter());
        handleText.HandleText(socielDTO.getLinkedIn());

        Sociel sociel = socielMapper.mapToSociel(socielDTO);
        return socielRepository.save(sociel);
    }

    @Override
    public Sociel getById(Long id) {

       return socielRepository.findById(id)
                .orElseThrow(() -> new ResoureNoteFoundException("Sociel " + id + " not found"));
    }

    @Override
    public Sociel update(Long id, SocielDTO socielDTO) {
        Sociel socielId = getById(id);

        handleText.HandleText(socielDTO.getFacebook());
        handleText.HandleText(socielDTO.getInstagram());
        handleText.HandleText(socielDTO.getTwitter());
        handleText.HandleText(socielDTO.getLinkedIn());

        if (socielRepository.findByFacebook(socielDTO.getFacebook()).isPresent()){
            throw new RuntimeException("Facebook is already exist");
        }
        if (socielRepository.findByInstagram(socielDTO.getInstagram()).isPresent()){
            throw new RuntimeException("Instagram is already exist");
        }
        if (socielRepository.findByTwitter(socielDTO.getTwitter()).isPresent()){
            throw new RuntimeException("Twitter is already exist");
        }
        if (socielRepository.findByLinkedInIs(socielDTO.getLinkedIn()).isPresent()){
            throw new RuntimeException("LinkedIn is already exist");
        }

        socielId.setFacebook(socielDTO.getFacebook());
        socielId.setInstagram(socielDTO.getInstagram());
        socielId.setTwitter(socielDTO.getTwitter());
        socielId.setLinkedIn(socielDTO.getLinkedIn());

       return socielRepository.save(socielId);
    }

    @Override
    public List<Sociel> getAll() {
        return socielRepository.findAll();
    }

    @Override
    public void delete(Long id) {
        Sociel sociel = getById(id);
        socielRepository.delete(sociel);
    }
}

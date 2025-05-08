package com.personal.portfolio_api.service.impl;

import com.personal.portfolio_api.dto.SummaryDTO;
import com.personal.portfolio_api.enumerat.SummaryStatus;
import com.personal.portfolio_api.exception.BadRequestException;
import com.personal.portfolio_api.exception.ResoureNoteFoundException;
import com.personal.portfolio_api.mapper.SummaryMapper;
import com.personal.portfolio_api.model.Summary;
import com.personal.portfolio_api.repository.SummaryRepository;
import com.personal.portfolio_api.service.SummaryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class SummaryServiceImpl implements SummaryService {

    private final SummaryRepository summaryRepository;


    @Override
    public Summary create(SummaryDTO summaryDTO) {

        if (summaryRepository.findByEmail(summaryDTO.getEmail()) != null) {
            throw new BadRequestException("Email already exists");
        }
        if (summaryRepository.findByPhoneNumber(summaryDTO.getPhoneNumber()) != null) {
            throw new BadRequestException("Phone number already exists");
        }

        Summary summary = SummaryMapper.INSTANCE.mapToSummary(summaryDTO);
        return summaryRepository.save(summary);
    }

    @Override
    public Summary update(Long id, SummaryDTO summaryDTO) {
        Summary summary = getById(id);

        if (summaryRepository.findByEmail(summaryDTO.getEmail()) != null) {
            throw new BadRequestException("Email already exists");
        }

        if (summaryRepository.findByPhoneNumber(summaryDTO.getPhoneNumber()) != null) {
            throw new BadRequestException("Phone number already exists");
        }

        summary.setNationality(summaryDTO.getNationality());
        summary.setEmail(summaryDTO.getEmail());
        summary.setPhoneNumber(summaryDTO.getPhoneNumber());
        summary.setAddress(summaryDTO.getAddress());
        summary.setName(summaryDTO.getName());
        summary.setStatus(SummaryStatus.valueOf(summaryDTO.getStatus()));

        return summaryRepository.save(summary);
    }

    @Override
    public Summary getById(Long id) {
        return summaryRepository.findById(id).orElseThrow(() ->
                new ResoureNoteFoundException(" Summary "+ id +" not found"));

    }

    @Override
    public List<Summary> getAll() {
        if (summaryRepository.findAll().isEmpty()) {
            throw new ResoureNoteFoundException("No summarry");
        }
        return summaryRepository.findAll();
    }

    @Override
    public void delete(Long id) {
        Summary summary = getById(id);
        summaryRepository.delete(summary);
    }
}

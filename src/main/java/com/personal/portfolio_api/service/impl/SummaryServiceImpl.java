package com.personal.portfolio_api.service.impl;

import com.personal.portfolio_api.dto.SummaryDTO;
import com.personal.portfolio_api.enumerat.SummaryStatus;
import com.personal.portfolio_api.exception.BadRequestException;
import com.personal.portfolio_api.exception.ResoureNoteFoundException;
import com.personal.portfolio_api.model.Summary;
import com.personal.portfolio_api.repository.SummaryRepository;
import com.personal.portfolio_api.service.SummaryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class SummaryServiceImpl implements SummaryService {

    private final SummaryRepository summaryRepository ;

    @Override
    public void updateSummary(Long id, SummaryDTO summaryDTO) {

        if (summaryDTO.getEmail() != null){
            if (!summaryDTO.getEmail().matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
                throw new BadRequestException("Email " + summaryDTO.getEmail() + " is not Email format");
            }
        }

        if (summaryDTO.getPhoneNumber() != null){
            if (!summaryDTO.getPhoneNumber().matches("^[0-9]{9,10}$")) {
                throw new BadRequestException("Phone number " + summaryDTO.getPhoneNumber() + " is not Phone number format");
            }
        }

        if (summaryRepository.findByEmail(summaryDTO.getEmail()) != null){
            throw new BadRequestException("Email is already exist");
        }

        if (summaryRepository.findByPhoneNumber(summaryDTO.getPhoneNumber()) != null){
            throw new BadRequestException("Phone number is already exist");
        }

        Summary summaryId = summaryRepository.findById(id)
                .orElseThrow(() -> new ResoureNoteFoundException("Summary " + id + " not found"));

        summaryId.setName(summaryDTO.getName());
        summaryId.setAddress(summaryDTO.getAddress());
        summaryId.setEmail(summaryDTO.getEmail());
        summaryId.setPhoneNumber(summaryDTO.getPhoneNumber());
        summaryId.setStatus(SummaryStatus.valueOf(summaryDTO.getStatus()));
        summaryId.setNationality(summaryDTO.getNationality());

        summaryRepository.save(summaryId);

    }
}

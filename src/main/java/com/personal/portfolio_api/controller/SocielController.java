package com.personal.portfolio_api.controller;


import com.personal.portfolio_api.dto.*;
import com.personal.portfolio_api.exception.ApiResponeUtils;
import com.personal.portfolio_api.mapper.SocielMapper;
import com.personal.portfolio_api.model.Sociel;
import com.personal.portfolio_api.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/portfolio/sociel")
@RequiredArgsConstructor
public class SocielController {

    private final SocielService socielService;



    @PostMapping("/create")
    public ResponseEntity<?> createSociel(@RequestBody SocielDTO socielDTO) {
        Sociel sociel = socielService.create(socielDTO);
        return ResponseEntity.ok(ApiResponeUtils.successRespone(SocielMapper.INSTANCE.mapToSocielDTO(sociel)));
    }

    @GetMapping("")
    public ResponseEntity<?> getAll() {
        List<Sociel> sociels = socielService.getAll();
        List<SocielDTO> socielDTOList = sociels.stream()
                .map(SocielMapper.INSTANCE::mapToSocielDTO)
                .toList();
        return ResponseEntity.ok(ApiResponeUtils.successRespone(socielDTOList));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody SocielDTO socielDTO) {
        Sociel sociel = socielService.update(id, socielDTO);
        return ResponseEntity.ok(ApiResponeUtils.successRespone(SocielMapper.INSTANCE.mapToSocielDTO(sociel)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        socielService.delete(id);
        return ResponseEntity.ok(ApiResponeUtils.successRespone("Sociel Link deleted successfully"));
    }
}

package com.oussama.etudiantms.web;

import com.oussama.etudiantms.DTOs.RefDto;
import com.oussama.etudiantms.Repositories.FiliereRepository;
import com.oussama.etudiantms.Repositories.ModuleRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ReferentielController {

    private final FiliereRepository filiereRepository;
    private final ModuleRepository moduleRepository;

    public ReferentielController(FiliereRepository filiereRepository,
                                 ModuleRepository moduleRepository) {
        this.filiereRepository = filiereRepository;
        this.moduleRepository = moduleRepository;
    }

    @GetMapping("/filieres")
    public List<RefDto> filieres() {
        return filiereRepository.findAll().stream()
                .map(f -> new RefDto(f.getId(), f.getNomFiliere())).toList();
    }

    @GetMapping("/modules")
    public List<RefDto> modules() {
        return moduleRepository.findAll().stream()
                .map(m -> new RefDto(m.getId(), m.getNomModule())).toList();
    }
}

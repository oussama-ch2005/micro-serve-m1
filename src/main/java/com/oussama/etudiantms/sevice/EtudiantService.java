package com.oussama.etudiantms.sevice;

import com.oussama.etudiantms.DTOs.EtudiantRequestDto;
import com.oussama.etudiantms.DTOs.EtudiantResponseDto;
import com.oussama.etudiantms.Repositories.EtudiantRepository;
import com.oussama.etudiantms.Repositories.FiliereRepository;
import com.oussama.etudiantms.Repositories.ModuleRepository;
import com.oussama.etudiantms.entities.CarteEtudiant;
import com.oussama.etudiantms.entities.Etudiant;
import com.oussama.etudiantms.entities.Filiere;
import com.oussama.etudiantms.entities.Module;
import com.oussama.etudiantms.exceptions.ResourceNotFoundException;
import com.oussama.etudiantms.mapper.InterfaceMapper;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional(readOnly = true)

public class EtudiantService implements InterfaceService{
    private final EtudiantRepository etudiantRepository;
    private final FiliereRepository filiereRepository;
    private final ModuleRepository moduleRepository;
    private final InterfaceMapper mapper;



    public EtudiantService(EtudiantRepository etudiantRepository, InterfaceMapper mapper, FiliereRepository filiereRepository,
                           ModuleRepository moduleRepository) {
        this.etudiantRepository = etudiantRepository;
        this.mapper = mapper;
        this.filiereRepository = filiereRepository;
        this.moduleRepository = moduleRepository;
    }
    // ---------- méthodes utilitaires ----------
    private Etudiant findEtudiant(String id) {
        return etudiantRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Étudiant introuvable : " + id));
    }

    private Filiere findFiliere(Long id) {
        return (Filiere) filiereRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Filière introuvable : " + id));
    }

    private Module findModule(Long id) {
        return moduleRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Module introuvable : " + id));
    }




// ---------- lecture ----------

    @Override
    public EtudiantResponseDto getEtudiantById(String id) {
        Etudiant e=etudiantRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Utilisateur Introuvable"));;
        return mapper.EtudiantToEtudiantResonseDto(e);



    }

    @Override
    public EtudiantResponseDto getEtudiantByName(String nom) {
        Etudiant e =etudiantRepository.findByNom(nom)
                .orElseThrow(()-> new RuntimeException("Utilisateur Introuvable: "+ nom));
        return mapper.EtudiantToEtudiantResonseDto(e);



    }

    @Override
    public List<EtudiantResponseDto> getEtudiants() {

        return etudiantRepository.findAll().stream()
                .map(mapper::EtudiantToEtudiantResonseDto)
                .toList();
    }

    // ---------- écriture ----------
    @Override
    @Transactional
    public EtudiantResponseDto createEtudiant(EtudiantRequestDto dto) {
        Etudiant e = mapper.etudiantRequestDtoToEtudiant(dto);

        if (dto.getNom() != null && dto.getPrenom() != null) {
            int n = new Random().nextInt(90) + 10;
            e.setEmail(dto.getNom() + dto.getPrenom() + n + "@test.com");
        }
        if (dto.getFiliereId() != null) {
            e.setFiliere(findFiliere(dto.getFiliereId()));
        }
        e.setCarte(CarteEtudiant.builder()
                .numeroCarte("CE-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase())
                .build());

        return mapper.EtudiantToEtudiantResonseDto(etudiantRepository.save(e));
    }

    @Override
    @Transactional
    public EtudiantResponseDto updateEtudiant(String id, EtudiantRequestDto dto) {
        Etudiant e = findEtudiant(id);
        if (dto.getNom() != null)       e.setNom(dto.getNom());
        if (dto.getPrenom() != null)    e.setPrenom(dto.getPrenom());
        if (dto.getAge() != null)       e.setAge(dto.getAge());
        if (dto.getFiliereId() != null) e.setFiliere(findFiliere(dto.getFiliereId()));
        return mapper.EtudiantToEtudiantResonseDto(etudiantRepository.save(e));
    }


    @Override
    @Transactional
    public void deleteEtudiant(String id) {
        System.out.println("test ave=ant delete");
        etudiantRepository.deleteById(id);
        System.out.println("test apres delete");

    }
    // ---------- many-to-many ----------

    @Override
    @Transactional
    public EtudiantResponseDto inscrireModule(String etudiantId, Long moduleId) {
        Etudiant e = findEtudiant(etudiantId);
        e.addModule(findModule(moduleId));   // pas de save() : dirty checking
        return mapper.EtudiantToEtudiantResonseDto(e);
    }

    @Override
    @Transactional
    public EtudiantResponseDto retirerModule(String etudiantId, Long moduleId) {
        Etudiant e = findEtudiant(etudiantId);
        e.removeModule(findModule(moduleId));
        return mapper.EtudiantToEtudiantResonseDto(e);
    }
}

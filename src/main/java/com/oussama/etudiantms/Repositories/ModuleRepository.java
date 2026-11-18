package com.oussama.etudiantms.Repositories;

import com.oussama.etudiantms.entities.Module;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ModuleRepository extends JpaRepository<Module, Long> {
}

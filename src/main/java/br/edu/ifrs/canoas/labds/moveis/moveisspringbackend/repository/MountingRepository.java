package br.edu.ifrs.canoas.labds.moveis.moveisspringbackend.repository;

import br.edu.ifrs.canoas.labds.moveis.moveisspringbackend.domain.Mounting;
import br.edu.ifrs.canoas.labds.moveis.moveisspringbackend.domain.enumeration.MountingStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MountingRepository extends JpaRepository<Mounting, Long> {
    Mounting findByStatus(MountingStatus status);
}

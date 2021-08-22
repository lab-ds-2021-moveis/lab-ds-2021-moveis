package br.edu.ifrs.canoas.labds.moveis.moveisspringbackend.service.impl;

import br.edu.ifrs.canoas.labds.moveis.moveisspringbackend.domain.Employee;
import br.edu.ifrs.canoas.labds.moveis.moveisspringbackend.domain.Mounting;
import br.edu.ifrs.canoas.labds.moveis.moveisspringbackend.domain.enumeration.MountingStatus;
import br.edu.ifrs.canoas.labds.moveis.moveisspringbackend.repository.EmployeeRepository;
import br.edu.ifrs.canoas.labds.moveis.moveisspringbackend.repository.MountingRepository;
import br.edu.ifrs.canoas.labds.moveis.moveisspringbackend.service.MountingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class MountingServiceImpl extends BaseServiceImpl <Mounting> implements MountingService {

    @Autowired
    private MountingRepository mountingRepository;

    @Override
    public Mounting findByStatus(MountingStatus status) {
        return mountingRepository.findByStatus(MountingStatus.WAITING);
    }
}

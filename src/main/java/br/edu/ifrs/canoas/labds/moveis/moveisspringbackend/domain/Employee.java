package br.edu.ifrs.canoas.labds.moveis.moveisspringbackend.domain;

import br.edu.ifrs.canoas.labds.moveis.moveisspringbackend.domain.enumeration.EmployeeRole;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "funcionario")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Employee implements BaseEntity, Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator", sequenceName = "funcionario_id_seq", allocationSize = 1)
    @Column(name = "id_funcionario")
    private long id;

    @Column(name = "credencial", unique = true, nullable = false)
    private String credential;

    @Column(name = "nome", nullable = false)
    private String name;

    @Column(name = "senha", nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(name = "cargo", nullable = false)
    private EmployeeRole role;

}

package br.edu.ifrs.canoas.labds.moveis.moveisspringbackend.domain;

import br.edu.ifrs.canoas.labds.moveis.moveisspringbackend.domain.enumeration.MountingStatus;
import br.edu.ifrs.canoas.labds.moveis.moveisspringbackend.domain.enumeration.RequestStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "montagem")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Mounting implements BaseEntity, Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator", sequenceName = "montagem_id_seq", allocationSize = 1)
    @Column(name = "id_montagem")
    private long id;

    @Column (name = "data", nullable = true)
    private Instant dateRequest;

    @Column (name = "local")
    private String place;

    @Enumerated (EnumType.STRING)
    @Column (name = "status", nullable = false)
    private MountingStatus status;

    @OneToOne(mappedBy = "mounting", fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private Purchase purchase;
}

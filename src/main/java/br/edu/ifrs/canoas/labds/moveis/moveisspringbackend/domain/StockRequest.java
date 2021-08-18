package br.edu.ifrs.canoas.labds.moveis.moveisspringbackend.domain;

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
@Table (name = "solicitacao_estoque")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class StockRequest implements BaseEntity, Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator", sequenceName = "solicitacao_estoque_id_seq", allocationSize = 1)
    @Column(name = "id_solicitacao")
    private long id;

    @Column (name = "data_solicitacao", nullable = true)
    private Instant dateRequest;

    @Column (name = "qtd_solicitada", nullable = false)
    private int amount = 0;

    @Enumerated (EnumType.STRING)
    @Column (name = "status", nullable = false)
    private RequestStatus status;

    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn (name = "id_produto")
    private Product product;
}

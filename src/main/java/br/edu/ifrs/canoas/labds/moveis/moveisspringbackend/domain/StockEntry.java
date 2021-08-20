package br.edu.ifrs.canoas.labds.moveis.moveisspringbackend.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "entrada_estoque")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class StockEntry implements BaseEntity, Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator", sequenceName = "entrada_estoque_id_seq", allocationSize = 1)
    @Column(name = "id_estoque")
    private long id;

    @Column(name = "quantidade")
    private int amount = 0;

    @Column(name = "data_recebimento")
    @CreatedDate
    private Instant createdAt = Instant.now();

    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn (name = "id_produto")
    private Product product;
}
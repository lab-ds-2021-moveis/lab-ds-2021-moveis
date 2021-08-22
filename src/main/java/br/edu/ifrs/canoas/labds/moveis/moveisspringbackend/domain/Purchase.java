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
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "compra")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Purchase implements BaseEntity, Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator", sequenceName = "compra_id_seq", allocationSize = 1)
    @Column(name = "id_compra")
    private long id;

    @Column(name = "valor_total", nullable = false)
    private Double totalValue;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cpf_cliente", referencedColumnName = "cpf")
    private Customer customer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_funcionario", nullable = true)
    private Employee employee = null;

    // TODO: Relacionar objeto de montagem. OneToOne

    @Column(name = "created_at")
    @CreatedDate
    private Instant createdAt = Instant.now();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "purchase")
    private List<ProductPurchase> productPurchases = new ArrayList<>();

    @OneToOne (fetch = FetchType.LAZY)
    @JoinColumn (name = "id_montagem")
    private Mounting mounting;

    public String toString() {
        return "Purchase" +
            "(" +
                "id=" + id + ";" +
                "totalValue=" + totalValue + ";" +
                "createdAt=" + createdAt + ";" +
            ")";
    }

}

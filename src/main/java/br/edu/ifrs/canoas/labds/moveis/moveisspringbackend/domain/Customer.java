package br.edu.ifrs.canoas.labds.moveis.moveisspringbackend.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "cliente")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Customer implements BaseEntity, Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator", sequenceName = "cliente_id_seq", allocationSize = 1)
    @Column(name = "id_cliente")
    private long id;

    @Column(name = "cpf", unique = true, nullable = false)
    private String cpf;

    @Column(name = "telefone", unique = true, nullable = false)
    private String phone;

    @Column(name = "email", unique = true, nullable = false)
    private String email;

    @Column(name = "nome")
    private String name;

    @Column(name = "senha")
    private String password;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "customer")
    private List<Purchase> purchases = new ArrayList<>();

    public static String role = "CUSTOMER";
}

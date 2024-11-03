package br.com.adam.studyingspringboot.model;

import jakarta.persistence.*;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

@Table(name = "restaurante")
@Entity
public class RestauranteModel extends RepresentationModel<RestauranteModel> implements Serializable  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "nome", nullable = false)
    private String nome;
    @Column(name = "descricao", nullable = false)
    private String descricao;
    @OneToMany(mappedBy = "restaurante", cascade = CascadeType.ALL)
    private List<VoteModel> votes;

    @Column(name = "cor_restaurante", nullable = false)
    private String cor;

    public long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}

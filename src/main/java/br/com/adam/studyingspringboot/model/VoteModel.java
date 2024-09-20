package br.com.adam.studyingspringboot.model;

import jakarta.persistence.*;

import java.io.Serializable;

@Table(name = "votes")
@Entity
public class VoteModel implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "registro")
    private String registro;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_restaurante", referencedColumnName = "id")
    private RestauranteModel restaurante;

    public long getId() {
        return id;
    }

    public String getRegistro() {
        return registro;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setRestaurante(RestauranteModel restaurante) {
        this.restaurante = restaurante;
    }

    public void setRegistro(String registro) {
        this.registro = registro;
    }

    public RestauranteModel getRestaurante() {
        return restaurante;
    }
}

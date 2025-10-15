package entities;

import jakarta.persistence.*;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "cargos")
@Getter @Setter
public class Cargo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre", length=100, nullable = false)
    private String nombre;

    @Override
    public String toString() {
        return "Cargos{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                '}';
    }
}

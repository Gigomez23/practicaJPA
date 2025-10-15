package repository;

import entities.Cargo;
import java.util.List;

public interface ICargo {
    Cargo guardar(Cargo cargo);
    Cargo buscarPorId(Long id);
    Cargo eliminar(Long id);
    Cargo actualizar(Cargo cargo);
    List<Cargo> listar();
}

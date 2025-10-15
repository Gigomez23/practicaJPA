package repository;

import entities.Empleado;
import java.util.List;

public interface IEmpleado {
    Empleado guardar(Empleado empleado);
    Empleado buscarPorId(Long id);
    Empleado eliminar (Long id);
    Empleado actualizar(Empleado empleado);
    List<Empleado> listar();
}

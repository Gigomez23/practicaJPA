package repository.dao;

import entities.Cargo;
import entities.Empleado;
import repository.IEmpleado;
import jakarta.persistence.EntityManager;

import java.util.List;


public class EmpleadoDao implements IEmpleado{
    private final EntityManager em;

    public EmpleadoDao(EntityManager em) {
        this.em = em;
    }

    @Override
    public Empleado guardar(Empleado empleado) {
        if(empleado.getId()==null){
            em.getTransaction().begin();
            em.persist(empleado);
            em.getTransaction().commit();
            return empleado;
        }
        return em.merge(empleado);
    }

    @Override
    public Empleado buscarPorId(Long id) {
        return em.find(Empleado.class, id);
    }

    @Override
    public Empleado eliminar(Long id) {
        Empleado empleado = buscarPorId(id);
        if (empleado != null) {
            em.getTransaction().begin();
            em.remove(empleado);
            em.getTransaction().commit();
        }
        return empleado;
    }

    @Override
    public Empleado actualizar(Empleado empleado) {
        if (empleado.getId() != null) {
            em.getTransaction().begin();
            Empleado actualizado = em.merge(empleado);
            em.getTransaction().commit();
            return actualizado;
        }
        return null;
    }

    @Override
    public List<Empleado> listar() {
        List<Empleado> lista = em.createQuery("from Empleado", Empleado.class).getResultList();
        return lista;
    }

}

package repository.dao;

import entities.Cargo;
import entities.Empleado;
import repository.ICargo;
import jakarta.persistence.EntityManager;

import java.util.List;

public class CargoDao implements ICargo {
    private final EntityManager em;

    public CargoDao(EntityManager em) {
        this.em = em;
    }

    @Override
    public Cargo guardar(Cargo cargo) {
        if(cargo.getId() == null){
            em.getTransaction().begin();
            em.persist(cargo);
            em.getTransaction().commit();
            return cargo;
        }
        return em.merge(cargo);
    }

    @Override
    public Cargo buscarPorId(Long id) {
        return em.find(Cargo.class, id);
    }

    @Override
    public Cargo eliminar(Long id) {
        Cargo cargo = buscarPorId(id);
        if (cargo != null) {
            em.getTransaction().begin();
            em.remove(cargo);
            em.getTransaction().commit();
        }
        return cargo;
    }

    @Override
    public Cargo actualizar(Cargo cargo) {
        if (cargo.getId() != null) {
            em.getTransaction().begin();
            Cargo actualizado = em.merge(cargo);
            em.getTransaction().commit();
            return actualizado;
        }
        return null;
    }

    @Override
    public List<Cargo> listar() {
        List<Cargo> lista = em.createQuery("from Cargo", Cargo.class).getResultList();
        return lista;
    }

}

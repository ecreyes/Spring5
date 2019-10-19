package com.ecreyes.springapp.model.dao;

import com.ecreyes.springapp.model.entity.Cliente;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository("ClienteDaoJPA")
public class ClienteDaoImpl implements IClienteDao {

    /* se inyecta la unidad de persistencia segun la configuración en el application.properties,
     * si no hay nada configurado se utiliza H2 por defecto.  */
    @PersistenceContext
    private EntityManager entityManager; //usado para realizar operaciones a la bd mediante objetos

    @Override
    @Transactional(readOnly = true)
    public List<Cliente> findAll() {
        return entityManager.createQuery("from Cliente").getResultList();
    }

    @Override
    @Transactional //solo porque es de escritura
    public void save(Cliente cliente) {
        entityManager.persist(cliente); //guarda el cliente en bd
    }
}

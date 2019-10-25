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
    public List<Cliente> findAll() {
        return entityManager.createQuery("from Cliente").getResultList();
    }

    @Override
    public Cliente findOne(Long id) {
        return entityManager.find(Cliente.class,id);
    }

    @Override
    public void save(Cliente cliente) {
        if(cliente.getId()!=null && cliente.getId()>0){
            entityManager.merge(cliente); // actualizo el registro
        }else{
            entityManager.persist(cliente); //guarda el cliente en bd
        }
    }

    @Override
    public void delete(Long id) {
        Cliente cliente = findOne(id);
        entityManager.remove(cliente);
    }
}

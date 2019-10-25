package com.ecreyes.springapp.model.service;

import com.ecreyes.springapp.model.dao.IClienteDao;
import com.ecreyes.springapp.model.entity.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service("ClienteService")
public class ClienteServiceImpl implements IClienteService {
    @Autowired
    @Qualifier("ClienteDaoJPA")
    private IClienteDao clienteDao;


    @Override
    @Transactional
    public List<Cliente> findAll() {
        return clienteDao.findAll();
    }

    @Override
    @Transactional
    public void save(Cliente cliente) {
        clienteDao.save(cliente);
    }

    @Override
    @Transactional
    public Cliente findOne(Long id) {
        return clienteDao.findOne(id);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        clienteDao.delete(id);
    }
}

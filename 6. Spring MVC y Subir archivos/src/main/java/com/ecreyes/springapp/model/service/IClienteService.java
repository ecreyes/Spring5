package com.ecreyes.springapp.model.service;

import com.ecreyes.springapp.model.entity.Cliente;

import java.util.List;

public interface IClienteService {
    List<Cliente> findAll();
    void save(Cliente cliente);
    Cliente findOne(Long id);
    void delete(Long id);
}

package com.ecreyes.springapp.model.dao;

import com.ecreyes.springapp.model.entity.Cliente;

import java.util.List;

public interface IClienteDao {
    List<Cliente> findAll();
}

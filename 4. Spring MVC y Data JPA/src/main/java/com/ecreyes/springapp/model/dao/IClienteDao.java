package com.ecreyes.springapp.model.dao;

import com.ecreyes.springapp.model.entity.Cliente;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface IClienteDao extends CrudRepository<Cliente,Long> {

}

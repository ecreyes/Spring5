package com.ecreyes.springapp.model.service;

import org.springframework.stereotype.Service;

@Service
public class MiServicio implements IService {
    @Override
    public String operacion() {
        return "operación muy costosa....";
    }
}

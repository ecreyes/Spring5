package com.ecreyes.springapp.model.service;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Primary
@Service("MiServicio")
public class MiServicio implements IService {
    @Override
    public String operacion() {
        return "operación muy costosa....";
    }
}

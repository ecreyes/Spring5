package com.ecreyes.springapp.model.service;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service("MiServicioComplejo")
public class MiServicioComplejo implements IService {
    @Override
    public String operacion() {
        return "esta es otro tipo de operación compleja";
    }
}

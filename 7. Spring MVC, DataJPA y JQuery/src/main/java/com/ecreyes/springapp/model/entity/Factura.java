package com.ecreyes.springapp.model.entity;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name = "facturas") //nombre de la tabla en bd
public class Factura {
    @Id //campo id de la tabla
    @GeneratedValue(strategy = GenerationType.IDENTITY) //id autoincremental
    private Long id;

    private String descripcion;

    private String observacion;

    @Column(name = "create_at") //cambiar el nombre a la columna
    @Temporal(TemporalType.DATE) //formato de fecha completo dia,mes,año
    @NotNull
    private Date createAt;

    @ManyToOne(fetch = FetchType.LAZY)
    private Cliente cliente;

    @PrePersist
    private void prePersis(){
        createAt = new Date();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
}

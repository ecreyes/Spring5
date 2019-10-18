package com.ecreyes.springapp.model.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity // se declara la clase como una entidad para la bd
@Table(name = "clientes") //nombre de la tabla en bd
public class Cliente implements Serializable {
    @Id //campo id de la tabla
    @GeneratedValue(strategy = GenerationType.IDENTITY) //id autoincremental
    private Long id;
    private String nombre;
    private String apellido;
    private String email;
    @Column(name = "create_at") //cambiar el nombre a la columna
    @Temporal(TemporalType.TIMESTAMP) //formato de fecha completo dia,mes,año, minuto,hora,seg
    private Date createAt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }
}

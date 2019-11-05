package com.ecreyes.springapp.model.entity;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

@Entity // se declara la clase como una entidad para la bd
@Table(name = "clientes") //nombre de la tabla en bd
public class Cliente implements Serializable {
    @Id //campo id de la tabla
    @GeneratedValue(strategy = GenerationType.IDENTITY) //id autoincremental
    private Long id;

    @NotEmpty
    @Size(min=2,max=15)
    private String nombre;

    @NotEmpty
    @Size(min=2,max=15)
    private String apellido;

    @NotEmpty
    @Email
    private String email;

    @Column(name = "create_at") //cambiar el nombre a la columna
    @Temporal(TemporalType.DATE) //formato de fecha completo dia,mes,año
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull
    private Date createAt;

    private String foto;

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

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

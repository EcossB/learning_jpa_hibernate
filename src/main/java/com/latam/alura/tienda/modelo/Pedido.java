package com.latam.alura.tienda.modelo;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

/*
mapeando clase pedido la cual se conecta con la clase cliente.
 */
@Entity
@Table(name = "pedidos")
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate fecha = LocalDate.now();
    private BigDecimal valor_total;

    @ManyToOne // un cliente tiene mucho pedidos
    private Clientes clientes;

//    @ManyToMany
//    @JoinTable(name = "items_pedido") // JPA con esta anotacion crea una nueva tabla
    @OneToMany
    private List<ItemsPedido> items;

    public Pedido() {
    }

    public Pedido(Clientes clientes) {
        this.clientes = clientes;
    }

    public Long getId() {
        return id;
    }


    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public BigDecimal getValor_total() {
        return valor_total;
    }

    public void setValor_total(BigDecimal valor_total) {
        this.valor_total = valor_total;
    }

    public Clientes getClientes() {
        return clientes;
    }

    public void setClientes(Clientes clientes) {
        this.clientes = clientes;
    }
}

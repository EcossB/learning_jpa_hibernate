package com.latam.alura.tienda.modelo;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
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

    public List<ItemsPedido> getItems() {
        return items;
    }

    public void setItems(List<ItemsPedido> items) {
        this.items = items;
    }

    private BigDecimal valor_total = new BigDecimal(0);

    @ManyToOne(fetch = FetchType.LAZY  ) // un cliente tiene mucho pedidos
    private Clientes clientes;

//    @ManyToMany
//    @JoinTable(name = "items_pedido") // JPA con esta anotacion crea una nueva tabla
    @OneToMany (mappedBy = "pedido", cascade = CascadeType.ALL) // la propiedad cascade hace que tambien se modifique los valores en item pedido Cascade crea un efecto dominó en las operaciones realizadas en una entidad.
    private List<ItemsPedido> items = new ArrayList<>();

    public Pedido() {
    }

    public Pedido(Clientes clientes) {
        this.clientes = clientes;
    }

    public void agregarItems(ItemsPedido item){
        item.setPedido(this);
        this.items.add(item);
        this.valor_total = this.valor_total.add(item.getValor());
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

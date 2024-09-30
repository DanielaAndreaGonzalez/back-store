package com.store.back.models.tablas;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "ventas")
public class Venta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Cliente cliente;

    @ManyToOne
    private Empleado empleado;
    private Date fecha;
    private double total;
/*
Una Venta puede tener muchos DetalleVenta:
Cada venta puede incluir múltiples productos o ítems.
Cada ítem en la venta se representa como un DetalleVenta separado.
 */
    @OneToMany(mappedBy = "venta", cascade = CascadeType.ALL)
    private List<DetalleVenta> detalles;
}

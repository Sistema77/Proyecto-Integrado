package Proyecto.Java.Final.DTO;

import java.util.Calendar;
import Proyecto.Java.Final.Util.FechaHoraComparable;

public class FacturaDTO extends FechaHoraComparable {
    private Long id_factura;
    private String descripcion;
    private String tipoFactura;
    private CuentaDTO cuenta;

    public FacturaDTO() {
        super(null);
    }

    public FacturaDTO(Calendar fecha_Hora) {
        super(fecha_Hora);
    }

    public Long getId_factura() {
        return id_factura;
    }

    public void setId_factura(Long id_factura) {
        this.id_factura = id_factura;
    }

    public Double getCantidadDinero() {
        return cantidadDinero;
    }

    public void setCantidadDinero(Double cantidadDinero) {
        this.cantidadDinero = cantidadDinero;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getTipoFactura() {
        return tipoFactura;
    }

    public void setTipoFactura(String tipoFactura) {
        this.tipoFactura = tipoFactura;
    }

    public CuentaDTO getCuenta() {
        return cuenta;
    }

    public void setCuenta(CuentaDTO cuenta) {
        this.cuenta = cuenta;
    }
}

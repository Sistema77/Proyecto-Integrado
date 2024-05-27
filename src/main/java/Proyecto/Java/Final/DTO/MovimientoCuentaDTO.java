package Proyecto.Java.Final.DTO;

import java.util.Calendar;
import Proyecto.Java.Final.DAO.CuentaDAO;
import Proyecto.Java.Final.Util.FechaHoraComparable;

public class MovimientoCuentaDTO extends FechaHoraComparable {
    private Long id_movimiento;
    private Double cantidadDinero;
    private String tipo_movimiento;
    private CuentaDAO cuenta;

    public MovimientoCuentaDTO() {
        super(null);
    }

    public MovimientoCuentaDTO(Calendar fecha_Hora) {
        super(fecha_Hora);
    }

    public Long getId_movimiento() {
        return id_movimiento;
    }

    public void setId_movimiento(Long id_movimiento) {
        this.id_movimiento = id_movimiento;
    }

    public Double getCantidad_dinero() {
        return cantidadDinero;
    }

    public void setCantidad_dinero(Double cantidad_dinero) {
        this.cantidadDinero = cantidad_dinero;
    }

    public String getTipo_movimiento() {
        return tipo_movimiento;
    }

    public void setTipo_movimiento(String tipo_movimiento) {
        this.tipo_movimiento = tipo_movimiento;
    }

    public CuentaDAO getCuenta() {
        return cuenta;
    }

    public void setCuenta(CuentaDAO cuenta) {
        this.cuenta = cuenta;
    }

    @Override
    public String toString() {
        return "MovimientoCuentaDTO [id_movimiento=" + id_movimiento + ", cantidad_dinero=" + cantidadDinero
                + ", tipo_movimiento=" + tipo_movimiento + ", fecha_Hora=" + fecha_Hora + ", cuenta=" + cuenta + "]";
    }
}

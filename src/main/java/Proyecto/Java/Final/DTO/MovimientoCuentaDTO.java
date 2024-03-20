package Proyecto.Java.Final.DTO;

import java.util.Calendar;

public class MovimientoCuentaDTO {
  // Atributos
    
    private Long id_movimiento;
    private Double cantidad_dinero;
    private String descripcion;
    private String tipo_movimiento;
    private Calendar fecha_Hora;
    private CuentaDTO cuenta;

    // Constructor
    public MovimientoCuentaDTO() {}

    // Getter / Setter
	public Long getId_movimiento() {
		return id_movimiento;
	}

	public void setId_movimiento(Long id_movimiento) {
		this.id_movimiento = id_movimiento;
	}

	public Double getCantidad_dinero() {
		return cantidad_dinero;
	}

	public void setCantidad_dinero(Double cantidad_dinero) {
		this.cantidad_dinero = cantidad_dinero;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getTipo_movimiento() {
		return tipo_movimiento;
	}

	public void setTipo_movimiento(String tipo_movimiento) {
		this.tipo_movimiento = tipo_movimiento;
	}

	public Calendar getFecha_Hora() {
		return fecha_Hora;
	}

	public void setFecha_Hora(Calendar fecha_Hora) {
		this.fecha_Hora = fecha_Hora;
	}

	public CuentaDTO getCuenta() {
		return cuenta;
	}

	public void setCuenta(CuentaDTO cuenta) {
		this.cuenta = cuenta;
	}
    
}

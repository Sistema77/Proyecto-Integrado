package Proyecto.Java.Final.DTO;

import java.util.Calendar;

import Proyecto.Java.Final.DAO.CuentaDAO;

public class MovimientoCuentaDTO {
  // Atributos
    
    private Long id_movimiento;
    private Double cantidad_dinero;
    private String descripcion;
    private String tipo_movimiento;
    private Calendar fecha_Hora;
    private String cuenta_envia;

	private CuentaDAO cuenta;

    // Constructor

    public MovimientoCuentaDTO() {}

    // Getter / Setter
    
    public String getCuenta_envia() {
 		return cuenta_envia;
 	}

 	public void setCuenta_envia(String cuenta_envia) {
 		this.cuenta_envia = cuenta_envia;
 	}
    
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

	public CuentaDAO getCuenta() {
		return cuenta;
	}

	public void setCuenta(CuentaDAO cuenta) {
		this.cuenta = cuenta;
	}

	@Override
	public String toString() {
		return "MovimientoCuentaDTO [id_movimiento=" + id_movimiento + ", cantidad_dinero=" + cantidad_dinero
				+ ", descripcion=" + descripcion + ", tipo_movimiento=" + tipo_movimiento + ", fecha_Hora=" + fecha_Hora
				+ ", cuenta_envia=" + cuenta_envia + ", cuenta=" + cuenta + "]";
	}

	
	
    
}

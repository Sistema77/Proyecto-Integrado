package Proyecto.Java.Final.DTO;

import java.util.Calendar;

import Proyecto.Java.Final.DAO.CuentaDAO;

public class TransacionDTO {
	
	private Long id_trasaciones;
	private Double cantidadDinero;
	private Calendar fecha_Hora;
	private String descripcion;
	private String cuenta_enviada;
	private CuentaDAO cuenta;


	public TransacionDTO() {
	}

	public Long getId_trasaciones() {
		return id_trasaciones;
	}

	public void setId_trasaciones(Long id_trasaciones) {
		this.id_trasaciones = id_trasaciones;
	}

	public Double getCantidadDinero() {
		return cantidadDinero;
	}

	public void setCantidadDinero(Double cantidadDinero) {
		this.cantidadDinero = cantidadDinero;
	}

	public Calendar getFecha_Hora() {
		return fecha_Hora;
	}

	public void setFecha_Hora(Calendar fecha_Hora) {
		this.fecha_Hora = fecha_Hora;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public CuentaDAO getCuenta() {
		return cuenta;
	}

	public void setCuenta(CuentaDAO cuenta) {
		this.cuenta = cuenta;
	}

	public String getCuenta_enviada() {
		return cuenta_enviada;
	}

	public void setCuenta_enviada(String cuenta_enviada) {
		this.cuenta_enviada = cuenta_enviada;
	}
	
	
}
package Proyecto.Java.Final.DAO;

import java.util.Calendar;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "factura", schema = "schemabody")
public class FacturaDAO {
	// Atributos
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id_factura")
	private Long id_factura;
	
	@Column(name = "fecha_Hora")
	private Calendar fecha_Hora;
	
	@Column(name = "cantidadDinero")
	private Double cantidadDinero;
	
	@Column(name = "descripcion")
	private String descripcion;
	
	@Column(name = "Tipo_Factura")
	private String tipoFactura;
	
	@ManyToOne
	@JoinColumn(name = "id_cuenta")
	private CuentaDAO cuenta;
	
	// Constructor
	public FacturaDAO() {}

	// Getter/Setter
	public Long getId_factura() {
		return id_factura;
	}

	public void setId_factura(Long id_factura) {
		this.id_factura = id_factura;
	}

	public Calendar getFecha_Hora() {
		return fecha_Hora;
	}

	public void setFecha_Hora(Calendar fecha_Hora) {
		this.fecha_Hora = fecha_Hora;
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

	public CuentaDAO getCuenta() {
		return cuenta;
	}

	public void setCuenta(CuentaDAO cuenta) {
		this.cuenta = cuenta;
	}
}

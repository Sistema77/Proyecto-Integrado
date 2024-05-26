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
@Table(name = "movimientocuenta", schema = "schemabody")
public class MovimientoCuentaDAO {
	// Atributos
	
		@Id
	    @GeneratedValue(strategy=GenerationType.AUTO)
	    @Column(name = "id_movimiento")
	    private Long id_movimiento;
		
		@Column(name = "cantidad_dinero")
		private Double cantidad_dinero;
		
		@Column(name = "tipo_movimiento")
	    private String tipo_movimiento;
		
		@Column(name = "fecha_Hora")
		private Calendar fecha_Hora;
		
		@ManyToOne
		@JoinColumn(name = "cuenta")
		private CuentaDAO cuenta;

		//Constructor
		public MovimientoCuentaDAO(){}

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

		public Calendar getFecha_Hora() {
			return fecha_Hora;
		}

		public void setFecha_Hora(Calendar fecha_Hora) {
			this.fecha_Hora = fecha_Hora;
		}
		
}

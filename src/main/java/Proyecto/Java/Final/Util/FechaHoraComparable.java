package Proyecto.Java.Final.Util;

import java.util.Calendar;

public abstract class FechaHoraComparable implements Comparable<FechaHoraComparable> {
    protected Calendar fecha_Hora;
    protected Double cantidadDinero;

    public FechaHoraComparable(Calendar fecha_Hora) {
        this.fecha_Hora = fecha_Hora;
    }

    public Calendar getFecha_Hora() {
        return fecha_Hora;
    }

    
    public Double getCantidadDinero() {
		return cantidadDinero;
	}

	public void setFecha_Hora(Calendar fecha_Hora) {
		this.fecha_Hora = fecha_Hora;
	}

	public void setCantidadDinero(Double cantidadDinero) {
		this.cantidadDinero = cantidadDinero;
	}

	@Override
    public int compareTo(FechaHoraComparable o) {
        return this.fecha_Hora.compareTo(o.getFecha_Hora());
    }
}

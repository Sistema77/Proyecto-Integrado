package Proyecto.Java.Final.Util;

import java.util.Calendar;

public abstract class FechaHoraComparable implements Comparable<FechaHoraComparable> {
    protected Calendar fecha_Hora;

    public FechaHoraComparable(Calendar fecha_Hora) {
        this.fecha_Hora = fecha_Hora;
    }

    public Calendar getFecha_Hora() {
        return fecha_Hora;
    }

    @Override
    public int compareTo(FechaHoraComparable o) {
        return this.fecha_Hora.compareTo(o.getFecha_Hora());
    }
}

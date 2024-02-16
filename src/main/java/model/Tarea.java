package model;

import java.sql.Date;
import org.bson.types.ObjectId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Tarea {
	private ObjectId _id;
	private String nombre;
	private String descripcion;
	private String estado;
	private Date fecha_vencimiento;
	private ObjectId id_proyecto;

    public Tarea(String nombre, String descripcion, Date fechaFin, ObjectId proyectoID) {
		super();
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.estado = "Progreso";
		this.fecha_vencimiento = fechaFin;
		this.id_proyecto = proyectoID;
    }
    
    public Tarea(String estado) {
    	this.estado = estado;
    }
}

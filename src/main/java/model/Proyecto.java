package model;

import java.sql.Date;

import org.bson.types.ObjectId;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Proyecto {
	private ObjectId _id;
	private String nombre;
	private String descripcion;
	private Date fecha_inicio;
	private Date fecha_fin;
	private ObjectId idEmpleado;
	
	public Proyecto(String nombre, String descripcion, Date fecha_inicio, Date fecha_fin, ObjectId idEmpleado) {
		super();
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.fecha_inicio = fecha_inicio;
		this.fecha_fin = fecha_fin;
		this.idEmpleado = idEmpleado;
	}	

}

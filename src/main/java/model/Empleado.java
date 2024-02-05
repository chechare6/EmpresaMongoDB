package model;

import java.util.Date;

import org.bson.types.ObjectId;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Empleado {
	private ObjectId _id;
	private String nombre;
	private String puesto;
	private Date fechaEntrada;
	
	// CONSTRUCTORES
	public Empleado(String nombre, String puesto) {
		this.nombre = nombre;
		this.puesto = puesto;
	}
}

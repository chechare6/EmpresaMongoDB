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
	private Double salario;
	private Date fechaEntrada;
	
	public Empleado(String nombre, String puesto, Double salario, Date fechaEntrada) {
		this.nombre = nombre;
		this.puesto = puesto;
		this.salario = salario;
		this.fechaEntrada = fechaEntrada;
	}
}

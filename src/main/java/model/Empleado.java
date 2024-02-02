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
	private double salario;
	private Date fechaEntrada;
	
	// CONSTRUCTORES
//	public Empleado(String nombre, String puesto) {
//		this.nombre = nombre;
//		this.puesto = puesto;
//	}
//	
//	public Empleado(String nombre, String puesto, double salario) {
//		this.nombre = nombre;
//		this.puesto = puesto;
//		this.salario = salario;
//	}
//	
//	public Empleado(String nombre, String puesto, Date fechaEntrada) {
//		this.nombre = nombre;
//		this.puesto = puesto;
//		this.fechaEntrada = fechaEntrada;
//	}
	
	public Empleado(String nombre, String puesto, double salario, Date fechaEntrada) {
		this.nombre = nombre;
		this.puesto = puesto;
		this.salario = salario;
		this.fechaEntrada = fechaEntrada;
	}
	
	/*
	public String toJson() {
		return new Gson().toJson(this);
	}
	
	public static Empleado fromJson(String json) {
		return new Gson().fromJson(json, Empleado.class);
	}
	 */
}

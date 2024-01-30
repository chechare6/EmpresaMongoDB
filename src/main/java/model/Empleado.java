package model;

import org.bson.types.ObjectId;

import com.google.gson.Gson;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Empleado {
	private ObjectId _id;
	private String nombre;
	private String puesto;
	
	public Empleado(String nombre, String puesto) {
		this.nombre = nombre;
		this.puesto = puesto;
	}
	
	public String toJson() {
		return new Gson().toJson(this);
	}
	
	public static Empleado fromJson(String json) {
		return new Gson().fromJson(json, Empleado.class);
	}

}

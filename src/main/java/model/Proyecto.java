package model;

import java.sql.Date;

import com.google.gson.Gson;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Proyecto {
	private String _id;
	private String nombre;
	private String descripcion;
	private Date fecha_inicio;
	private Date fecha_fin;
	
	public Proyecto(String nombre, String descripcion, Date fecha_inicio, Date fecha_fin) {
		super();
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.fecha_inicio = fecha_inicio;
		this.fecha_fin = fecha_fin;
	}	
	
	public String toJson() {
		return new Gson().toJson(this);
	}
	
	public static Proyecto fromJson(String json) {
		return new Gson().fromJson(json, Proyecto.class);
	}

}

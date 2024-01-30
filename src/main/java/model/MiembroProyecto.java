package model;

import org.bson.types.ObjectId;

import com.google.gson.Gson;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MiembroProyecto {
	private String _id;
	private ObjectId id_proyecto;
	private ObjectId id_empleado;
	
	public String toJson() {
		return new Gson().toJson(this);
	}
	
	public static MiembroProyecto fromJson(String json) {
		return new Gson().fromJson(json, MiembroProyecto.class);
	}
}

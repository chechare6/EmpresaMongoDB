package view;

import java.sql.Date;
import java.util.List;

import org.bson.types.ObjectId;

import Controller.Controller;
import IO.IO;
import model.Proyecto;

public class MenuProyecto {

	public static void menuProyecto(Controller controller) {
		System.out.println("MENU PROYECTO");
		while (true) {
			List<String> opciones = List.of("PROYECTOS:\n1. VER", "2. BUSCAR", "3. AÑADIR", "4. ELIMINAR",
					"5. MODIFICAR", "6. AÑADIR EMPLEADO A PROYECTO", "7. ELIMINAR EMPLEADO A PROYECTO", "0. VOLVER");
			IO.println(opciones);
			switch (IO.readString().charAt(0)) {
			case '1':
				verProyectos(controller);
				break;
			case '2':
				searchProyecto(controller);
				break;
			case '3':
				String tryAdd = (addProyecto(controller) ? "Se pudo añadir proyecto"
						: "No se pudo añadir el nuevo proyecto");
				IO.println(tryAdd);
				break;
			case '4':
				String tryDelete = (deleteProyecto(controller) ? "Se eliminó correctamente el proyecto"
						: "No se pudo eliminar el proyecto");
				IO.println(tryDelete);
				break;
			case '5':
				updateProyecto(controller);
				break;
			case '0':
				Start.main(null);
				break;
			}
		}
	}

	private static void verProyectos(Controller controller) {
		controller.getProyectos();	
	}

	private static Boolean addProyecto(Controller controller) {
		IO.print("Nombre del nuevo proyecto: ");
		String nombre = IO.readString();
		IO.print("Descipcion del mismo: ");
		String descripcion = IO.readString();
		IO.print("Fecha de inicio: (yyyy-MM-dd)");
		Date fechaInicio = Date.valueOf(IO.readLocalDate());
		IO.print("Fecha de fin: (yyyy-MM-dd)");
		Date fechaFin = Date.valueOf(IO.readLocalDate());
		return controller.addProyecto(new Proyecto(nombre, descripcion, fechaInicio, fechaFin));
	}

	private static Boolean deleteProyecto(Controller controller) {
		IO.print("Introduce el ID del proyecto a borrar: ");
		ObjectId id = new ObjectId(IO.readString());
		return controller.deleteProyecto(id);
	}

	private static Boolean updateProyecto(Controller controller) {
		IO.print("Introduce el ID del proyecto a modificar: ");
		ObjectId id = new ObjectId(IO.readString());
		return controller.updateProyecto(id);
	}

	private static void searchProyecto(Controller controller) {
		IO.print("ID del proyecto que buscas: ");
		ObjectId id = new ObjectId(IO.readString());
		IO.println(controller.searchProyecto(id));
	}
}

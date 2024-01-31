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
			List<String> opciones = List.of("EMPLEADOS:\n1. VER", "2. BUSCAR", "3. AÑADIR", "4. ELIMINAR",
					"5. MODIFICAR", "6. AÑADIR PROYECTO A EMPLEADO", "7. ELIMINAR PROYECTO DE EMPLEADO", "0. VOLVER");
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

	private static boolean addProyecto(Controller controller) {
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

	private static boolean deleteProyecto(Controller controller) {
		IO.print("Introduce el nombre del proyecto a borrar: ");
		String nombre = IO.readString();
		return controller.deleteProyecto(nombre);
	}

	private static void updateProyecto(Controller controller) {
		// TODO Auto-generated method stub

	}

	private static void searchProyecto(Controller controller) {
		IO.print("ID del proyecto que buscas: ");
		ObjectId id = new ObjectId(IO.readString());
		controller.searchProyecto(id);
	}
}

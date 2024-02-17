package view;

import Controller.Controller;
import IO.IO;
import model.Tarea;
import org.bson.types.ObjectId;

import java.sql.Date;

public class MenuTarea {
	public static void menuTarea(Controller controller) {
		while (true) {
			IO.println("╔══════════════════════════════════╗");
			IO.println("║          MENÚ TAREAS             ║");
			IO.println("╠══════════════════════════════════╣");
			IO.println("║ 1. VER TAREAS                    ║");
			IO.println("║ 2. BUSCAR POR ID                 ║");
			IO.println("║ 3. AÑADIR TAREA                  ║");
			IO.println("║ 4. ELIMINAR TAREA                ║");
			IO.println("║ 5. MODIFICAR TAREA               ║");
			IO.println("║ 6. BUSCAR TAREA POR ESTADO       ║");
			IO.println("║ 7. CAMBIAR ESTADO DE LA TAREA    ║");
			IO.println("║ 0. VOLVER                        ║");
			IO.println("╚══════════════════════════════════╝");
			IO.print("OPCIÓN: ");
			switch (IO.readString().charAt(0)) {
			case '1':
				verTareas(controller);
				break;
			case '2':
				searchTarea(controller);
				break;
			case '3':
				String tryAdd = (addTarea(controller) ? "Tarea añadida" : "No se ha podido añadir una Tarea");
				IO.println(tryAdd);
				break;
			case '4':
				String tryDelete = (deleteTarea(controller) ? "Tarea eliminada correctamente"
						: "No se ha podido eliminar");
				IO.println(tryDelete);
				break;
			case '5':
				String tryUpdate = (updateTarea(controller) ? "Actualizado con exito"
						: "No se ha podido realizar la operacion");
				IO.println(tryUpdate);
				break;
			case '6':
				searchTareaByState(controller);
				break;
			case '7':
				updateTareaByState(controller);
				break;
			case '0':
				Start.main(null);
				break;
			}
		}
	}

	private static void searchTarea(Controller controller) {
		IO.print("ID de la tarea que buscas: ");
		ObjectId id = new ObjectId(IO.readString());
		IO.println(controller.searchTarea(id));

	}

	private static void verTareas(Controller controller) {
		controller.getTareas();
	}

	private static void searchTareaByState(Controller controller) {
		IO.print("Introduce el estado de la tarea: ");
		String state = IO.readString();
		controller.searchTareaByState(state);
	}

	private static Boolean addTarea(Controller controller) {
		IO.print("Introduce el nombre de la tarea: ");
		String nombre = IO.readString();
		IO.print("Descripcion de la tarea: ");
		String descripcion = IO.readString();
		IO.print("Fecha de vencimiento de la tarea: (yyyy-MM-dd): ");
		Date fechaFin = Date.valueOf(IO.readLocalDate());
		ObjectId idProyecto = null;
		Boolean condicion = false;
		while (!condicion) {
			IO.print("¿Está asignado a algún proyecto? (S/N): ");
			switch (IO.readString().toUpperCase()) {
			case "S":
				IO.println("------------");
				controller.getProyectos();
				IO.println("------------");
				IO.print("Introduzca el ID del proyecto: ");
				idProyecto = new ObjectId(IO.readString());
				condicion = true;
				break;
			case "N":
				idProyecto = null;
				condicion = true;
				break;
			default:
				IO.println("Esa respuesta no es válida");
			}
		}
		return controller.addTarea(new Tarea(nombre, descripcion, fechaFin, idProyecto));
	}

	private static Boolean updateTarea(Controller controller) {
		IO.print("Introduce el ID de la tarea a modificar: ");
		ObjectId id = new ObjectId(IO.readString());
		if (!controller.searchTarea(id).isEmpty())
			IO.print("Introduce el nuevo nombre da la tarea: ");
		String nombre = IO.readString();
		IO.print("Introduce la nueva descripcion de la tarea: ");
		String descripcion = IO.readString();
		IO.print("Introduce el estado de la tarea: ");
		String estado = IO.readString();
		IO.print("Introduce la nueva Fecha de vencimiento de la tarea: (yyyy-MM-dd): ");
		Date fechaFin = Date.valueOf(IO.readLocalDate());
		ObjectId idProyecto = null;
		Boolean condicion = false;
		while (!condicion) {
			IO.print("¿Modificar el proyecto al que está asociada la tarea? (S/N): ");
			switch (IO.readString().toUpperCase()) {
			case "S":
				IO.println("------------");
				controller.getProyectos();
				IO.println("------------");
				IO.print("Introduce el id del proyecto deseado: ");
				idProyecto = new ObjectId(IO.readString());
				condicion = true;
				break;
			case "N":
				idProyecto = null;
				condicion = true;
				break;
			default:
				IO.println("Esa respuesta no es válida.");
				break;
			}
		}
		return controller.updateTarea(new Tarea(id, nombre, descripcion, estado, fechaFin, idProyecto));
	}

	private static Boolean updateTareaByState(Controller controller) {
		IO.print("Introduce el ID de la tarea a modificar: ");
		ObjectId id = new ObjectId(IO.readString());
		if (!controller.searchTarea(id).isEmpty()) {
			IO.print("Introduce el nuevo estado de la tarea: ");
			String estado = IO.readString();
			return controller.updateTareaByState(new Tarea(id, estado));
		}
		return false;
	}

	private static Boolean deleteTarea(Controller controller) {
		IO.print("Introduce el nombre de la tarea a borrar: ");
		ObjectId id = new ObjectId(IO.readString());
		return controller.deleteTarea(id);
	}

}
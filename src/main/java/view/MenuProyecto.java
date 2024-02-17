package view;

import java.sql.Date;
import org.bson.types.ObjectId;

import Controller.Controller;
import IO.IO;
import model.Proyecto;

public class MenuProyecto {

	public static void menuProyecto(Controller controller) {
		System.out.println("MENU PROYECTO");
		while (true) {
			IO.println("╔══════════════════════════════════╗");
			IO.println("║          MENÚ PROYECTOS          ║");
			IO.println("╠══════════════════════════════════╣");
			IO.println("║ 1. VER PROYECTOS                 ║");
			IO.println("║ 2. BUSCAR PROYECTO POR ID        ║");
			IO.println("║ 3. AÑADIR PROYECTO               ║");
			IO.println("║ 4. ELIMINAR PROYECTO             ║");
			IO.println("║ 5. MODIFICAR PROYECTO            ║");
			IO.println("║ 6. AÑADIR EMPLEADO A PROYECTO    ║");
			IO.println("║ 7. ELIMINAR EMPLEADO DE PROYECTO ║");
			IO.println("║ 0. VOLVER                        ║");
			IO.println("╚══════════════════════════════════╝");
			IO.print("OPCIÓN: ");
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
			case '6':
				addEmpleado(controller);
				break;
			case '7':
				deleteEmpleado(controller);
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
		Boolean condicion = false;
		ObjectId idEmpleado = null;
		while (!condicion) {
			IO.print("¿Asignar un empleado? (S/N): ");
			switch (IO.readString().toUpperCase()) {
			case "S":
				IO.println("------------");
				controller.getEmpleados();
				IO.println("------------");
				IO.print("Introduce el id del empleado deseado: ");
				idEmpleado = new ObjectId(IO.readString());
				condicion = true;
				break;
			case "N":
				idEmpleado = null;
				condicion = true;
				break;
			default:
				IO.println("Esa respuesta no es válida.");
				break;
			}
		}
		return controller.addProyecto(new Proyecto(nombre, descripcion, fechaInicio, fechaFin, idEmpleado));
	}

	private static Boolean deleteProyecto(Controller controller) {
		IO.print("Introduce el ID del proyecto a borrar: ");
		ObjectId id = new ObjectId(IO.readString());
		return controller.deleteProyecto(id);
	}

	private static Boolean updateProyecto(Controller controller) {
		IO.print("Introduce el ID del proyecto a modificar: ");
		ObjectId id = new ObjectId(IO.readString());
		if (!controller.searchProyecto(id).isEmpty()) {
			IO.print("Intoduce el nuevo nombre del proyecto: ");
			String nombre = IO.readString();
			IO.print("Intoduce la nueva descripcion del proyecto: ");
			String descripcion = IO.readString();
			IO.print("Introduce la nueva fecha de inicio: (yyyy-MM-dd) ");
			Date fecha_inicio = Date.valueOf(IO.readLocalDate());
			IO.print("Introduce la nueva fecha de fin: (yyyy-MM-dd) ");
			Date fecha_fin = Date.valueOf(IO.readLocalDate());
			ObjectId idEmpleado = null;
			Boolean condicion = false;
			while (!condicion) {
				IO.print("¿Modificar empleado asignado al proyecto? (S/N): ");
				switch (IO.readString().toUpperCase()) {
				case "S":
					IO.println("------------");
					controller.getEmpleados();
					IO.println("------------");
					IO.print("Introduce el id del empleado deseado: ");
					idEmpleado = new ObjectId(IO.readString());
					condicion = true;
					break;
				case "N":
					idEmpleado = null;
					condicion = true;
					break;
				default:
					IO.println("Esa respuesta no es válida.");
				}
			}
			return controller
					.updateProyecto(new Proyecto(id, nombre, descripcion, fecha_inicio, fecha_fin, idEmpleado));
		} else {
			IO.println("No existe un proyecto con esa ID.");
			return false;
		}
	}

	private static void searchProyecto(Controller controller) {
		IO.print("ID del proyecto que buscas: ");
		ObjectId id = new ObjectId(IO.readString());
		IO.println(controller.searchProyecto(id));
	}

	private static Boolean addEmpleado(Controller controller) {
		IO.print("Introduce el ID del proyecto al que lo quieres agregar: ");
		ObjectId idProyecto = new ObjectId(IO.readString());
		IO.print("Introduce el ID del empleado que quieres agregar: ");
		ObjectId idEmpleado = new ObjectId(IO.readString());
		if (controller.searchEmpleado(idEmpleado) != null && controller.searchProyecto(idProyecto) != null) {
			return controller.addEmpleadoToProyecto(idEmpleado, idProyecto);
		} else if (controller.searchProyecto(idProyecto) == null) {
			IO.println("No se ha encontrado el proyecto con id: " + idProyecto);
			return false;
		} else {
			IO.println("No se ha encontrado al empleado con id: " + idEmpleado);
			return false;
		}
	}

	private static Boolean deleteEmpleado(Controller controller) {
		IO.print("Introduce el ID del proyecto del que eliminaremos empleado: ");
		ObjectId id = new ObjectId(IO.readString());
		controller.deleteEmpleadoFromProyecto(id);
		return null;
	}
}

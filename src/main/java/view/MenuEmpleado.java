package view;

import java.sql.Date;
import org.bson.types.ObjectId;

import Controller.Controller;
import IO.IO;
import model.Empleado;

public class MenuEmpleado {

	public static void menuEmpleado(Controller controller) {
		while (true) {
			IO.println("╔══════════════════════════════════╗");
			IO.println("║          MENÚ EMPLEADOS          ║");
			IO.println("╠══════════════════════════════════╣");
			IO.println("║ 1. VER EMPLEADOS                 ║");
			IO.println("║ 2. BUSCAR POR ID                 ║");
			IO.println("║ 3. AÑADIR EMPLEADO               ║");
			IO.println("║ 4. ELIMINAR EMPLEADO             ║");
			IO.println("║ 5. MODIFICAR EMPLEADO            ║");
			IO.println("║ 0. VOLVER                        ║");
			IO.println("╚══════════════════════════════════╝");
			IO.print("OPCIÓN: ");
			switch (IO.readString().charAt(0)) {
			case '1':
				verEmpleados(controller);
				break;
			case '2':
				searchEmpleado(controller);
				break;
			case '3':
				String tryAdd = (addEmpleado(controller) ? "Se pudo añadir empleado"
						: "No se pudo añadir el nuevo empleado");
				IO.println(tryAdd);
				break;
			case '4':
				String tryDelete = (deleteEmpleado(controller) ? "Se eliminó correctamente el empleado"
						: "No se pudo eliminar el empleado");
				IO.println(tryDelete);
				break;
			case '5':
				String tryUpdate = (updateEmpleado(controller) ? "Se actualizó correctamente el empleado"
						: "No se pudo actualizar el empleado");
				IO.println(tryUpdate);
				break;
			case '0':
				Start.main(null);
				break;
			}
		}
	}

	private static void verEmpleados(Controller controller) {
		controller.getEmpleados();
	}

	private static void searchEmpleado(Controller controller) {
		IO.print("Introduce la ID del empleado: ");
		ObjectId id = new ObjectId(IO.readString());
		IO.println(controller.searchEmpleado(id));
	}

	private static boolean addEmpleado(Controller controller) {
		IO.print("Nombre del nuevo empleado: ");
		String nombre = IO.readString();
		IO.print("Puesto que ocupa el empleado: ");
		String puesto = IO.readString();
		Double salario = null;
		Boolean condicion = false;
		while (!condicion) {
			IO.print("¿Añadir salario del empleado? (S/N): ");
			switch (IO.readString().toUpperCase()) {
			case "S":
				IO.print("Introduce el salario del empleado: ");
				salario = IO.readDouble();
				condicion = true;
				break;
			case "N":
				salario = null;
				condicion = true;
				break;
			default:
				IO.println("Esa respuesta no es válida.");
				break;
			}
		}
		condicion = false;
		Date fechaEntrada = null;
		while (!condicion) {
			IO.print("¿Añadir fecha de entrada del empleado? (S/N): ");
			switch (IO.readString().toUpperCase()) {
			case "S":
				IO.print("Introduce la fecha de entrada del empleado: (yyyy-MM-dd) ");
				fechaEntrada = Date.valueOf(IO.readLocalDate());
				condicion = true;
				break;
			case "N":
				fechaEntrada = null;
				condicion = true;
				break;
			default:
				IO.println("Esa respuesta no es válida.");
				break;
			}
		}
		return controller.addEmpleado(new Empleado(nombre, puesto, salario, fechaEntrada));
	}

	private static Boolean deleteEmpleado(Controller controller) {
		IO.print("Introduce el ID del empleado a borrar: ");
		ObjectId id = new ObjectId(IO.readString());
		return controller.deleteEmpleado(id);
	}

	private static Boolean updateEmpleado(Controller controller) {
		IO.print("Introduce el ID del empleado a modificar: ");
		ObjectId id = new ObjectId(IO.readString());
		if (!controller.searchEmpleado(id).isEmpty()) {
			IO.print("Introduce el nuevo nombre del empleado: ");
			String nombre = IO.readString();
			IO.print("Introduce el nuevo puesto del empleado: ");
			String puesto = IO.readString();
			Double salario = null;
			Boolean condicion = false;
			while (!condicion) {
				IO.print("¿Cambiar salario del empleado? (S/N): ");
				switch (IO.readString().toUpperCase()) {
				case "S":
					IO.print("Introduce el salario del emlpeado: ");
					salario = IO.readDouble();
					condicion = true;
					break;
				case "N":
					salario = null;
					condicion = true;
					break;
				default:
					IO.println("Esa respuesta no es válida.");
					break;
				}
			}
			condicion = false;
			Date fechaEntrada = null;
			while (!condicion) {
				IO.print("¿Cambiar la fecha de entrada del empleado? (S/N): ");
				switch (IO.readString().toUpperCase()) {
				case "S":
					IO.print("Introduce la nueva fecha de entrada: (yyyy-MM-dd) ");
					fechaEntrada = Date.valueOf(IO.readLocalDate());
					condicion = true;
					break;
				case "N":
					fechaEntrada = null;
					condicion = true;
					break;
				default:
					IO.println("Esa respuesta no es válida.");
					break;
				}
			}
			return controller.updateEmpleado(new Empleado(id, nombre, puesto, salario, fechaEntrada));
		}
		return false;
	}
}
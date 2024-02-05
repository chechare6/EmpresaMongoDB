package view;

import java.util.List;

import org.bson.types.ObjectId;

import Controller.Controller;
import IO.IO;
import model.Empleado;

public class MenuEmpleado {
	
	public static void menuEmpleado(Controller controller) {
		while (true) {
			List<String> opciones = List.of("EMPLEADOS:\n1. VER", "2. BUSCAR", "3. AÑADIR", "4. ELIMINAR",
					"5. MODIFICAR", "0. VOLVER");
			IO.println(opciones);
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
		return controller.addEmpleado(new Empleado(nombre, puesto));
	}

	private static Boolean deleteEmpleado(Controller controller) {
		IO.print("Introduce el ID del empleado a borrar: ");
		ObjectId id = new ObjectId(IO.readString());
		return controller.deleteEmpleado(id);
	}
	
	private static Boolean updateEmpleado(Controller controller) {
		IO.print("Introduce el ID del empleado a modificar: ");
		ObjectId id = new ObjectId(IO.readString());
		return controller.updateEmpleado(id);
	}

}
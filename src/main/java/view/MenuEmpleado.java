package view;

import java.sql.Date;
import java.util.List;

import org.bson.Document;
import org.bson.types.ObjectId;

import Controller.Controller;
import IO.IO;
import model.Empleado;

public class MenuEmpleado {
	
	public static void menuEmpleado(Controller controller) {
		while (true) {
			List<String> opciones = List.of("EMPLEADOS:\n1. VER", "2. BUSCAR", "3. AÑADIR", "4. ELIMINAR",
					"5. MODIFICAR", "6. AÑADIR PROYECTO A EMPLEADO", "7. ELIMINAR PROYECTO DE EMPLEADO", "0. VOLVER");
			IO.println(opciones);
			switch (IO.readString().charAt(0)) {
			case '1':
				verEmpleados(controller);
				break;
			case '2':
				IO.println(searchEmpleado(controller));
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
			case '6':
				addProyect(controller);
				break;
			case '7':
				deleteProyect(controller);
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
	
	private static Document searchEmpleado(Controller controller) {
		IO.print("Introduce la ID del empleado: ");
		ObjectId id = new ObjectId(IO.readString());
		return controller.searchEmpleado(id);
	}

	private static boolean addEmpleado(Controller controller) {
		IO.print("Nombre del nuevo empleado: ");
		String nombre = IO.readString();
		IO.print("Puesto que ocupa el empleado: ");
		String puesto = IO.readString();
		IO.print("¿Tiene salario?: ");
		double salario = (Double) null;
		Date fechaEntrada = null;
		String sal = IO.readString();
		if(sal.equals("Y")) {
			IO.print("Introduce salario: ");
			salario = IO.readDouble();
		}			
			
		return controller.addEmpleado(new Empleado(nombre, puesto, salario, fechaEntrada));
	}

	private static boolean deleteEmpleado(Controller controller) {
		IO.print("Introduce el nombre del empleado a borrar: ");
		String nombre = IO.readString();
		return controller.deleteEmpleado(nombre);
	}
	
	private static Boolean updateEmpleado(Controller controller) {
		IO.print("Introduce el ID del empleado a modificar: ");
		ObjectId id = new ObjectId(IO.readString());
		return controller.updateEmpleado(id);
	}

	private static boolean deleteProyect(Controller controller) {
		// TODO Auto-generated method stub
		return false;
	}

	private static void addProyect(Controller controller) {
		// TODO Auto-generated method stub

	}
}
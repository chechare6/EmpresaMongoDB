package view;

import Controller.Controller;
import IO.IO;
import model.Tarea;
import org.bson.types.ObjectId;

import java.util.List;
import java.sql.Date;


public class MenuTarea {
	public static void menuTarea(Controller controller) {
		while (true){
			List<String> opciones = List.of(
					"TAREAS:\n1. VER TAREAS",
					"2. BUSCAR TAREA POR ESTADO",
					"3. AÑADIR TAREA",
					"4. MODIFICAR TAREA",
					"5. CAMBIAR ESTADO DE LA TAREA",
					"6. ELIMINAR TAREA",
					"0. VOLVER"
			);

			IO.println(opciones);
			switch (IO.readString().charAt(0)){
				case '1':
					verTareas(controller);
					break;
				case'2':
					searchTareaByState(controller);
					break;
				case '3':
					String tryAdd = (addTarea(controller) ? "Tarea añadida" : "No se ha podido añadir una Tarea");
					IO.println(tryAdd);
					break;
				case '4':
					String tryUpdate = (updateTarea(controller) ? "Actualizado con exito" : "No se ha podido realizar la operacion");
					IO.println(tryUpdate);
					break;
				case '5':
					//updateState(controller);
				case '6':
					String tryDelete = (deleteTarea(controller) ? "Tarea eliminada correctamente" : "No se ha podido eliminar");
					IO.println(tryDelete);
					break;
				case '0':
					Start.main(null);
					break;
			}
		}
	}

	private static void verTareas(Controller controller){
		controller.getTareas();
	}

	private static void searchTareaByState(Controller controller){
		IO.print("Introduce el estado de la tarea: ");
		String state = IO.readString();
		controller.searchTareaByState(state);
		//TODO: Tocar el CRUD repository para encontrarlo por estado
	}

	private static Boolean addTarea(Controller controller){
		IO.print("Introduce el nombre de la tarea: ");
		String nombre = IO.readString();
		IO.print("Descripcion de la tarea: ");
		String descripcion = IO.readString();
		IO.print("Fecha de vencimiento de la tarea: (yyyy-MM-dd): ");
		Date fechaFin = Date.valueOf(IO.readLocalDate());
		/*
		 * Se puede simplificar
		 */
		IO.print("Sabes el ID del proyecto? (s/n)");
		String proyectoID="";
		switch (IO.readString().charAt(0)){
			case 's':
				IO.print("Introduzca el ID del proyecto");
				proyectoID = IO.readString();
				break;
			case 'n':
				controller.getProyectos();
				System.out.println("-----------");
				IO.print("Introduzca el ID del proyecto");
				proyectoID = IO.readString();
				break;
			default:
				System.out.println("RESPUESTA NO VÁLIDA");
				break;

		}
		return controller.addTarea(new Tarea(nombre, descripcion, fechaFin, proyectoID));
	}

	private static Boolean updateTarea(Controller controller){
		IO.print("Introduce el ID de la tarea a modificar: ");
		ObjectId id = new ObjectId(IO.readString());
		return controller.updateTarea(id);
	}

	private static Boolean deleteTarea(Controller controller){
		IO.print("Introduce el nombre de la tarea a borrar: ");
		ObjectId id = new ObjectId(IO.readString());
		return controller.deleteTarea(id);
	}


}
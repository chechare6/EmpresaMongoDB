package view;

import Controller.Controller;
import IO.IO;

import java.util.List;

public class MenuTarea {
	public static void menuTarea(Controller controller) {
		while (true){
			List<String> opciones = List.of(
					"TAREAS:\n1. VER TAREAS",
					"2. BUSCAR TAREA POR ESTADO",
					"3. AÑADIR TAREA",
					"4. CAMBIAR ESTADO",
					"5. ELIMINAR TAREA",
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
					updateTarea(controller);
					break;
				case '5':
					String tryDelete = ( deleteTarea(controller) ? "Tarea eliminada correctamente" : "No se ha podido eliminar");
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
		//TODO
	}

	private static boolean addTarea(Controller controller){
		//TODO
		return false;
	}

	private static void updateTarea(Controller controller){
		//TODO
	}

	private static boolean deleteTarea(Controller controller){
		//TODO
		return false;
	}


}
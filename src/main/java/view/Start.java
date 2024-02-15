package view;

import Controller.Controller;
import IO.IO;
import conexionDB.MongoDB;
import repositories.empleados.EmpleadosRepositoryImpl;
import repositories.proyecto.ProyectoRepositoryImpl;
import repositories.tareas.TareasRepositoryImpl;

public class Start {
	public static void main(String[] args) {
		MongoDB.initDatabase();;
		Controller controller = new Controller(
				new EmpleadosRepositoryImpl(),
				new ProyectoRepositoryImpl(),
				new TareasRepositoryImpl()
				);
		while(true) {
			IO.println("╔══════════════════════════════════╗");
	        IO.println("║     BBDD EMPRESA ~ MONGO DB      ║");
	        IO.println("╠══════════════════════════════════╣");
	        IO.println("║ 1. EMPLEADOS                     ║");
	        IO.println("║ 2. PROYECTOS                     ║");
	        IO.println("║ 3. TAREAS                        ║");
	        IO.println("║ 0. SALIR                         ║");
	        IO.println("╚══════════════════════════════════╝");
	        IO.print("OPCIÓN: ");
			switch (IO.readString().charAt(0)) {
			case '1':
				MenuEmpleado.menuEmpleado(controller);
				break;
			case '2':
				MenuProyecto.menuProyecto(controller);
				break;
			case '3':
				MenuTarea.menuTarea(controller);
				break;
			case '0':
				IO.println("CERRANDO BBDD");
				System.exit(0);
				break;
			}
		}
	}
}

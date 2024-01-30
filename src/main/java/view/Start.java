package view;

import java.util.List;
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
		List<String> opciones;
		while(true) {
			opciones = List.of("BBDD Empresa ~ MongoDB:\n1. DEPARTAMENTOS", "2. EMPLEADOS", "3. PROYECTOS","4. MIEMBROS - PROYECTOS", "0. CERRAR");
			IO.println(opciones);
			int seleccionado = IO.readString().charAt(0);
			switch (seleccionado) {
			case '1':
				MenuTarea.menuTarea(controller);
				break;
			case '2':
				MenuEmpleado.menuEmpleado(controller);
				break;
			case '3':
				MenuProyecto.menuProyecto(controller);
				break;
			case '4':
				MenuMiembrosProyectos.menuMiembrosProyectos(controller);
				break;
			case '0':
				IO.println("CERRANDO BBDD");
				System.exit(0);
				break;
			}
		}
	}
}

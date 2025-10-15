package run;

import config.JPAUtil;
import jakarta.persistence.EntityManager;
import repository.dao.CargoDao;
import repository.dao.EmpleadoDao;
import utils.Menu;
import utils.menuUtils;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        EntityManager em = JPAUtil.getEntityManager();
        Scanner sc = new Scanner(System.in);

        CargoDao cargoDao = new CargoDao(em);
        EmpleadoDao empleadoDao = new EmpleadoDao(em);

        App(sc, cargoDao, empleadoDao);
    }

    public static void App(Scanner sc, CargoDao cargoDao, EmpleadoDao empleadoDao) {
        Menu menuPrincipal = new Menu("Menú Principal");
        Menu menuEmpleados = new Menu("Menú Empleados");
        Menu menuCargos = new Menu("Menú Cargos");

        menuCargos.agregarItem("Agregar Cargo", () -> menuUtils.agregarCargoMenu(sc, cargoDao));
        menuCargos.agregarItem("Buscar Cargo", () -> menuUtils.buscarCargoMenu(sc, cargoDao));
        menuCargos.agregarItem("Actualizar Cargo", () -> menuUtils.actualizarCargoMenu(sc, cargoDao));
        menuCargos.agregarItem("Eliminar Cargo", () -> menuUtils.eliminarCargoMenu(sc, cargoDao));
        menuCargos.agregarItem("Listar Cargos", () -> menuUtils.listarCargosMenu(sc, cargoDao));

        menuEmpleados.agregarItem("Agregar Empleado", () -> menuUtils.agregarEmpleadoMenu(sc, empleadoDao, cargoDao));
        menuEmpleados.agregarItem("Buscar Empleado", () -> menuUtils.buscarEmpleadoMenu(sc, empleadoDao));
        menuEmpleados.agregarItem("Actualizar Empleado", () -> menuUtils.actualizarEmpleadoMenu(sc, empleadoDao, cargoDao));
        menuEmpleados.agregarItem("Eliminar Empleado", () -> menuUtils.eliminarEmpleadoMenu(sc, empleadoDao));
        menuEmpleados.agregarItem("Listar Empleados", () -> menuUtils.listarEmpleados(sc, empleadoDao));

        menuPrincipal.agregarItem("Menú Empleados", menuEmpleados::mostrar);
        menuPrincipal.agregarItem("Menú Cargos", menuCargos::mostrar);


        boolean continuar = true;
        while (continuar) {
            menuPrincipal.mostrar();
            System.out.print("¿Desea continuar? (s/n): ");
            String resp = sc.nextLine();
            if (resp.equalsIgnoreCase("n")) {
                continuar = false;
                System.out.println("Saliendo del sistema...");
            }
        }
    }
}

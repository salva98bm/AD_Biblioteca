package biblioteca;

import com.matisse.MtDatabase;
import com.matisse.MtException;

public class Principal {

	public static void main(String[] args) {
		
		String hostname = "localhost";
		String dbname = "biblioteca";
		creaObjetos(hostname, dbname);
		
	}

	public static void creaObjetos(String hostname, String dbname) {
		try {
			// Abre la base de datos con el hostname (localhost), y el nombre de la base de datos dbname (biblioteca).
			MtDatabase db = new MtDatabase(hostname, dbname);
			db.open();
			db.startTransaction();
			System.out.println("Conectado a la base de datos " +
					db.getName() + " de Matisse");
			
			// Crea un objeto Autor
			Autor a1 = new Autor(db);
			a1.setNombre("Haruki");
			a1.setApellidos("Murakami");
			a1.setEdad(53);
			System.out.println("Objeto de tipo Autor creado.");
			
			// Crea un objeto Libro
			Libro l1 = new Libro(db);
			l1.setTitulo("Baila Baila Baila");
			l1.setEditorial("TusQuests");
			l1.setPaginas(512);
			
			// Crea otro objeto libro
			Libro l2 = new Libro(db);
			l2.setTitulo("Tokio Blues");
			l2.setEditorial("TusQuests");
			l2.setPaginas(498);
			System.out.println("Objetos de tipo Libro creados.");
			
			// Crea un array de Obras para guardar los libros y hacer las relaciones
			Obra o1[] = new Obra[2];
			o1[0] = l1;
			o1[1] = l2;
			
			// Guarda las relaciones del autor con los libros que ha escrito.
			a1.setEscribe(o1);
			
			// Ejecuta un commit para materializar las peticiones.
			db.commit();
			
			// Cierra la base de datos.
			db.close();
			
			System.out.println("\nHecho.");
			
		} catch (MtException mte) {
			System.out.println("MtException : " + mte.getMessage());
		}
	}
}

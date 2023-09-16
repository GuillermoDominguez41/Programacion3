package clustering_Humano;

public class Modelo {

	public Modelo() {
	}
	
	
	public static void main(String[] args) {
		Grafo g= new Grafo(9);
		
		g.agregarArista(1, 2);
		g.agregarArista(8, 4);
		g.agregarArista(2, 1);
		g.agregarArista(5, 1);
		g.agregarArista(6, 2);
		
		g.agregarArista(7, 2);
		g.agregarArista(5, 4);
		
		System.out.println(g.existeArista(2, 0));
		System.out.println(g.existeArista(6, 2));
		System.out.println(g.existeArista(1, 5));
		

	}
}

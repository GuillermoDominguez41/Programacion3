package clustering_Humano;

public class Persona {
	String Nombre;
	Integer InteresDeporte;
	Integer InteresMusica;
	Integer InteresEspectaculo;
	Integer InteresCiencia;
	boolean interesValido;

	public Persona(String Nombre, Integer ID, Integer IM, Integer IE, Integer IC) {
		this.Nombre = Nombre;
		if (interesValido(ID, IM, IE, IC)) {
			this.InteresMusica = IM;
			this.InteresDeporte = ID;
			this.InteresEspectaculo = IE;
			this.InteresCiencia = IC;
		} else
			throw new RuntimeException("Algun interes se encuentra fuera del rango definido");

	}

	// ------------ Validar intereses -------------------

	private boolean interesValido(Integer ID, Integer IM, Integer IE, Integer IC) {
		return rangoInteresValido(ID) && rangoInteresValido(IM) && rangoInteresValido(IE) && rangoInteresValido(IC);

	}

	private boolean rangoInteresValido(Integer interes) {
		
		System.out.println(interes >= 1 && interes <= 5);
		return interes >= 1 && interes <= 5;
	}

	// ---------- Getters y Setters ---------
	public String getNombre() {
		return Nombre;
	}

	public Integer getInteresDeporte() {
		return InteresDeporte;
	}

	public Integer getInteresMusica() {
		return InteresMusica;
	}

	public Integer getInteresEspectaculo() {
		return InteresEspectaculo;
	}

	public Integer getInteresCiencia() {
		return InteresCiencia;
	}
}

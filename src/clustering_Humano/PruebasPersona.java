package clustering_Humano;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class PruebasPersona {

	@Test
	void interesDentroRango() {
		Persona p = new Persona("Roberto", 2, 2, 3, 1);
		assertEquals(p.getInteresDeporte(), 2);
	}

	@Test
	void rangoInteresPorEncima() {
		try {
			Persona p = new Persona("Roberto", 6, 2, 3, 1);
			p.getInteresCiencia();
		} catch (RuntimeException e) {

			assertTrue(true);
		} catch (Exception e) {
			fail("Numero fuera de rango");
		}

	}

	@Test
	void rangoInteresPorDebajo() {
		try {
			Persona p = new Persona("Roberto", 4, 2, 0, 1);
			p.getInteresCiencia();
		} catch (RuntimeException e) {

			assertTrue(true);
		} catch (Exception e) {
			fail("Numero fuera de rango");
		}

	}
}

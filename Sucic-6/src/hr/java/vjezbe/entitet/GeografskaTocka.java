package hr.java.vjezbe.entitet;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * predstavlja entitet grafičke točke s koordinatima x i y
 */
public class GeografskaTocka extends BazniEntitet implements Serializable {
	private BigDecimal x;
	
	private BigDecimal y;

	/**
	 * prima parametre koordinata x i y
	 * @param x je koordinata x točke
	 * @param y je koordinata y točke
	 */
	public GeografskaTocka(BigDecimal x, BigDecimal y) {
		super();
		this.x = x;
		this.y = y;
	}

	public GeografskaTocka(BigDecimal x, BigDecimal y, int id) {
		super(id);
		this.x = x;
		this.y = y;
	}
	
	public BigDecimal getX() {
		return x;
	}
	public void setX(BigDecimal x) {
		this.x = x;
	}
	public BigDecimal getY() {
		return y;
	}

	public void setY(BigDecimal y) {
		this.y = y;
	}
}

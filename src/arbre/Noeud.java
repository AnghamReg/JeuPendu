package arbre;

public class Noeud {
private String caractere;
private Noeud fg,fd;

//constructeur
 Noeud() {}

public Noeud(String c) {
	this.caractere=c;
	this.fg=new Noeud();
	this.fd=new Noeud();
}

//getters
public String getCaractere() {
	return this.caractere;
}

public Noeud getFG() {
	return this.fg;
}

public Noeud getFD() {
	return this.fd;
}

//setters
public void setCaractere(String c) {
	this.caractere=c;
}

public void setFG(Noeud fg) {
	this.fg=fg;
}

public void setFD(Noeud fd) {
	this.fd=fd;
}

//méthodes 

public boolean estFeuille() {
	return this.fd==null && this.fg==null;
}

}

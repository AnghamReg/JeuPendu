package arbre;

public class Arbre {
	private Noeud racine;
	
	//constructeur
	public Arbre(String c) {
		this.racine=new Noeud(c);
	}
	
	//getters
	public Noeud getRacine() {
		return this.racine;
	}
	
	//setters
	public void setRacine(String c) {
		this.racine.setCaractere(c);
	}
	
	//méthodes
	
	//arbre est vide
	public boolean estVide() {
		return this.racine.getCaractere().equals("$");
	}
	
	
	//dessiner arbre
	public void dessinerArbre() {
		System.out.println("_________ Votre arbre ___________ \n\n");
        dessinerAvecContraintes(this.racine, 0);
    }
	
	private void dessinerAvecContraintes(Noeud node, int niveau) {
        if (node == null) {
            return;
        }
        if (node.estFeuille()) {
            for (int i = 0; i < niveau; i++) {
                System.out.print("   "); 
            }
            System.out.println("|_" + node.getCaractere());
            return;
        }

        if (node.getFD() != null) {
            dessinerAvecContraintes(node.getFD(), niveau + 1);
        }
        for (int i = 0; i < niveau; i++) {
            System.out.print("   "); 
        }
        System.out.println("|_" + node.getCaractere()); 

        if (node.getFG() != null) {
            dessinerAvecContraintes(node.getFG(), niveau + 1);
        }
    }

	//taille
	public int tailleArbre() {
		return tailleSousArbre(this.racine);
	}
	
	 private int tailleSousArbre(Noeud node) {
	        if (node == null) {
	            return 0;
	        } else {
	            return 1 + tailleSousArbre(node.getFG()) + tailleSousArbre(node.getFD());
	        }
	    }
	 
	 //hauteur
	 public int hauteurArbre() {
		 return hauteurSousArbre(this.racine);
	 }
	 
	 private int hauteurSousArbre(Noeud node) {
		 if(node==null) {
			 return 0;
		 } else {
			 return (1+ maximum(hauteurSousArbre(node.getFG()),hauteurSousArbre(node.getFD())));
		 }
	 }
	 
	 private int maximum(int a,int b) {
		 if(a>b) {
			 return a;
		 }else {
			 return b;
		 }
	 }
	 
	 //nombre de feuilles
	 public int nbFeuilles() {
		 return calculFeuilles(this.racine);
	 }
	 
	 private int calculFeuilles(Noeud node) {
		 if(node==null) {
			 return 0;
		 } else {
			 if(node.estFeuille()) {
				 return 1;
			 } else {
				 return (calculFeuilles(node.getFG())+calculFeuilles(node.getFD()));
			 }
		 }
	 }
	 
	 //rechercher
	 public boolean rechercherNoeudParCaractere(String c) {
		 return rechercherSousNoeud(this.racine,c);
	 }
	 
	 private boolean rechercherSousNoeud(Noeud node,String c) {
		 if(node==null) {
			 return false;
		 } else {
			 if(node.getCaractere().equals(c)) {
				 return true;
			 }else {
				 return (rechercherSousNoeud(node.getFD(),c) || rechercherSousNoeud(node.getFG(),c));
			 }
		 }
	 }
	 
	 //insérer chaine dans arbre
	 public void insererChaine(String ch) {
		 int n=ch.length();
		 String[] charArray=ch.split("");
		 
		 if(this.estVide()) {
			 this.setRacine(charArray[0]);
			 Noeud j=new Noeud();
			 this.racine.setFG(new Noeud(charArray[1]));
			 j=this.racine.getFG();
			 int i=2;
			 while(i<n) {
				 Noeud fg=new Noeud(charArray[i]);
				 j.setFG(fg);
				 j=j.getFG();
				 i++;
			 }
			 j.setFG(new Noeud("\0"));
			 return;
		 }else {
			 //Premier lettre de la chaine est différent du racine
			 if(!this.racine.getCaractere().equals(charArray[0])) {
				if(this.racine.getFD().getCaractere()==null) {
		
					this.racine.setFD(new Noeud(charArray[0]));
					Noeud j=new Noeud();
					j=this.racine.getFD();
					int i=1;
					while(i<n) {
						Noeud fd=new Noeud(""+charArray[i]);
						 j.setFG(fd);
						 j=j.getFG();
						 i++;
					}
					j.setFG(new Noeud("\0"));
					return;
				}else {
					// Fils droite différent de null
					Noeud j=new Noeud();
					 Noeud y=new Noeud();
					 j=this.racine.getFD();
					 boolean stop=false;
					 int a=0;
					//parcourir l'arbre jusqu'à arrêter à \0 
					 //ou jusqu'à FD=null ou FG=null
					 while(!stop) {
						 if(j.getCaractere().equals("\0")) {
							 stop=true;
						 }else {
							 if(j.getCaractere().equals(charArray[a])) {
								 if(j.getFG()==null) {
									 stop=true;
								 }else {
									 j=j.getFG();
									 a++; 
								 }
								 
							 }else {
								 if(j.getFD().getCaractere()==null) {
									 stop=true;
								 }else {
									 j=j.getFD(); 
								 }
							 }
						 }	 
					 }
					 ajouterChaineArbre(charArray,a,j,n);					
				}
			 }else {
				//premier lettre de la chaine est égale à la racine
				 Noeud j=new Noeud();
				 Noeud y=new Noeud();
				 j=this.racine.getFG();
				 boolean stop=false;
				 int a=1;
				 //parcourir l'arbre jusqu'à arrêter à \0 
				 //ou jusqu'à FD=null ou FG=null
				 while(!stop) {
					 if(j.getCaractere().equals("\0")) {
						 stop=true;
					 }else {
						 if(j.getCaractere().equals(charArray[a])) {
							 if(j.getFG()==null) {
								 stop=true;
							 }else {
								 j=j.getFG();
								 a++; 
							 }
							 
						 }else {
							 if(j.getFD().getCaractere()==null) {
								 stop=true;
							 }else {
								 j=j.getFD(); 
							 }
						 }
					 }	 
				 }
				 //ajouter le reste de la chaine à l'arbre
				 ajouterChaineArbre(charArray,a,j,n);	
			 } 
		 }
}
	 
	 private void ajouterChaineArbre(String[] charArray,int a,Noeud j,int length) {
		 Noeud fd=new Noeud(charArray[a]);
		 Noeud y=new Noeud();
		 j.setFD(fd);
		 y=j.getFD();
		 int i=a+1;
		 while(i<length) {
			 Noeud x=new Noeud(""+charArray[i]);
			 y.setFG(x);
			 y=y.getFG();
			 i++;
		 }
		 y.setFG(new Noeud("\0"));
	 }
	


}

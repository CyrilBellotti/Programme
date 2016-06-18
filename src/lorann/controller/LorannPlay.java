package lorann.controller;


import java.awt.Point;
import java.io.IOException;


import lorann.contract.IDoActionOnHeroes;
import lorann.contract.ILorannFrame;
import lorann.contract.ILorannWorld;
import lorann.contract.IOrderPerformed;
import lorann.model.Hero;
import lorann.model.LorannWorld;
import lorann.view.LorannView;

public class LorannPlay implements IOrderPerformed {
	private ILorannWorld lorann;
	private ILorannWorld lorannMeeting;
	private ILorannFrame lorannFrame;
	private int	playMode;

// --------- Création du nouvel Hero dans la monde aux coordonnées 1,1 sur la map ---------
	public LorannPlay(final ILorannWorld lorann) {
		this.lorann = lorann;
		this.lorann.addMobile(new Hero(), 1, 1);
	}

	private ILorannWorld getLorannWorld() {
		return this.lorann;
	}

	private ILorannWorld getLorannMeeting() {
		return this.lorannMeeting;
	}

	private ILorannFrame getLorannFrame() {
		return this.lorannFrame;
	}

	public void setLorannFrame(final ILorannFrame lorannFrame) {
		this.lorannFrame = lorannFrame;
	}

	private int getPlayMode() {
		return this.playMode;
	}

	private void setPlayMode(final int playMode) {
		this.playMode = playMode;
		this.getLorannFrame().setViewMode(playMode);
	}

	private ILorannWorld getActualLorannWorld() {
		if (this.getPlayMode() == LorannView.VIEW_MODE_MEETING) {
			return this.getLorannMeeting();
		}
		return this.getLorannWorld();
	}

	@Override
	public void orderPerform(final UserOrder userOrder) throws IOException {
// --------- Le Hero bouge (position x,y) ---------
		switch (userOrder) {
			case UP:
				System.out.println("Déplacement HAUT");
				this.getActualLorannWorld().getHero().moveUp(); // Ce déplace vers le haut 
				break;
			case RIGHT:
				System.out.println("Déplacement DROITE");
				this.getActualLorannWorld().getHero().moveRight(); // Ce déplace vers la droite
				break;
			case DOWN:
				System.out.println("Déplacement BAS");
				this.getActualLorannWorld().getHero().moveDown(); // Ce déplace vers le bas
				break;
			case LEFT:
				System.out.println("Déplacement GAUCHE");
				this.getActualLorannWorld().getHero().moveLeft(); // Ce déplace vers la gauche
				break;
			case TOPRIGHT:
				System.out.println("Déplacement diago HAUT / DROITE");
				this.getActualLorannWorld().getHero().moveTR(); // Ce déplace en diagonale haut/droite
				break;
			case TOPLEFT:
				System.out.println("Déplacement diago HAUT / GAUCHE");
				this.getActualLorannWorld().getHero().moveTL(); // Ce déplace en diagonale haut/gauche
				break;
			case BOTTOMLEFT:
				System.out.println("Déplacement diago BAS / DROITE");
				this.getActualLorannWorld().getHero().moveBL(); // Ce déplace en diagonale bas/droite
				break;
			case BOTTOMRIGHT:
				System.out.println("Déplacement diago BAS / GAUCHE"); 
				this.getActualLorannWorld().getHero().moveBR(); // Ce déplace en diagonale bas/gauche
				break;
			case FIRE:
				System.out.println("Lorann tire une boule");
				this.getActualLorannWorld().getHero().fire();
			case NOP:
			default:
				break;
			}
		// le hero a-t-il fait une action particuliere 
		this.getWorldAnswer();
	}

// ---------- Méthode permettant de finir le niveau ----------
	private void FinNiveau() throws IOException {
		LorannView.displayMessage("Bravo tu as fini le niveau"); // Affiche une fenetre avec un message
		System.exit(0); // Quitte l'application
/*		this.setLorannMeeting(new LorannWorld("niveau 2.txt")); // ----------  Utile uniquement en cas de menu
		this.resolveWorldAnswer(); // ----------  Utile uniquement en cas de menu*/
		
	}

// ---------- Ajoute Hero dans le sous monde ---------- Utile uniquement en cas de menu
/*	private void resolveWorldAnswer() throws IOException {
		this.getLorannMeeting().addMobile(new Hero(), 1, 1);
		this.getLorannFrame().setMeeting(this.getLorannMeeting());
		this.setPlayMode(LorannView.VIEW_MODE_MEETING);
	}*/

// ---------- Méthode permettant de quitter le sous monde ----------  Utile uniquement en cas de menu
/*	private void exitMetting() throws IOException {
		this.setPlayMode(LorannView.VIEW_MODE_MAP);
	}*/

// ---------- Méthode permettant de manger la CrystalBall et d'ouvrir la porte ----------	
	private void eat() throws IOException
	{
		this.getActualLorannWorld().delElement(this.getActualLorannWorld().getHero().getX(), this.getActualLorannWorld().getHero().getY()); // Permet de manger la CrystalBall
		Point point = this.getActualLorannWorld().getFirstPositionByCategory("GateClose"); // Permet de trouver l'emplacement de la porte fermée
		if (point.getX() != -1 && point.getY() != -1) // Si on a trouvé la porte
		{
			this.getActualLorannWorld().delElement((int)point.getX(), (int)point.getY()); // Supprime la porte fermée
			this.getActualLorannWorld().addBoneOpen((int)point.getX(), (int)point.getY()); // Ajoute la porte ouverte
		}
	}

// ---------- Fonction permettant au Hero de manger le bourse dorée ----------
	private void purse() throws IOException{
		this.getActualLorannWorld().delElement(this.getActualLorannWorld().getHero().getX(), this.getActualLorannWorld().getHero().getY());
	}
	
	
// ---------- Fonction faisant que le Hero meurt au contact d'un monstre ----------	
	private void eatHero() throws IOException{
		this.getActualLorannWorld().delHero(this.getActualLorannWorld().getHero().getX(), this.getActualLorannWorld().getHero().getY());
		System.exit(0);
	}
	
// ---------- Fonction permettant de quitter le sous monde ----------  Utile uniquement en cas de menu
/*	private void escapeMetting() throws IOException {
		this.exitMetting(); 
		this.getActualLorannWorld().getHero().moveBack();
	}*/
	
	// ---------- Fonction permettant de donnée une réponse concernant l'action du Hero (ex : passer une porte)
	private void getWorldAnswer() throws IOException {
		final IDoActionOnHeroes element = this.getActualLorannWorld().getElements(this.getActualLorannWorld().getHero().getX(),
				this.getActualLorannWorld().getHero().getY());

		switch (element.getActionOnHeroes()) {
			case ENTER_GATE:
// ---------- R'envoie à la méthode permettant de finir le niveau ----------
				this.FinNiveau();
				break;
// ------ R'envoie à la méthode permettant de sortir du sous monde ---------- Utile uniquement en cas de menu 
	/*		case ESCAPE:
				this.escapeMetting();*/
			case EAT: 
// ---------- R'envoie à la méthode permettant de manger une bourse dorée ----------				
				this.eat();
			case EAT_PURSE:
				this.purse();
				break;
// ---------- R'envoie à la méthode permettant de tuer le Hero ----------
			case EAT_HERO:
				this.eatHero();
			case NOP:
			default:
				break;
		}
	}

	private void setLorannMeeting(final ILorannWorld lorannMeeting) {
		this.lorannMeeting = lorannMeeting;
	}
}

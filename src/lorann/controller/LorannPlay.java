package lorann.controller;


import java.awt.Point;
import java.io.IOException;


import lorann.contract.IDoActionOnHeroes;
import lorann.contract.ILorannFrame;
import lorann.contract.ILorannWorld;
import lorann.contract.IOrderPerformed;
import lorann.model.Fire;
import lorann.model.Hero;
import lorann.model.LorannWorld;
import lorann.model.Monster_1;
import lorann.model.Monster_2;
import lorann.model.Monster_3;
import lorann.model.Monster_4;
import lorann.view.LorannView;

public class LorannPlay implements IOrderPerformed {
	private ILorannWorld lorann;
	private ILorannWorld lorannMeeting;
	private ILorannFrame lorannFrame;
	private int	playMode;
	private String lastDirection;

// --------- Création du nouvel Hero dans la monde aux coordonnées 1,1 sur la map ---------
	public LorannPlay(final ILorannWorld lorann) {
		lastDirection = null;
		this.lorann = lorann;
		this.lorann.addMobile(new Hero(), 1, 1);
		this.lorann.addMobile(new Monster_1(), 9, 5);
		this.lorann.addMobile(new Monster_2(), 11, 5);
		this.lorann.addMobile(new Monster_3(), 13, 5);
		this.lorann.addMobile(new Monster_4(), 16, 5);
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
				lastDirection = "down";
				System.out.println("Déplacement HAUT");
				this.getActualLorannWorld().getHero().move(this.getActualLorannWorld().getHero().getX(), this.getActualLorannWorld().getHero().getY() - 1); // Ce déplace vers le haut 
				break;
			case RIGHT:
				lastDirection = "left";
				System.out.println("Déplacement DROITE");
				this.getActualLorannWorld().getHero().move(this.getActualLorannWorld().getHero().getX() +1, this.getActualLorannWorld().getHero().getY()); // Ce déplace vers la droite
				break;
			case DOWN:
				lastDirection = "up";
				System.out.println("Déplacement BAS");
				this.getActualLorannWorld().getHero().move(this.getActualLorannWorld().getHero().getX(), this.getActualLorannWorld().getHero().getY() + 1);; // Ce déplace vers le bas
				break;
			case LEFT:
				lastDirection = "right";
				System.out.println("Déplacement GAUCHE");
				this.getActualLorannWorld().getHero().move(this.getActualLorannWorld().getHero().getX() - 1, this.getActualLorannWorld().getHero().getY()); // Ce déplace vers la gauche
				break;
			case TOPRIGHT:
				lastDirection = "dl";
				System.out.println("Déplacement diago HAUT / DROITE");
				this.getActualLorannWorld().getHero().move(this.getActualLorannWorld().getHero().getX() + 1, this.getActualLorannWorld().getHero().getY() - 1); // Ce déplace en diagonale haut/droite
				break;
			case TOPLEFT:
				lastDirection = "dr";
				System.out.println("Déplacement diago HAUT / GAUCHE");
				this.getActualLorannWorld().getHero().move(this.getActualLorannWorld().getHero().getX() - 1, this.getActualLorannWorld().getHero().getY() - 1); // Ce déplace en diagonale haut/gauche
				break;
			case BOTTOMLEFT:
				lastDirection = "tr";
				System.out.println("Déplacement diago BAS / DROITE");
				this.getActualLorannWorld().getHero().move(this.getActualLorannWorld().getHero().getX() - 1, this.getActualLorannWorld().getHero().getY() + 1); // Ce déplace en diagonale bas/droite
				break;
			case BOTTOMRIGHT:
				lastDirection = "tl";
				System.out.println("Déplacement diago BAS / GAUCHE"); 
				this.getActualLorannWorld().getHero().move(this.getActualLorannWorld().getHero().getX() + 1, this.getActualLorannWorld().getHero().getY() + 1); // Ce déplace en diagonale bas/gauche
				break;
			case FIRE:
				System.out.println("Lorann tire une boule");
				if (lastDirection != null && this.getLorannWorld().getExistFireball() == false) {
					this.lorann.addMobile(new Fire(lastDirection), this.getActualLorannWorld().getHero().getX(), this.getActualLorannWorld().getHero().getY());
					this.getLorannWorld().setExistFireball(true);
				}
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

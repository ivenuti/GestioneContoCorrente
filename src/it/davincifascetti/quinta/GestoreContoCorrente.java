package it.davincifascetti.quintainf;

public class GestoreContoCorrente extends Thread{
	
	private ContoCorrente cc;
	private String descrizione;
	
	public GestoreContoCorrente(ContoCorrente cc, String descrizione) {
		this.cc = cc;
		this.descrizione = descrizione;
	}
	
	public void run(){
		System.out.println(descrizione + " in esecuzione");
		float quantità = (int)(Math.random()*90+10);
		if(Math.random()<0.5) {
			//versamento
			System.out.println(descrizione + " Verso "+ quantità);
			cc.versa(quantità);
		}else {
			//prelievo
			System.out.println(descrizione + " Prelevo "+ quantità);
			try {
				cc.preleva(quantità);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.out.println(descrizione + " sta per finire; saldo="+cc.getSaldo());
	}

	public static void main(String[] args) throws InterruptedException {
		ContoCorrente c = new ContoCorrente("02010102");
		GestoreContoCorrente bancomat = new GestoreContoCorrente(c, "Bancomat");
		GestoreContoCorrente sportello = new GestoreContoCorrente(c, "Sportello");
		GestoreContoCorrente online = new GestoreContoCorrente(c, "Online");
		bancomat.start();
		sportello.start();
		online.start();
		
		bancomat.join();
		sportello.join();
		online.join();
		System.out.println("Alla fine il saldo è "+c.getSaldo());

	}

}

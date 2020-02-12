package it.davincifascetti.quinta;

public class ContoCorrente {
	private float saldo;
	private String id;
	
	public ContoCorrente(String id) {
		this(id, 0);
	}
	
	public ContoCorrente(String id, float saldoIniziale) {
		this.id = id;
		this.saldo = saldoIniziale;
	}
	
	public synchronized void versa(float quanto) {
		//saldo = saldo + quanto;
		float reg1 = quanto;
		float reg2 = saldo;
		float acc = reg1 + reg2;
		saldo = acc;
		notify();
	}
	
	public synchronized void preleva(float quanto) throws InterruptedException {
		while(saldo-quanto<0) {
			System.out.println("Non c'è disponibilità di "+quanto);
			wait();
		}
			//saldo = saldo - quanto;
			float reg1 = quanto;
			float reg2 = saldo;
			float acc = reg2 - reg1;
			saldo = acc;
		
	}
	
	public float getSaldo() {
		return saldo;
	}

}

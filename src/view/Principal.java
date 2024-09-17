package view;
import control.Banco;
import java.util.Random;

public class Principal {

	
	public static void main(String[] args) {
		Random rd = new Random();
		int numTransactions = 20;
		for (int i = 0; i < numTransactions; i++) {
			int codConta = rd.nextInt(10000);
			double saldoConta = rd.nextDouble()*2000;
			double valor = rd.nextDouble() * 200;
			boolean isSaque = rd.nextBoolean();
			Banco banco = new Banco(codConta, saldoConta, valor, isSaque);
			banco.start();
		}
		
		
		
	}
}

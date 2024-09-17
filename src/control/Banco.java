package control;

import java.util.concurrent.Semaphore;

public class Banco extends Thread {
	private final Semaphore saqueControl = new Semaphore(1);
	private final Semaphore depositoControl = new Semaphore(1);
	private final Semaphore transactionControl = new Semaphore (2);
	private int codConta;
	private double saldoConta;
	private double valor;
	private boolean isSaque;
	
	public Banco (int codConta, double saldoConta, double valor, boolean isSaque) {
		this.codConta = codConta;
		this.saldoConta = saldoConta;
		this.valor = valor;
		this.isSaque = isSaque;
	}
		public void run() {
			try {
				transactionControl.acquire();
				if(isSaque) {
					try {
					saqueControl.acquire();
					long timestamp = System.currentTimeMillis();
					//transactionControl.acquire();
					saldoConta -= valor;
					System.out.println("Saque realizado com sucesso \n"
							+ "Conta:" + codConta + "\n"
							+ "Saldo Atualizado:" + saldoConta + "\n"+
							"TimeStamp:" + timestamp + "\n\n");
					}
					catch(InterruptedException e) {
						e.getMessage();
					}
					finally {
						//transactionControl.release();
						saqueControl.release();
						sleep(1000);
					}
				}
				
				else {
					try {
					depositoControl.acquire();
					//transactionControl.acquire();
					saldoConta += valor;
					long timestamp = System.currentTimeMillis();
					System.out.println("Depósito realizado com sucesso \n" +
										"Conta: " + codConta + "\n" +
										"Saldo Atualizado: " + saldoConta  + "\n"+
										"TimeStamp:" + timestamp + "\n\n");
					}catch(InterruptedException e) 
					{
						e.getMessage();
					}
					finally 
					{
						//transactionControl.release();
						depositoControl.release();
						sleep (1000);
					}
				}
				
				
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				transactionControl.release();
			}
			
		
		}	
		
	}



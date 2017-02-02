package br.drinith.teste;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.csvreader.CsvReader;

public class TesteVerificarEmailThread {


	public static void main(String args[]) throws IOException {
		
		String nome;
		String email;
		String path = "C:\\Users\\Instrutor\\Desktop\\";
		CsvReader cvs = new CsvReader(path+"cadastro.csv");
		
		cvs.readHeaders();
		
	    List <Email> listEmail = new ArrayList<Email>();
	    
	    while (cvs.readRecord())
		{
			 nome = cvs.get("Nome");
			 email = cvs.get("E-mail");
			
			listEmail.add(new Email(nome,email));
			
			// perform program logic here
			
		}

		cvs.close();
	    
	 
<<<<<<< HEAD
		Thread t= null;
=======
	    VerificarEmailThread vEmail;
>>>>>>> branch 'master' of https://github.com/drinith/VerificarEmail.git
  
<<<<<<< HEAD
		
		for (int i = 0; i <10; i++) {
=======
	    Thread[] t = new Thread[listEmail.size()];
		for (int i = 0; i < listEmail.size(); i++) {
>>>>>>> branch 'master' of https://github.com/drinith/VerificarEmail.git
				
			vEmail = new VerificarEmailThread();
			vEmail.email = listEmail.get(i);
<<<<<<< HEAD
		    t = new Thread(vEmail);
			t.start();
=======
			t[i] = new Thread(vEmail);
			t[i].start();
>>>>>>> branch 'master' of https://github.com/drinith/VerificarEmail.git
			
<<<<<<< HEAD
			System.out.print(i);
			try {
				t.join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
=======
		
			System.out.println("Contagem "+i);
>>>>>>> branch 'master' of https://github.com/drinith/VerificarEmail.git
		}
<<<<<<< HEAD
		
		
		
	    
=======
		for (Thread thread : t) {
			try {
				thread.join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	
	
>>>>>>> branch 'master' of https://github.com/drinith/VerificarEmail.git
	    
	    for (int ctr = 0; ctr < VerificarEmailThread.listEmail.size(); ctr++) {
	        System.out.println(VerificarEmailThread.listEmail.get(ctr).endereco +
	        		" is valid? " + VerificarEmailThread.listEmail.get(ctr).existe);
	    }
	   
	}
	
}

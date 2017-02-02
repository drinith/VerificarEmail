package br.drinith.testethread;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.csvreader.CsvReader;
import com.csvreader.CsvWriter;

public class TesteVerificarEmailThread {


	public static void main(String args[]) throws IOException {
		
		String nome;
		String email;
		String path = "C:\\Users\\Felipe\\Desktop\\";
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
	    
	 
	    VerificarEmailThread vEmail;
  
	    Thread[] t = new Thread[listEmail.size()];
		for (int i = 0; i < listEmail.size(); i++) {
				
			vEmail = new VerificarEmailThread();
			vEmail.email = listEmail.get(i);
			t[i] = new Thread(vEmail);
			t[i].start();
			
		
			System.out.println("Contagem "+i);
		}
		for (Thread thread : t) {
			try {
				thread.join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	
	
	    
	    for (int ctr = 0; ctr < VerificarEmailThread.listEmail.size(); ctr++) {
	        System.out.println(VerificarEmailThread.listEmail.get(ctr).getEndereco() +
	        		" is valid? " + VerificarEmailThread.listEmail.get(ctr).isExiste());
	    }
	    
	    String outputFile = path+"users.csv";

		// before we open the file check to see if it already exists
		boolean alreadyExists = new File(outputFile).exists();
		try {
			// use FileWriter constructor that specifies open for appending
			CsvWriter csvOutput = new CsvWriter(new FileWriter(outputFile, true), ',');
			// if the file didn't already exist then we need to write out
			// the header line
			if (!alreadyExists) {
				csvOutput.write("Nome");
				csvOutput.write("E-mail");
				csvOutput.write("existe");
				csvOutput.endRecord();
				
			}
			
			for (int ctr = 0; ctr < VerificarEmailThread.listEmail.size(); ctr++) {

				

				// else assume that the file already has the correct header line

				// write out a few records e se o email existir
				
					
					csvOutput.write( VerificarEmailThread.listEmail.get(ctr).getNome());
					csvOutput.write( VerificarEmailThread.listEmail.get(ctr).getEndereco());
					csvOutput.write( VerificarEmailThread.listEmail.get(ctr).getExiste());
					csvOutput.endRecord();
				

			}


			System.out.println("Fim do processo ");
			csvOutput.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	   
	}
	
}
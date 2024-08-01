package com.example.demo;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import jakarta.mail.MessagingException;

@Controller
public class myController {
	
	private final ProdJDBCTemplate prodJDBCTemp;
	
	@Autowired
	EmailService emailservice;
	
	ArrayList<prodottoOrdinato>	listaO = new ArrayList();
	double sommaO = 0;
	ArrayList<String> listaI = new ArrayList();

    @Autowired
    public myController(ProdJDBCTemplate prodJDBCTemp) {
        this.prodJDBCTemp = prodJDBCTemp;
    }
    @GetMapping("/negozio")
	public String getNegozio(Model model) {
    	
ArrayList<prodotto>	lista = prodJDBCTemp.getLista();
	    
	    for (prodotto p1: lista) {
	    	System.out.println(p1);
	    }
	    
	    listaI.clear();
	    listaO.clear();
	    sommaO = 0;
	    model.addAttribute("lista", lista);
	    	
	    	
		
		
		return "Store2";
	}
	
	@GetMapping("/gestore")
	public String getGestore() {
		
		return "formG";
	}
	
	@GetMapping("/test")
	public String getTest() {
		
		return "test";
	}
	
	
	@PostMapping("/submit")
	public ResponseEntity<String> getSubmit(@RequestParam("nome") String nome, @RequestParam("tipo") String tipologia, 
			@RequestParam("prezzo") int prezzo, @RequestParam("posti") int posti, @RequestParam("data") String data, 
			@RequestParam("img") String img) {
		
		System.out.println(prezzo);
		
		prodJDBCTemp.insertProd(nome, tipologia, prezzo, posti, data, img);
		
		
		return ResponseEntity.ok("Prodotto " + nome + " aggiunto con successo");
		
	}
	
	@PostMapping("/delete")
	public ResponseEntity<String> getDelete(@RequestParam("nome") String nome){
		
		
		prodJDBCTemp.delete(nome);
		
		return ResponseEntity.ok("Prodotto " + nome + " eliminato con successo");
	}
	
	    @GetMapping("/")
		public String getStore(Model model) {
	    	
	    ArrayList<prodotto>	lista = prodJDBCTemp.getLista();
	    
	    for (prodotto p1: lista) {
	    	System.out.println(p1);
	    }
	    
	    listaI.clear();
	    listaO.clear();
	    sommaO = 0;
	    model.addAttribute("lista", lista);
	    	
	    	
		return "index";
	}
	    
	    @PostMapping("/process")
	    public String getProcess(@RequestParam("qnts")String [] listaQnt, Model model ) {
	    	ArrayList<prodottoOrdinato>	listaOrdini = new ArrayList(); 
	    	double somma = 0;
	    	ArrayList<prodotto>	lista = prodJDBCTemp.getLista();
	    	
	    	
	    	for (int i = 0; i < lista.size(); i++) {
	    		
	    		if (!listaQnt[i].equals("0")) {
	    			prodottoOrdinato p1 = new prodottoOrdinato();
	    			p1.setNome(lista.get(i).nome);
	    			p1.setQnt(Integer.parseInt(listaQnt[i]));
	    			listaO.add(p1);
	    			listaOrdini.add(p1);
	    			listaI.add(lista.get(i).url);
	    			
	    			somma += lista.get(i).getPrezzo() * p1.getQnt();
	    			sommaO += lista.get(i).getPrezzo() * p1.getQnt();
	    			
	    			
	    		}
	    		
	    	}
	    	
	    	model.addAttribute("lista", listaOrdini);
	    	model.addAttribute("somma", somma);
	    	
	    	
	    	return ("recap");
	    }
	    @GetMapping("/show")
	    @ResponseBody
	    public ArrayList<prodotto> getLista(){
	    	
	    	ArrayList<prodotto>	lista = prodJDBCTemp.getLista();
	    	
	    	return lista;
	    	
	    }
	    	
	    
  @PostMapping("/conferma")
  public ResponseEntity<String> getConferma(@RequestParam("mail") String mail) throws MessagingException{
	
	  String to = mail;
		 String subject = "ordine da TalentformStore confermato";
		 String text = "";
		 text+= "Hai acquistato:  \n";
		 String img = "";
		 for (prodottoOrdinato p1 : listaO) {
			 
			 text+= "Modello: " + p1.getNome() + "\n";
			 text+= "Numero pezzi: " + p1.getQnt() + "\n";
			 prodJDBCTemp.updatePosti(p1.getQnt(), p1.getNome());
		 }
		 
		 
		 
		 text += "Il prezzo totale da pagare è " + sommaO + " euro";
		  emailservice.sendEmailWithImage(to, subject, text,listaI);
		  
		  
		  
		  
	  
	  return ResponseEntity.ok("Conferma dell'avvenuto acquisto, guarda la tua mail");
	  
  }}


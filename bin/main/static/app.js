


	
	

window.onload = gestoreLoad;

// creiamo un array vuoto chiamato listaO che ci serve per ottenere tutti gli elementi presenti sul db 
let listaO =  [];


// creiamo la classe prodotto ordinato 
class prodottoOrdinato {
   			
   			  constructor(name, prezzo, qnt, url) {
   				this.name = name;
   						    
   							this.prezzo = prezzo;
   							
   							this.qnt = qnt;
							
							this.url = url;
   					
   			  }
			  visualizza(){
			  		
			  		return "Nome: " + this.name + " Prezzo " +  this.prezzo + " Quantità " +  this.qnt;
			  	  }
			  	}
			  	

function gestoreLoad(){
	
	
	// la funzione location reload ci permette di avere un refresh automatico della pagina
	// anche quando l'utente utilizza le frecce del browser
	window.addEventListener('pageshow', function(event) {
	            if (event.persisted) {
	                location.reload();
	            }
	        });
	
	
	// effettua una chiamata alla rotta dell'applicazione show
	$.ajax({
	       url: '/show',  // URL dell'endpoint REST
	       type: 'GET',
	       success: function(data) {
			
			// va a iterare sui dati per ogni item all'interno della lista va a instanziare
			// un oggetto di tipo prodottoOrdinato
			   
	           data.forEach(function(item) {
				
				let p1 = new prodottoOrdinato(item.nome, item.prezzo, item.url);
				
				listaO.push(p1);
				 
				
				
	               
	           });
	       },
	       error: function(jqXHR, textStatus, errorThrown) {
	           console.error('Error fetching data:', textStatus, errorThrown);
	           $('#dataList').append('<li>Error loading data</li>');
	       }
	   });
	   
	   
	  
	
	
	   let nodo = document.getElementById("result");
		  
		
	let btn = document.getElementById("btn");
	btn.onclick = add;
	
	// viene inizializzata una lista che ci servirà per inserire i prodotti ordinati
	let lista =[];
	
	
	// aggiunge uno o più prodotti ordinati al carrello
	function add(){
		
		
		
		
		// per ogni elemento presente nella lista resituita dal database
		for (let i = 0; i < listaO.length; i++) {
			        
			        // recuperiamo la quantià di prodotti dellìevento selezionata dall'utente
					let qnt = document.getElementById(listaO[i].name).value;
					
					
					if (qnt > 0){
						let p1 = new prodottoOrdinato(listaO[i].name, listaO[i].prezzo, qnt);
						lista.push(p1);
					}
					
				}
				
						
		crea();
		lista = [];
		
	}
	
	
	// funzione crea  va a popolare il carrello con gli elementi selezionati dall'utente
	function crea(){
		
		// puliamo il nodo result
		rimuoviFigli(nodo);
		
		// una variabile per ottenere la somma degli acquisti
		let tot = 0;
	  
		// per ogni elemento presente nella lista, creiamo un elemento p
		// settiamo il textcontent dell'elemento p con il visualizza dell'oggetto
		// incrementiamo la variaible tot con il prezzo
		for (let i = 0; i < lista.length; i++){
			
			let nodoP = document.createElement("p");
			nodoP.textContent = lista[i].visualizza();
			nodo.appendChild(nodoP);
			tot += lista[i].prezzo * lista[i].qnt;
			
		}
		let nodoT = document.createElement("p");
				nodoT.textContent = "Totale : " +  tot;
				nodo.appendChild(nodoT);
	}
	
	function rimuoviFigli (nodo) {
	 while (nodo.childNodes.length > 0) {
	 nodo.removeChild(nodo.firstChild);
	 
	 
	 }}	
	
	 // Fun
	         // Inizializzare il carousel al caricamento della pagina
	       
			 		
	
	
	
}			
		 	         
	


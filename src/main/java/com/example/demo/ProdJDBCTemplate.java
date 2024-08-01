package com.example.demo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Component;

@Component
public class ProdJDBCTemplate {
	
	private JdbcTemplate jdbcTemplateObject;
	
	@Autowired
    public void setJdbcTemplateObject(JdbcTemplate jdbcTemplateObject) {
        this.jdbcTemplateObject = jdbcTemplateObject;
    }
	
	
	public int insertProd(String nome, String tipologia, double prezzo, int postiDisponibili, String data, String url) {
		
		String query = "INSERT INTO eventi (nome,  tipologia, prezzo, postiD, data,  url) VALUES (?, ?, ?, ?, ?, ?)";
		return jdbcTemplateObject.update(query, nome, tipologia, prezzo, postiDisponibili, data, url);
		
	}
	
	public int delete(String nome) {
		
		String query = "DELETE FROM eventi WHERE nome = ?";
		return jdbcTemplateObject.update(query, nome);
	}
	
	public ArrayList <prodotto> getLista(){
		// seleziona tutti i record da eventi
		String query = "SELECT * FROM eventi";
		
		// il metodo esegue la query e come secondo parametro crea un result set extractor
		 return jdbcTemplateObject.query(query, new ResultSetExtractor<ArrayList<prodotto>>() {
            // l'oggetto resultSetExtractor ha il metodo extractData che deve essere obbligatoriamente implementato
			@Override
			public ArrayList<prodotto> extractData(ResultSet rs) throws SQLException, DataAccessException {
				
				// creiamo un arraylist di prodotto che ci servirà come valore di ritorno del metodo
				ArrayList <prodotto> listaP = new ArrayList<>();
				
				// andiamo a iterare il resulta set
				while (rs.next()) {
					
					prodotto p1 = new prodotto();
					// con i risultati del result set abbiamo instanziato un oggetto prodotto e lo abbiamo
					// aggiunto alla lista
					p1.setNome(rs.getString("nome"));
					p1.setTipologia(rs.getString("tipologia"));
					p1.setPrezzo(rs.getDouble("prezzo"));
					p1.setPostiDisponibili(rs.getInt("postiD"));
					p1.setData(rs.getString("data"));
					p1.setUrl(rs.getString("url"));
					listaP.add(p1);
					
				}
				
				return listaP;
			}
		
	});

}
	
	public int updatePosti(int posti, String nome) {
        String query = "UPDATE eventi SET postiD = postiD - ? WHERE nome = ?";
        return jdbcTemplateObject.update(query, posti, nome);
    }




}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.samirAtivacao.controller;

import java.util.List;

import com.samirAtivacao.DAO.DAOInformacoesDosPrev;
import com.samirAtivacao.modelo.Ativo;
import com.samirAtivacao.modelo.InfomacoesDosPrev;
import com.samirAtivacao.modelo.Usuario;
import com.samirAtivacao.repository.SeleniumRepositorio;

/**
 *
 * @author AGU
 */
public class GeralController {
    private SeleniumRepositorio repository = new SeleniumRepositorio();
    private Thread backgroundThread;
    
    public String beremiz( Usuario usuario) {
			/*try {
	            //URL do som que no caso esta no pendrive, mais ainda é uma fase de teste.
	            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("espartanos.wav").getAbsoluteFile());
	            //AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("autodestruição.wav").getAbsoluteFile());
	            Clip clip = AudioSystem.getClip();
	            clip.open(audioInputStream);
	            clip.start();
	        } catch (Exception ex) {
	            System.out.println("Erro ao executar SOM!");
	            ex.printStackTrace();
	        }*/
			repository.open(usuario);
			try {
				repository.colocarFiltro(usuario.getEtiqueta());
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Ativo ativo = new Ativo();
			int x = 0;
			int y = 0;
			boolean validacao;
		    ativo.setAtivo(false);
			try {
				while("Sem registros para exibir" != repository.entrarNoProcessoAutomatico(usuario.getEtiqueta())) {
					validacao = repository.dataDeValidacaoDosPrev();
					if ( validacao == true) {
						ativo = repository.verificacaoDeAtivo();
						repository.etiquetar(ativo.getAtivo(), ativo.getBeneficio(), 1);
						x++;
					}
					else {
						repository.etiquetar(ativo.getAtivo(), ativo.getBeneficio(), 0);
						y++;
					}
				}
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			String finalizacao ="Processos Validos: " + x + "          invalidos: " + y;
			//sadbja
			/*try {
	            //URL do som que no caso esta no pendrive, mais ainda é uma fase de teste.
	            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("gemido.wav").getAbsoluteFile());
				// AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("autodestruição.wav").getAbsoluteFile());
	            Clip clip = AudioSystem.getClip();
	            clip.open(audioInputStream);
	            clip.start();
	        } catch (Exception ex) {
	            System.out.println("Erro ao executar SOM!");
	            ex.printStackTrace();
	        }*/
			repository.quit();
			
			 return finalizacao;
			
		}
    public String samir(Usuario  usuario) {
    	System.out.println("etiqueta do funcionar: " + usuario.getEtiqueta());
    	InfomacoesDosPrev info = new InfomacoesDosPrev();
    	
    	DAOInformacoesDosPrev daoInfo = new DAOInformacoesDosPrev();
    	
    	repository.open(usuario);
    	
    	try {
    		repository.colocarFiltro(usuario.getEtiqueta());
			
		} catch (Exception e) {
			// TODO: handle exception
		}
    	Ativo ativo = new Ativo();
    	ativo.setAtivo(false);
		int x = 0;
		boolean validacao;
		String letra = "";
		daoInfo.excluirUInfomacoesDosPrev();
		try {
			while("Sem registros para exibir" != repository.entrarNoProcessoAutomatico(usuario.getEtiqueta())) {
				validacao = repository.dataDeValidacaoDosPrev();
				if ( validacao == true) {
					ativo = repository.verificacaoDeAtivo();
					if(ativo.getAtivo() == true) {
						if(x <= 10) {
							info = repository.procurarDosPrev();
							daoInfo.salvarInformacoesDosPrev(info);
							repository.etiquetar(validacao, letra, 0);
							x++;
						}
						else {
							break;
						}
						
					}
					else {
						repository.etiquetar(ativo.getAtivo(), ativo.getBeneficio(), 1);
					}
				}
				else {
					repository.etiquetar(ativo.getAtivo(), ativo.getBeneficio(), 0);
					
				}
			}
			
		} catch (Exception e) {
			System.out.println("entrei no cath");
		}
		BancoController banco = new BancoController();
		List<InfomacoesDosPrev> aLista = banco.litaDosPrev();
		
		//repository.teste(info);
		 return "deu certo";
		
		
	}
    public String InserirNoFront(Usuario usuario, Boolean driever) {
    	//repository.open(usuario);
    	if(driever == true){
    		repository.openFront(usuario);
    	}
    	else {
    		
    		Runnable task = new Runnable() {
    			@Override
    			public void run() {
    				repository.openSamirFront(usuario);
    			}
        	};
    	}
    	
    	BancoController banco = new BancoController();
		List<InfomacoesDosPrev> lista = banco.litaDosPrev();
		
		int x = 0;
		while(x <lista.size()) {
			repository.inserirDosPrev(lista.get(x));
			x++;
		}
		
		return "Entrei";
    }
    
  
	public String pararTriagem () {
    	try {

			repository.quit();

			

		} catch (Exception e) {
		}
    	return "Parou";
    }
}

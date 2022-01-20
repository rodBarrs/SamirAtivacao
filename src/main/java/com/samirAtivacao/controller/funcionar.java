/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.samirAtivacao.controller;

import com.samirAtivacao.modelo.Ativo;
import com.samirAtivacao.modelo.InfomacoesDosPrev;
import com.samirAtivacao.modelo.LoginModelo;
import com.samirAtivacao.repository.SeleniumRepositorio;

/**
 *
 * @author AGU
 */
public class funcionar {
    private SeleniumRepositorio repository = new SeleniumRepositorio();
    
    public String testeDeAtivacao( LoginModelo login) {
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
			repository.open(login);
			try {
				repository.colocarFiltro();
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
				while("Sem registros para exibir" != repository.entrarNoProcessoAutomatico()) {
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
    public InfomacoesDosPrev[] samir(LoginModelo  login) {
    	repository.open(login);
    	try {
    		repository.colocarFiltro();
			
		} catch (Exception e) {
			// TODO: handle exception
		}
    	Ativo ativo = new Ativo();
    	ativo.setAtivo(false);
		InfomacoesDosPrev[] lista = new InfomacoesDosPrev[100];
		int x = 0;
		boolean validacao;
		String letra = "";
		try {
			while("Sem registros para exibir" != repository.entrarNoProcessoAutomatico()) {
				validacao = repository.dataDeValidacaoDosPrev();
				if ( validacao == true) {
					ativo = repository.verificacaoDeAtivo();
					if(ativo.getAtivo() == true) {
						lista[x] = repository.procurarDosPrev();
						repository.etiquetar(validacao, letra, 0);
						x++;
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
			// TODO: handle exception
		}
		System.out.println("Lista: " + lista);
		repository.teste(lista);
		 return lista;
		
		
	}
}

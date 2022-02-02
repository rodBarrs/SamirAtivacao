/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.samirAtivacao.controller;

import com.samirAtivacao.DAO.DAOInformacoesDosPrev;
import com.samirAtivacao.modelo.Ativo;
import com.samirAtivacao.modelo.InfomacoesDosPrev;
import com.samirAtivacao.modelo.Usuario;
import com.samirAtivacao.repository.SeleniumRepositorio;

/**
 *
 * @author AGU
 */
public class funcionar {
    private SeleniumRepositorio repository = new SeleniumRepositorio();
    
    public String testeDeAtivacao( Usuario usuario) {
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
    public InfomacoesDosPrev[] samir(Usuario  usuario) {
    	System.out.println("etiqueta do funcionar: " + usuario.getEtiqueta());
    	InfomacoesDosPrev info = new InfomacoesDosPrev();
    	repository.open(usuario);
    	DAOInformacoesDosPrev salvarInfo = new DAOInformacoesDosPrev();
    	try {
    		repository.colocarFiltro(usuario.getEtiqueta());
			
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
			while("Sem registros para exibir" != repository.entrarNoProcessoAutomatico(usuario.getEtiqueta())) {
				validacao = repository.dataDeValidacaoDosPrev();
				if ( validacao == true) {
					ativo = repository.verificacaoDeAtivo();
					if(ativo.getAtivo() == true) {
						info = repository.procurarDosPrev();
						salvarInfo.salvarInformacoesDosPrev(info);
						lista[x] = info;
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
			System.out.println("entrei no cath");
		}
		repository.teste(info);
		 return lista;
		
		
	}
}

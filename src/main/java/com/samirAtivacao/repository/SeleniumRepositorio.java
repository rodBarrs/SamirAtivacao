/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.samirAtivacao.repository;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.samirAtivacao.modelo.Ativo;
import com.samirAtivacao.modelo.InfomacoesDosPrev;
import com.samirAtivacao.modelo.LoginModelo;

/**
 *
 * @author AGU
 */
public class SeleniumRepositorio {
    WebDriver driver;
	WebDriverWait wait;

	public String open(LoginModelo usuario) {
		String url = "https://sapiens.agu.gov.br/login";
		System.setProperty("webdriver.gecko.driver", "GeckoDriver.exe");
		driver = new FirefoxDriver();
		driver.get(url);

		String campoUserPath = "/html/body/div[1]/div[1]/div/div/div[2]/div/div/div/table[1]/tbody/tr/td[2]/input";
		WebElement campoUserElemt = driver.findElement(By.xpath(campoUserPath));
		// String user = "039.669.222-23";
		campoUserElemt.sendKeys(usuario.getUser());

		String campoPassPath = "/html/body/div[1]/div[1]/div/div/div[2]/div/div/div/table[2]/tbody/tr/td[2]/input";
		WebElement campoPassElemt = driver.findElement(By.xpath(campoPassPath));
		// String pass = "AfonsoSoVacuo1";
		campoPassElemt.sendKeys(usuario.getPass());

		String sendLoginPath = "button-1019-btnIconEl";
		WebElement sendLoginElem = driver.findElement(By.id(sendLoginPath));
		sendLoginElem.click();

		// driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		// WebElement myDynamicElement = (new WebDriverWait(driver,
		// 60)).until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/div[4]/div[1]/div[2]/div/div[1]/div[1]/div[2]/div/a[3]/span/span/span[1]")));
		// wait.until(ExpectedConditions.elementToBeClickable(
		// By.xpath("/html/body/div[4]/div[1]/div[2]/div/div[2]/div/div[4]/div/table/tbody/tr[1]/td[3]/div/a[1]")));
		long time = 100;
		wait = new WebDriverWait(driver, time);
		wait.until(ExpectedConditions.presenceOfElementLocated(By
				.xpath("/html/body/div[4]/div[1]/div[2]/div/div[2]/div/div[4]/div/table/tbody/tr[1]/td[3]/div/a[1]")));
		wait.until(ExpectedConditions.elementToBeClickable(By
				.xpath("/html/body/div[4]/div[1]/div[2]/div/div[2]/div/div[4]/div/table/tbody/tr[1]/td[3]/div/a[1]")));
		boolean confirmacaoDeLogin1 = driver
				.findElement(
						By.xpath("/html/body/div[4]/div[1]/div[2]/div/div[1]/div[1]/div[2]/div/a[2]/span/span/span[1]"))

				.getText().toUpperCase().contains("Tramitações");
		boolean confirmacaoDeLogin2 = driver
				.findElement(
						By.xpath("/html/body/div[4]/div[1]/div[2]/div/div[1]/div[1]/div[2]/div/a[3]/span/span/span[1]"))
				.getText().toUpperCase().contains("Comunicações");

		if (confirmacaoDeLogin1 == true && confirmacaoDeLogin2 == true) {
			return driver
					.findElement(By.xpath(
							"/html/body/div[4]/div[1]/div[2]/div/div[1]/div[1]/div[2]/div/a[2]/span/span/span[1]"))
					.getText();
		} else {
			return "ACESSO NEGADO";
		}

	}

	public String colocarFiltro() throws InterruptedException {
		this.driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS).pageLoadTimeout(30, TimeUnit.SECONDS);
		WebElement setaAparecer = driver.findElement(
				By.xpath("/html/body/div[4]/div[1]/div[2]/div/div[2]/div[1]/div[3]/div/div/div[33]/div/span"));
		setaAparecer.click();
		WebElement seta = driver.findElement(
				By.xpath("/html/body/div[4]/div[1]/div[2]/div/div[2]/div[1]/div[3]/div/div/div[33]/div/div"));
		seta.click();

		WebElement filtro = driver.findElement(By.xpath("/html/body/div[11]/div/div[2]/div/div[6]/a/div[1]"));
		filtro.click();
		WebElement filtroEs = driver.findElement(By.xpath("/html/body/div[13]/div/div[2]"));
		filtroEs.click();
		WebElement filtroSpace = driver
				.findElement(By.xpath("/html/body/div[13]/div/div[2]/div/table/tbody/tr/td[2]/input"));
		filtroSpace.click();
		filtroSpace.sendKeys("SAMIR");
		Thread.sleep(1000);
		long time = 100;
		wait = new WebDriverWait(driver, time);
		wait.until(ExpectedConditions.presenceOfElementLocated(By
				.xpath("/html/body/div[4]/div[1]/div[2]/div/div[2]/div/div[4]/div/table/tbody/tr[1]/td[3]/div/a[1]")));
		wait.until(ExpectedConditions.elementToBeClickable(By
				.xpath("/html/body/div[4]/div[1]/div[2]/div/div[2]/div/div[4]/div/table/tbody/tr[1]/td[3]/div/a[1]")));
		Thread.sleep(1000);

		return "fitro colocado";

	}

	public String entrarNoProcessoAutomatico() throws InterruptedException {
		this.driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS).pageLoadTimeout(30, TimeUnit.SECONDS);
		Thread.sleep(1000);
		String verificacao = driver
				.findElement(By.xpath("/html/body/div[4]/div[1]/div[2]/div/div[2]/div/div[2]/div/div/div[7]"))
				.getText();

		System.out.println(verificacao);
		verificacao = verificacao.replace("Sem registros para exibir", "");
		System.out.println(verificacao);

		boolean confirmacaoDeExistencia = verificacao == "";
		System.out.println(confirmacaoDeExistencia);
		if (confirmacaoDeExistencia == true) {
			return "Sem registros para exibir";
		} else {
			long time = 15000;
			wait = new WebDriverWait(driver, time);
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(
					"/html/body/div[4]/div[1]/div[2]/div/div[2]/div/div[4]/div/table/tbody/tr[1]/td[3]/div/a[1]")));
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
					"/html/body/div[4]/div[1]/div[2]/div/div[2]/div/div[4]/div/table/tbody/tr[1]/td[3]/div/a[1]")));
			boolean confirmacaoDeAtivacao = driver
					.findElement(By.xpath(
							"/html/body/div[4]/div[1]/div[2]/div/div[2]/div[1]/div[4]/div/table/tbody/tr[1]/td[9]/div"))
					.getText().toUpperCase().contains("SAMIR");
			if (confirmacaoDeAtivacao == false) {
				return "Codigo das eiquetas estao errados";
			} else {
				WebElement processo = driver.findElement(By.xpath(
						"/html/body/div[4]/div[1]/div[2]/div/div[2]/div[1]/div[4]/div/table/tbody/tr[1]/td[3]/div/a[1]"));
				processo.click();
				return "pronto para procurar";
			}

		}

	}

	public String procurarProcesso(LoginModelo usuario) {
		this.driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS).pageLoadTimeout(60, TimeUnit.SECONDS);
		WebElement seta1 = driver
				.findElement(By.xpath("/html/body/div[4]/div[1]/div[2]/div/div[2]/div/div[3]/div/div/div[3]/div"));
		seta1.click();
		WebElement seta = driver
				.findElement(By.xpath("/html/body/div[4]/div[1]/div[2]/div/div[2]/div/div[3]/div/div/div[3]/div/div"));
		seta.click();

		WebElement filtro = driver.findElement(By.xpath("/html/body/div[11]/div/div[2]/div/div[6]/a"));
		filtro.click();
		WebElement filtroEs = driver
				.findElement(By.xpath("/html/body/div[13]/div/div[2]/div/table/tbody/tr/td[2]/input"));
		// /html/body/div[13]/div/div[2]/div/table/tbody/tr/td[2]/input
		// /html/body/div[13]/div/div[2]/div/table/tbody/tr/td[2]/input
		filtroEs.click();
		filtroEs.sendKeys(usuario.getProcesso());

		WebElement preocessoElement = driver.findElement(
				By.xpath("/html/body/div[4]/div[1]/div[2]/div/div[2]/div/div[4]/div/table/tbody/tr/td[3]/div/a[1]"));
		preocessoElement.click();

		// WebElement dosPrev = this.driver.findElement(By.cssSelector(" span[id*=DOSSIÊ
		// PREVIDENCIÁRIO]"));
		// dosPrev.click();
		// WebElement colunaNome =
		// linhaDaTabela.findElement(By.cssSelector("td:nth-child(1)"));

		return "pronto para fazer a leitura";

	}

	public boolean dataDeValidacaoDosPrev() throws InterruptedException {
		this.driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS).pageLoadTimeout(30, TimeUnit.SECONDS);
		Thread.sleep(1000);
		List<String> janela = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(janela.get(1));
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("treeview-1015")));
		WebElement TabelaTref = driver.findElement(By.id("treeview-1015"));
		List<WebElement> listaMovimentacao = new ArrayList<WebElement>(TabelaTref.findElements(By.cssSelector("tr")));
		for (int i = listaMovimentacao.size(); i > 2; i--) {

			// Providência Jurídica é o título da movimentação
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//tr[" + i + "]/td[2]/div/span")));
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//tr[" + i + "]/td[2]/div/span")));
			Thread.sleep(500);
			
			Boolean existeDosPrev = driver.findElement(By.xpath("//tr[" + i + "]/td[2]/div/span")).getText()
					.toUpperCase().contains("DOSSIÊ PREVIDENCIÁRIO");
			if (existeDosPrev == true) {
				WebElement dosClick = driver.findElement(By.xpath("//tr[" + i + "]/td[2]/div/span"));
				dosClick.click();
				/*
				 * String campoPassPath =
				 * "/html/body/div[3]/div[1]/div/div/table[1]/tbody/tr/td[2]/input"; WebElement
				 * campoPassElemt = driver.findElement(By.xpath(campoPassPath));
				 * campoPassElemt.click(); campoPassElemt.sendKeys("LIDO"); WebElement
				 * salvarEtiqueta = driver
				 * .findElement(By.xpath("/html/body/div[3]/div[1]/div/div/a/span/span/span[2]")
				 * ); salvarEtiqueta.click();
				 */

				driver.switchTo().frame(0);
				try {
					String dataValiadcaoString = driver.findElement(By.xpath("/html/body/div/p[2]/b")).getText();
					System.out.println(dataValiadcaoString);
					dataValiadcaoString = dataValiadcaoString
							.replace("* Informações extraídas dos sistemas informatizados do INSS em: ", "");
					System.out.println(dataValiadcaoString);

					DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
					formatter = formatter.withLocale(Locale.US);
					// DateTimeFormatter dataForma = DateTimeFormatter.ofPattern("dd/MM/yyyy
					// HH:mm:ss");
					System.out.println("passou1");
					LocalDateTime dataValidacao = LocalDateTime.parse(dataValiadcaoString, formatter);
					System.out.println("passou2");
					System.out.println(dataValidacao);
					// Date data =
					// Date.from(dataValidacao.atZone(ZoneId.systemDefault()).toInstant());
					LocalDateTime dataATUALocalDateTime = LocalDateTime.now();
					System.out.println("data de validacao ano: " + dataValidacao.getYear());
					System.out.println("data atual ano: " + dataATUALocalDateTime.getYear());
					System.out.println("data de validacao: " + dataValidacao.getDayOfYear());
					System.out.println("data atual: " + dataATUALocalDateTime.getDayOfYear());
					System.out.println("data de validacao mes: " + dataValidacao.getMonthValue());
					System.out.println("data atual mes: " + dataATUALocalDateTime.getMonthValue());
					if (dataATUALocalDateTime.getYear() == dataValidacao.getYear()) {
						System.out.println("mesmo ano");
						int x = 0;
						x = dataATUALocalDateTime.getDayOfYear() - dataValidacao.getDayOfYear();
						if (x <= 30) {
							System.out.println("x é menor ou igual a 30" + x);
							return true;
						} else {

							System.out.println("x maior que 30 dias");
							return false;

						}
					} else {
						if (dataATUALocalDateTime.getYear() == dataValidacao.getYear() + 1) {
							if (dataValidacao.getMonthValue() == 12) {

								int x = dataValidacao.getDayOfYear() - 333;
								if (x <= dataATUALocalDateTime.getDayOfYear()) {
									System.out.println("x é menor ou igual a 30" + x);
									return true;
								} else {

									System.out.println("x maior que 30 dias");
									return false;

								}
							}
						} else {
							System.out.println("ano diferente");
							return false;
						}

					}

				} catch (Exception e) {
					System.out.println("Vish entrei no tal do catch 2");
					// dataAjuizamento =
					// driver.findElement(By.xpath("/html/body/div/div[5]/table/tbody/tr[3]/td[2]")).getText();
				}

				return false;
			}
		}

		return false;
	}

	public InfomacoesDosPrev procurarDosPrev() {
                InfomacoesDosPrev informacao = new InfomacoesDosPrev();
		this.driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS).pageLoadTimeout(30, TimeUnit.SECONDS);
		List<String> janela = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(janela.get(1));
		WebElement TabelaTref = driver.findElement(By.id("treeview-1015"));
		List<WebElement> listaMovimentacao = new ArrayList<WebElement>(TabelaTref.findElements(By.cssSelector("tr")));
		for (int i = listaMovimentacao.size(); i > 2; i--) {

			// Providência Jurídica é o título da movimentação
			Boolean existeDosPrev = driver.findElement(By.xpath("//tr[" + i + "]/td[2]/div/span")).getText()
					.toUpperCase().contains("DOSSIÊ PREVIDENCIÁRIO");
			if (existeDosPrev == true) {
				WebElement dosClick = driver.findElement(By.xpath("//tr[" + i + "]/td[2]/div/span"));
				dosClick.click();

				driver.switchTo().frame(0);
				try {
					String dataValiadcaoString = driver.findElement(By.xpath("/html/body/div/p[2]/b")).getText();
					System.out.println(dataValiadcaoString);
					dataValiadcaoString = dataValiadcaoString
							.replace("* Informações extraídas dos sistemas informatizados do INSS em: ", "");
					System.out.println(dataValiadcaoString);

					String dataAjuizamento = driver.findElement(By.xpath("/html/body/div/div[1]/table/tbody/tr[2]/td"))
							.getText();
					informacao.setDataAjuizamento(dataAjuizamento);
					String dib = driver.findElement(By.xpath("/html/body/div/div[3]/table/tbody/tr[2]/td[4]"))
							.getText();
					informacao.setDibInicial(dib);
					System.out.println("DIB: " + dib);
					String elementoData = driver.findElement(By.xpath("/html/body/div/p[2]/b")).getText();
					System.out.println("Elemento Data: " + elementoData);

				} catch (Exception e) {
					System.out.println("Vish entrei no tal do catch 2");
					// dataAjuizamento =
					// driver.findElement(By.xpath("/html/body/div/div[5]/table/tbody/tr[3]/td[2]")).getText();
				}

				return informacao;
			}
		}

		return null;

	}

	public void etiquetar(Boolean validacao, String beneficio, int i) {
		List<String> janela = new ArrayList<String>(driver.getWindowHandles());
		this.driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS).pageLoadTimeout(30, TimeUnit.SECONDS);
		driver.switchTo().window(janela.get(1));

		String campoPassPath = "/html/body/div[3]/div[1]/div/div/table[1]/tbody/tr/td[2]/input";
		WebElement campoPassElemt = driver.findElement(By.xpath(campoPassPath));
		campoPassElemt.click();
		if (i == 1){
			if (validacao == true) {
				campoPassElemt.sendKeys("ATIVO " + beneficio);
			} else {
				campoPassElemt.sendKeys("INDEFERIDO OU CESSADO");
			}
		}
		else if(i ==0){
			if (validacao == true) {
				campoPassElemt.sendKeys("LIDO " + beneficio);
			} else {
				campoPassElemt.sendKeys("INVALIDO DOSPREV" + beneficio);
			}
		}
		

		WebElement salvarEtiqueta = driver
				.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/a/span/span/span[2]"));
		salvarEtiqueta.click();

		driver.switchTo().window(janela.get(1)).close();
		driver.switchTo().window(janela.get(0));
		WebElement filtroSpace = driver.findElement(
				By.xpath("/html/body/div[4]/div[1]/div[2]/div/div[2]/div/div[2]/div/div/a[5]/span/span/span[2]"));
		filtroSpace.click();
	}

	public Ativo verificacaoDeAtivo() {
		this.driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS).pageLoadTimeout(1, TimeUnit.SECONDS);
		List<String> janela = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(janela.get(1));
		Ativo ativo = new Ativo();
		String beneficio = null;
		boolean verificarAtivo = false;
		driver.switchTo().frame(0);
		for (int j = 2; j < 100; j++) {
			try {
				verificarAtivo = driver.findElement(By.xpath("/html/body/div/div[3]/table/tbody/tr[" + j + "]/td[6]"))
						.getText().toUpperCase().contains("ATIVO");
				if (verificarAtivo) {
					beneficio = driver.findElement(By.xpath("/html/body/div/div[3]/table/tbody/tr[" + j + "]/td[2]"))
							.getText();
					System.out.println(beneficio);
					j = 100;
				}
			} catch (Exception e) {
				System.out.println("Entrei no Catch");
				break;
				
			}
		}
		ativo.setAtivo(verificarAtivo);
		ativo.setBeneficio(beneficio);
		return ativo;
	}

	public void quit() {
		driver.quit();
	}
}

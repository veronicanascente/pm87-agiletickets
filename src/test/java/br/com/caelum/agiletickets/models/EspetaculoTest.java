package br.com.caelum.agiletickets.models;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;

import junit.framework.Assert;

import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.joda.time.LocalTime;
import org.junit.Test;

public class EspetaculoTest {

	@Test
	public void deveInformarSeEhPossivelReservarAQuantidadeDeIngressosDentroDeQualquerDasSessoes() {
		Espetaculo ivete = new Espetaculo();

		ivete.getSessoes().add(sessaoComIngressosSobrando(1));
		ivete.getSessoes().add(sessaoComIngressosSobrando(3));
		ivete.getSessoes().add(sessaoComIngressosSobrando(2));

		assertTrue(ivete.Vagas(5));
	}

	@Test
	public void deveInformarSeEhPossivelReservarAQuantidadeExataDeIngressosDentroDeQualquerDasSessoes() {
		Espetaculo ivete = new Espetaculo();

		ivete.getSessoes().add(sessaoComIngressosSobrando(1));
		ivete.getSessoes().add(sessaoComIngressosSobrando(3));
		ivete.getSessoes().add(sessaoComIngressosSobrando(2));

		assertTrue(ivete.Vagas(6));
	}

	@Test
	public void DeveInformarSeNaoEhPossivelReservarAQuantidadeDeIngressosDentroDeQualquerDasSessoes() {
		Espetaculo ivete = new Espetaculo();

		ivete.getSessoes().add(sessaoComIngressosSobrando(1));
		ivete.getSessoes().add(sessaoComIngressosSobrando(3));
		ivete.getSessoes().add(sessaoComIngressosSobrando(2));

		assertFalse(ivete.Vagas(15));
	}

	@Test
	public void DeveInformarSeEhPossivelReservarAQuantidadeDeIngressosDentroDeQualquerDasSessoesComUmMinimoPorSessao() {
		Espetaculo ivete = new Espetaculo();

		ivete.getSessoes().add(sessaoComIngressosSobrando(3));
		ivete.getSessoes().add(sessaoComIngressosSobrando(3));
		ivete.getSessoes().add(sessaoComIngressosSobrando(4));

		assertTrue(ivete.Vagas(5, 3));
	}

	@Test
	public void DeveInformarSeEhPossivelReservarAQuantidadeExataDeIngressosDentroDeQualquerDasSessoesComUmMinimoPorSessao() {
		Espetaculo ivete = new Espetaculo();

		ivete.getSessoes().add(sessaoComIngressosSobrando(3));
		ivete.getSessoes().add(sessaoComIngressosSobrando(3));
		ivete.getSessoes().add(sessaoComIngressosSobrando(4));

		assertTrue(ivete.Vagas(10, 3));
	}

	@Test
	public void DeveInformarSeNaoEhPossivelReservarAQuantidadeDeIngressosDentroDeQualquerDasSessoesComUmMinimoPorSessao() {
		Espetaculo ivete = new Espetaculo();

		ivete.getSessoes().add(sessaoComIngressosSobrando(2));
		ivete.getSessoes().add(sessaoComIngressosSobrando(2));
		ivete.getSessoes().add(sessaoComIngressosSobrando(2));

		assertFalse(ivete.Vagas(5, 3));
	}

	private Sessao sessaoComIngressosSobrando(int quantidade) {
		Sessao sessao = new Sessao();
		sessao.setTotalIngressos(quantidade * 2);
		sessao.setIngressosReservados(quantidade);

		return sessao;
	}
	
	@Test
	public void deveCriarUmaSessaoQuandoDatasDeInicioEFimForemIguaisEPeriodicidadeDiaria(){
		LocalDate inicio = new LocalDate();
		LocalDate fim = new LocalDate();
		Periodicidade periodicidade = Periodicidade.DIARIA;
		LocalTime horario = new LocalTime();
		Espetaculo show = new Espetaculo();
		List<Sessao> lstSessao = show.criaSessoes(inicio, fim, horario, periodicidade);
		
		DateTime dataInicio = inicio.toDateTime(horario);
		
		Assert.assertEquals(1, lstSessao.size());
		Assert.assertEquals(show, lstSessao.get(0).getEspetaculo());
		Assert.assertEquals(dataInicio, lstSessao.get(0).getInicio());
	}
	
	@Test
	public void deveCriarUmaSessaoQuandoDatasDeInicioEFimForemIguaisEPeriodicidadeSemanal(){
		LocalDate inicio = new LocalDate();
		LocalDate fim = new LocalDate();
		Periodicidade periodicidade = Periodicidade.SEMANAL;
		LocalTime horario = new LocalTime();
		Espetaculo show = new Espetaculo();
		List<Sessao> lstSessao = show.criaSessoes(inicio, fim, horario, periodicidade);
		
		DateTime dataInicio = inicio.toDateTime(horario);
		
		Assert.assertEquals(1, lstSessao.size());
		Assert.assertEquals(show, lstSessao.get(0).getEspetaculo());
		Assert.assertEquals(dataInicio, lstSessao.get(0).getInicio());
	}
	
	@Test
	public void deveCriarCincoSessoesParaEspetaculoComIntervaloDeCincoDiasEPeriodiciodadeDiaria(){
		LocalDate inicio = new LocalDate();
		LocalDate fim = inicio.plusDays(4);
		Periodicidade periodicidade = Periodicidade.DIARIA;
		LocalTime horario = new LocalTime();
		Espetaculo show = new Espetaculo();
		List<Sessao> lstSessao = show.criaSessoes(inicio, fim, horario, periodicidade);
				
		Assert.assertEquals(32, lstSessao.size());
		
		DateTime dataInicio = inicio.toDateTime(horario);
		for(Sessao sessao : lstSessao){
			Assert.assertEquals(show, sessao.getEspetaculo());
			Assert.assertEquals(dataInicio, sessao.getInicio());
			dataInicio = dataInicio.plusDays(1);
		}	
	}
	
	@Test
	public void deveCriarQuatroSessoesParaEspetaculoComIntervaloDeQuatroSemanasEPeriodiciodadeSemanal(){
		LocalDate inicio = new LocalDate();
		LocalDate fim = inicio.plusWeeks(3);
		Periodicidade periodicidade = Periodicidade.SEMANAL;
		LocalTime horario = new LocalTime();
		Espetaculo show = new Espetaculo();
		List<Sessao> lstSessao = show.criaSessoes(inicio, fim, horario, periodicidade);
				
		Assert.assertEquals(5, lstSessao.size());
		
		DateTime dataInicio = inicio.toDateTime(horario);
		for(Sessao sessao : lstSessao){
			Assert.assertEquals(show, sessao.getEspetaculo());
			Assert.assertEquals(dataInicio, sessao.getInicio());
			dataInicio = dataInicio.plusWeeks(1);
		}
	}
	
}

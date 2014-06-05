package br.com.caelum.agiletickets.models;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.LocalDate;
import org.joda.time.LocalTime;
import org.joda.time.Weeks;

public class CriadorSessaoSemanal implements CriadorDeSessoes {

	@Override
	public List<Sessao> cria(LocalDate inicio, LocalDate fim,
			LocalTime horario, Espetaculo espetaculo) {
		int semanas = Weeks.weeksBetween(inicio, fim).getWeeks();
		semanas++;
		
		List<Sessao> lstSessao = new ArrayList<Sessao>();
		for(int i = 0; i < semanas; i++){
			Sessao sessao = new Sessao();
			sessao.setEspetaculo(espetaculo);
			sessao.setInicio(inicio.plusWeeks(i).toDateTime(horario));
			
			lstSessao.add(sessao);
		}
		
		return lstSessao;
	}

}

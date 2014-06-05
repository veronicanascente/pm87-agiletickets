package br.com.caelum.agiletickets.models;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.Days;
import org.joda.time.LocalDate;
import org.joda.time.LocalTime;

public class CriadorSessaoDiaria implements CriadorDeSessoes {

	@Override
	public List<Sessao> cria(LocalDate inicio, LocalDate fim, LocalTime horario, Espetaculo espetaculo) {
		int dias = Days.daysBetween(inicio, fim).getDays();
		dias++;
		
		List<Sessao> lstSessao = new ArrayList<Sessao>();
		for(int i = 0; i < dias; i++){
			Sessao sessao = new Sessao();
			sessao.setEspetaculo(espetaculo);
			sessao.setInicio(inicio.plusDays(i).toDateTime(horario));
			
			lstSessao.add(sessao);
		}
		
		return lstSessao;
	}

}

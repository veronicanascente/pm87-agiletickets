package br.com.caelum.agiletickets.domain.precos;

import java.math.BigDecimal;

import br.com.caelum.agiletickets.models.Sessao;
import br.com.caelum.agiletickets.models.TipoDeEspetaculo;

public class CalculadoraDePrecos {

	public static BigDecimal calcula(Sessao sessao, Integer quantidade) {
		BigDecimal preco;

		if (tipoDeEspetaculoEhCinemaOuShow(sessao)) {
			// quando estiver acabando os ingressos...
			if (ultimosIngressosDisponiveis(sessao, 0.05)) {
				preco = sessao.getPreco().add(
						sessao.getPreco().multiply(BigDecimal.valueOf(0.10)));
			} else {
				preco = sessao.getPreco();
			}
		} else if (sessao.getEspetaculo().getTipo()
				.equals(TipoDeEspetaculo.BALLET)) {
			if (ultimosIngressosDisponiveis(sessao, 0.50)) {
				preco = sessao.getPreco().add(
						sessao.getPreco().multiply(BigDecimal.valueOf(0.20)));
			} else {
				preco = sessao.getPreco();
			}

			if (sessao.getDuracaoEmMinutos() > 60) {
				preco = preco.add(sessao.getPreco().multiply(
						BigDecimal.valueOf(0.10)));
			}
		} else if (sessao.getEspetaculo().getTipo()
				.equals(TipoDeEspetaculo.ORQUESTRA)) {
			if (ultimosIngressosDisponiveis(sessao, 0.50)) {
				preco = sessao.getPreco().add(
						sessao.getPreco().multiply(BigDecimal.valueOf(0.20)));
			} else {
				preco = sessao.getPreco();
			}

			if (sessao.getDuracaoEmMinutos() > 60) {
				preco = preco.add(sessao.getPreco().multiply(
						BigDecimal.valueOf(0.10)));
			}
		} else {
			// nao aplica aumento para teatro (quem vai é pobretão)
			preco = sessao.getPreco();
		}

		return preco.multiply(BigDecimal.valueOf(quantidade));
	}

	public static boolean ultimosIngressosDisponiveis(Sessao sessao,
			Double percentualLimiteParaUltimosIngressos) {
		Double percentualRestanteDeIngressos = (sessao.getTotalIngressos() - sessao
				.getIngressosReservados())
				/ sessao.getTotalIngressos().doubleValue();
		return percentualRestanteDeIngressos <= percentualLimiteParaUltimosIngressos;
	}

	private static boolean tipoDeEspetaculoEhCinemaOuShow(Sessao sessao) {

		return sessao.getEspetaculo().getTipo().equals(TipoDeEspetaculo.CINEMA)
				|| sessao.getEspetaculo().getTipo()
						.equals(TipoDeEspetaculo.SHOW);
	}
}

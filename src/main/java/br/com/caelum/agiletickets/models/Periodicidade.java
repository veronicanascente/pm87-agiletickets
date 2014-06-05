package br.com.caelum.agiletickets.models;



public enum Periodicidade {
	
	DIARIA {
		@Override
		public CriadorDeSessoes criaSessoes() {
			return new CriadorSessaoDiaria();
		}
	}, SEMANAL {
		@Override
		public CriadorDeSessoes criaSessoes() {
			return new CriadorSessaoSemanal();
		}
	};
	
	public abstract CriadorDeSessoes criaSessoes();
	
}

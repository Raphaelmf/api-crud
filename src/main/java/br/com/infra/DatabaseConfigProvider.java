package br.com.infra;

import com.datastax.driver.core.Session;

public interface DatabaseConfigProvider {
	void InicializerCluster();
	Session getSession();
}

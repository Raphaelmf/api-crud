package br.com.infra;

import com.datastax.driver.core.Session;

public interface DatabaseConfigProvider {

	Session getSession();
}

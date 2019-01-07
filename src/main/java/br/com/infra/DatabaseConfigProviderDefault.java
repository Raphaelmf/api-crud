package br.com.infra;

import java.util.Objects;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.HostDistance;
import com.datastax.driver.core.PoolingOptions;
import com.datastax.driver.core.Session;
import com.datastax.driver.core.policies.DCAwareRoundRobinPolicy;
import com.datastax.driver.core.policies.TokenAwarePolicy;
import com.google.common.util.concurrent.MoreExecutors;
import com.google.inject.Singleton;

@Singleton
public class DatabaseConfigProviderDefault implements DatabaseConfigProvider {

	private Cluster cluster;
	private Session session;

	private void InicializerCluster() {

		try {

			cluster = Cluster.builder().addContactPoints("127.0.0.1").withPort(9042)
					.withPoolingOptions(new PoolingOptions().setConnectionsPerHost(HostDistance.LOCAL, 1, 30)
							.setInitializationExecutor(MoreExecutors.directExecutor()))
					.withLoadBalancingPolicy(new TokenAwarePolicy(DCAwareRoundRobinPolicy.builder().build())).build();

			session = cluster.connect();
			System.out.println("Cassandra Conectado");
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Não conectou " + e);
		}

	}

	@Override
	public Session getSession() {
		if (Objects.isNull(session)) {
			InicializerCluster();
		}
		return session;
	}

	public void close() {
		session.close();
		cluster.close();
	}
}

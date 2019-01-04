package br.com.infra;

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

	@Override
	public void InicializerCluster() {
		System.out.println("Entrou no connect");
		try {
			System.out.println("Entrou no try");
			cluster = Cluster.builder().addContactPoints("localhost")
					.withPort(9042).build();
					/*
					.withPoolingOptions(new PoolingOptions().setConnectionsPerHost(HostDistance.LOCAL, 1, 30)
							.setInitializationExecutor(MoreExecutors.directExecutor()))
					.withLoadBalancingPolicy(new TokenAwarePolicy(DCAwareRoundRobinPolicy.builder().build())).build();
*/
			System.out.println(cluster);
			connect();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Não conectou " + e);
		}

		System.out.println("Cassandra Conectado");
	}
	public void connect() {
		this.session = cluster.connect();
	}

	@Override
	public Session getSession() {
		System.out.println("entro no getsession()");
		try {
			InicializerCluster();
		}catch (Exception e) {
			// TODO: handle exception
			System.out.println("Não conectou entro da session " + e);
		}
		
		return session;
	}

	public void close() {
		session.close();
		cluster.close();
	}
}

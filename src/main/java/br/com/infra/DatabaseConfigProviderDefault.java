package br.com.infra;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Cluster.Builder;
import com.datastax.driver.core.Host;
import com.datastax.driver.core.HostDistance;
import com.datastax.driver.core.Metadata;
import com.datastax.driver.core.PoolingOptions;
import com.datastax.driver.core.Session;
import com.datastax.driver.core.policies.DCAwareRoundRobinPolicy;
import com.datastax.driver.core.policies.TokenAwarePolicy;
import com.google.common.util.concurrent.MoreExecutors;

public class DatabaseConfigProviderDefault implements DatabaseConfigProvider {

	private Cluster cluster;
	private Session session;

	@Override
	public void connect() {

		cluster = Cluster.builder().withCredentials("cassandra", "cassandra")
								    .addContactPoints("localhost").withPort(9042)
								    .withPoolingOptions(new PoolingOptions().setConnectionsPerHost(HostDistance.LOCAL, 1, 30)
								    										.setInitializationExecutor(MoreExecutors.directExecutor()))
								    .withLoadBalancingPolicy(new TokenAwarePolicy(DCAwareRoundRobinPolicy.builder().build())).build();
		session = cluster.connect();
	}

	public Session getSession() {
		return this.session;
	}

	public void close() {
		session.close();
		cluster.close();
	}
}

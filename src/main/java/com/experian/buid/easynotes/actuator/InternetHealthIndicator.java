package com.experian.buid.easynotes.actuator;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

@Component
public class InternetHealthIndicator implements HealthIndicator {
	
	private static final Log logger = LogFactory.getLog(InternetHealthIndicator.class);

	@Override
	public Health health() {
		if (isHostAvailable("google.com")) {
			return Health.up().build();
		} else {
			return Health.down().build();
		}
	}

	private boolean isHostAvailable(String hostName) {
		try (Socket socket = new Socket()) {
			int port = 443;
			InetSocketAddress socketAddress = new InetSocketAddress(hostName, port);
			socket.connect(socketAddress, 500);
			return true;
		} catch (IOException e) {
			if(logger.isWarnEnabled()) {
				logger.warn("Internet is down", e);
			}
			return false;
		}
	}
}

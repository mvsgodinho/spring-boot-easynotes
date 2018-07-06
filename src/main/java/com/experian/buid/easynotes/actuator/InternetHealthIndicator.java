package com.experian.buid.easynotes.actuator;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

@Component
public class InternetHealthIndicator implements HealthIndicator {

	@Override
	public Health health() {
		try {
			if (isHostAvailable("www.google.com")) {
				return Health.up().build();
			}
		} catch (IOException e) {
			System.out.println(e);
			return Health.down(e).build();
		}
		return Health.down().outOfService().build();
	}

	private boolean isHostAvailable(String hostName) throws IOException {
		try (Socket socket = new Socket()) {
			int port = 80;
			InetSocketAddress socketAddress = new InetSocketAddress(hostName, port);
			socket.connect(socketAddress, 500);
			return true;
		} catch (UnknownHostException unknownHost) {
			return false;
		}
	}
}

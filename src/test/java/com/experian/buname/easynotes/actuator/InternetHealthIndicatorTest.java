package com.experian.buname.easynotes.actuator;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.Status;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import com.experian.buname.easynotes.actuator.InternetHealthIndicator;

@RunWith(SpringRunner.class)
public class InternetHealthIndicatorTest {

	@TestConfiguration
	@PropertySource({"classpath:application.properties" ,"classpath:application-test.properties"})
    static class TestConfig {
  
        @Bean
        public InternetHealthIndicator internetHealthIndicator() {
            return new InternetHealthIndicator();
        }
    }
	
	@Autowired
	private InternetHealthIndicator internetHealthIndicator;
	
	@Test
	public void healthUpTest() {
		internetHealthIndicator.hostName = "google.com";
		Health health = internetHealthIndicator.health();
		assertThat(health.getStatus()).isEqualTo(Status.UP);
		assertThat(health.getDetails()).containsKey("ping");
		int ping = (int) health.getDetails().get("ping");
		assertThat(ping).isBetween(0, internetHealthIndicator.timeout);
	}
	
	@Test
	public void healthDownTest() {
		internetHealthIndicator.hostName = "doesntexists.xyz";
		Health health = internetHealthIndicator.health();
		assertThat(health.getStatus()).isEqualTo(Status.DOWN);
	}
}

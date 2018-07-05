package com.experian.buid.easynotes.config;

import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.boot.web.servlet.server.ConfigurableServletWebServerFactory;
import org.springframework.context.annotation.Configuration;

import com.experian.buid.easynotes.AppConstants;

/**
 * @author Marcos Godinho
 *
 */
@Configuration
public class WebServerConfig implements WebServerFactoryCustomizer<ConfigurableServletWebServerFactory> {

	@Override
	public void customize(ConfigurableServletWebServerFactory factory) {
		factory.setContextPath(AppConstants.Api.CONTEXT_PATH);
	}
}
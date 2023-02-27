package com.utb.autoevaluacion.saml.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.saml.provider.SamlServerConfiguration;

@ConfigurationProperties(prefix = "spring.security.saml2")
@Configuration
public class SamlAppConfig extends SamlServerConfiguration {
}

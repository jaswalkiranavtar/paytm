package com.paytm;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@SpringBootApplication
@EnableAspectJAutoProxy
@EnableOAuth2Sso
public class UIApplication extends WebSecurityConfigurerAdapter {

	public static void main(String[] args) {
		new SpringApplicationBuilder(UIApplication.class).run(args);
	}
	
	@Override
    public void configure(HttpSecurity http) throws Exception {
        //allow files to be embedded in frames, this enables the ui to work
        http.headers().frameOptions().disable();
        http.csrf().disable();
        super.configure(http);
    }

}

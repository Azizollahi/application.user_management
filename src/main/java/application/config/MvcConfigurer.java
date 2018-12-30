package application.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.resource.PathResourceResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
public class MvcConfigurer implements WebMvcConfigurer {

	private HandlerInterceptor tokenAuthorizer;

	@Autowired
	public MvcConfigurer(@Qualifier("createTokenAuthoriztionInceptor") HandlerInterceptor tokenAuthorizationInterceptor){
		this.tokenAuthorizer = tokenAuthorizationInterceptor;
	}

	@Bean
	public ViewResolver getViewResolver() {
		InternalResourceViewResolver resolver
				= new InternalResourceViewResolver();
		resolver.setPrefix("/WEB-INF/JSP/");
		resolver.setSuffix(".jsp");
		return resolver;
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry){
		registry.addInterceptor(tokenAuthorizer);
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/resources/**")
				.addResourceLocations("/resources/").setCachePeriod(3600)
				.resourceChain(true).addResolver(new PathResourceResolver());
	}
	@Override
	public void configureDefaultServletHandling(
			DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}
}

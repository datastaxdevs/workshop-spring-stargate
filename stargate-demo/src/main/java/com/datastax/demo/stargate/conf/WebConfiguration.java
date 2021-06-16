package com.datastax.demo.stargate.conf;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;

import com.datastax.astra.sdk.AstraClient;

@Configuration
public class WebConfiguration implements WebMvcConfigurer {

	@Autowired
	private AstraClient astraClient;

	
	
	@Override
	public void addInterceptors(InterceptorRegistry interceptorRegistry) {
		interceptorRegistry.addInterceptor(localeChangeInterceptor());
		interceptorRegistry.addInterceptor(exposeNamespaceInterceptor());
	}

	@Bean
	public LocaleResolver localeResolver() {
		CookieLocaleResolver localeResolver = new CookieLocaleResolver();
		localeResolver.setDefaultLocale(Locale.ENGLISH);
		return localeResolver;
	}

	@Bean
	public LocaleChangeInterceptor localeChangeInterceptor() {
		LocaleChangeInterceptor localeChangeInterceptor = new LocaleChangeInterceptor();
		localeChangeInterceptor.setParamName("lang");
		return localeChangeInterceptor;
	}

	@Bean
	public HandlerInterceptor exposeNamespaceInterceptor() {
		return new HandlerInterceptor() {
			public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
					ModelAndView modelAndView) throws Exception {
				astraClient.cqlSession().getKeyspace().ifPresent(namespace -> {
					if (modelAndView != null) {
						modelAndView.addObject("namespace", namespace.asInternal());
					}
				});
			}
		};
	}

	@Bean
    public SimpleMappingExceptionResolver exposeExtraErrorInfoExceptionResolver() {
		return new SimpleMappingExceptionResolver() {
		   	 @Override
			  protected ModelAndView doResolveException(HttpServletRequest req,
			        HttpServletResponse resp, Object handler, Exception ex) {

			    req.setAttribute("msgType", "error");
			    req.setAttribute("msgInfo", ex.getMessage());
			    return super.doResolveException(req, resp, handler, ex);
			  }
		};
    }
}

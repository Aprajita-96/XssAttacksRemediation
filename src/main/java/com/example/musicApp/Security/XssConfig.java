package com.example.musicApp.Security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.List;

@Configuration
class XssConfig implements WebMvcConfigurer {
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.csrf().disable()
//                .headers()
//                .contentTypeOptions();
//               http.headers()
//                .addHeaderWriter(new StaticHeadersWriter("X-Content-Security-Policy", "script-src 'self'"));
//        http.headers().xssProtection().block(true);
////    http.headers().contentSecurityPolicy("")
//    }
    @Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.add(jsonConverter());
    }
    @Bean
    public HttpMessageConverter<?> jsonConverter() {
        SimpleModule module = new SimpleModule();
        module.addDeserializer(String.class, new DefaultJsonDeserializer());
        ObjectMapper objectMapper = Jackson2ObjectMapperBuilder.json().build();
        objectMapper.registerModule(module);
        return new MappingJackson2HttpMessageConverter(objectMapper);
    }

}


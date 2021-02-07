package com.pspro.webservice;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.config.annotation.WsConfigurerAdapter;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;
 
/**
 * La clase Config.
 */
@EnableWs
@Configuration
public class Config extends WsConfigurerAdapter 
{
    
    /**
     * Message dispatcher servlet.
     *
     * @param applicationContext the application context
     * @return the servlet registration bean
     */
    @Bean
    public ServletRegistrationBean messageDispatcherServlet(ApplicationContext applicationContext) 
    {
        MessageDispatcherServlet servlet = new MessageDispatcherServlet();
        servlet.setApplicationContext(applicationContext);
        servlet.setTransformWsdlLocations(true);
        return new ServletRegistrationBean(servlet, "/service/*");
    }
 
    /**
     * Default wsdl 11 definition.
     *
     * @param countriesSchema the countries schema
     * @return the default wsdl 11 definition
     */
    @Bean(name = "concesionarioDetailsWsdl")
    public DefaultWsdl11Definition defaultWsdl11Definition(XsdSchema countriesSchema) 
    {
        DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
        wsdl11Definition.setPortTypeName("ConcesionarioDetailsPort");
        wsdl11Definition.setLocationUri("/service/concesionario-details");
        wsdl11Definition.setTargetNamespace("http://www.pspro.com/xml/concesionario");
        wsdl11Definition.setSchema(countriesSchema);
        return wsdl11Definition;
    }
 
    /**
     * Countries schema.
     *
     * @return the xsd schema
     */
    @Bean
    public XsdSchema countriesSchema() 
    {
        return new SimpleXsdSchema(new ClassPathResource("concesionario.xsd"));
    }
}
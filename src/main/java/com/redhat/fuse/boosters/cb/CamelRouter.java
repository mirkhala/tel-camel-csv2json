package com.redhat.fuse.boosters.cb;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.dataformat.bindy.csv.BindyCsvDataFormat;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.apache.camel.model.rest.RestBindingMode;
import org.apache.camel.spi.DataFormat;
import org.springframework.stereotype.Component;

@Component
public class CamelRouter extends RouteBuilder {
	
	DataFormat bindy = new BindyCsvDataFormat(Servicio.class);

/*
    @Override
    public void configure() throws Exception {

        from("file:/tmp?fileName=file.csv")
        	.streamCaching()
            .log("OPA: ${body}")
             .unmarshal(bindy)s
             .marshal()
             .json(JsonLibrary.Jackson).log("${body}")
             .setHeader(Exchange.HTTP_METHOD, constant("POST"))
             .to("http4://localhost:8082/camel/hello")
             .to("file:/tmp/?fileName=file-in-json.json");

         restConfiguration()
             .component("servlet")
             .bindingMode(RestBindingMode.json);
        
         rest("/hello").description("Greetings REST service")
             .consumes("application/json")
             .produces("application/json")
             
             .post().outType(String.class)
                 .responseMessage().code(200).endResponseMessage()
                 .to("direct:impl");

         from("direct:impl").description("REST service implementation route")
         	.log("Body -> ${body}");
    }
*/

@Override
public void configure() throws Exception {

    String kieServerUrl = "localhost:8080/kie-server/services/rest/server";
    String containerId = "puntoSencillo";
    String processId = "puntoSencillo.PuntoSencilloBusinessProcess";

    from("file:/tmp?fileName=file.csv").streamCaching()
        .unmarshal(bindy)
        .log("${body}")
        .process(new Processor(){
                @Override
                public void process(Exchange exchange) throws Exception {
                    Servicio servicio = exchange.getIn().getBody(Servicio.class);
                    ServicioWrapper pw = new ServicioWrapper(servicio);
                    
                    exchange.getIn().setBody(pw);
                }           
        })
        .marshal()
        .json(JsonLibrary.Jackson, true).log("${body}")
        //.setHeader(Exchange.HTTP_QUERY, constant("authMethod=Basic"))
        //.setHeader(Exchange.HTTP_QUERY, constant("authUsername=kieserver"))
        //.setHeader(Exchange.HTTP_QUERY, constant("authPassword=kieserver1!"))
        .setHeader(Exchange.CONTENT_TYPE, constant("application/json"))
        .setHeader(Exchange.HTTP_METHOD, constant("POST"))
        .to("http4://"+kieServerUrl +"/containers/"+ containerId + "/processes/"+ processId +"/instances?authMethod=Basic&authUsername=kieserver&authPassword=kieserver1!&authenticationPreemptive=true");
    
    }
}
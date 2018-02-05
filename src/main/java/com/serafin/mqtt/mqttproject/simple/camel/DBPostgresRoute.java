//package com.serafin.mqtt.mqttproject.simple.camel;
//
//import org.apache.camel.CamelContext;
//import org.apache.camel.builder.RouteBuilder;
//import org.apache.camel.impl.DefaultCamelContext;
//
//public class DBPostgresRoute {
//
//    public void routeStart(String topic) {
//
//        CamelContext context = new DefaultCamelContext();
//
//        try {
//            context.addRoutes(new RouteBuilder() {
//                @Override
//                public void configure() throws Exception {
//                    from("file:data/" + topic + ".txt")
//                            .to("log:?level=INFO&showBody=true")
//                            .process(new InsertProcessor())
//                            .to("jdbc:myDataSource")
//                            .to("sql:select * from messages?dataSource=myDataSource")
//                            .to("log:?level=INFO&showBody=true");
//                }
//            });
//
//            context.start();
//            Thread.sleep(5000);
//            context.stop();
//
//        } catch (Exception e) {
//            System.out.println("Inside Exception " + e);
//        }
//    }
//}

//package com.sinisiro.StartBoot.start.controller;
//
//import com.rabbitmq.client.*;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.eclipse.paho.client.mqttv3.MqttClient;
//import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
//import org.eclipse.paho.client.mqttv3.MqttMessage;
//import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.messaging.simp.SimpMessagingTemplate;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.ResponseBody;
//import org.springframework.web.servlet.ModelAndView;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.nio.charset.StandardCharsets;
//import java.time.LocalDate;
//import java.time.LocalTime;
//import java.time.format.DateTimeFormatter;
//import java.util.Map;
//
//@Controller
//@RequiredArgsConstructor
//@Slf4j
//@RequestMapping("/mq")
//public class MqController {
//
//    private final static String QUEUE_NAME = "hello";
//    private static final String EXCHANGE_NAME = "topic_logs";
//
//    @Autowired
//    private SimpMessagingTemplate messagingTemplate;
//
//
//
//    @RequestMapping(value = "/init", method = RequestMethod.GET)
//    public String index(ModelAndView mav, HttpServletRequest req, HttpServletResponse res) {
//
//        // 현재 시간
//        LocalTime now = LocalTime.now();
//        // 포맷 정의하기
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH시 mm분 ss초");
//        // 포맷 적용하기
//        String formatedNow = now.format(formatter);
//
//
//        log.info("=[MQ 앱이 시작되었습니다.]= [" + LocalDate.now() + " " + formatedNow + "]");
//        return "mq/init";
//    }
//
//    @RequestMapping(value = "/publish", method = RequestMethod.POST)
//    public @ResponseBody
//    String
//    publishMessage(@RequestBody Map requestMap) throws Exception {
//
//        String reqVal = (String) requestMap.get("val");
//
//        ConnectionFactory factory = new ConnectionFactory();
//        factory.setHost("localhost");
//        try (Connection connection = factory.newConnection();
//             Channel channel = connection.createChannel()) {
//            channel.queueDeclare(QUEUE_NAME, false, false, false, null);
//            String message = reqVal;
//            channel.basicPublish("", QUEUE_NAME, null, message.getBytes(StandardCharsets.UTF_8));
//            System.out.println(" [x] Sent '" + message + "'");
//        }
//        return "publish";
//    }
//
//
//    @RequestMapping(value = "/publishToken", method = RequestMethod.POST)
//    public @ResponseBody
//    String
//    publishTokenMessage(@RequestBody Map requestMap) throws Exception {
//
//        String topic = (String) requestMap.get("topic");
//        String messageTopic = (String) requestMap.get("messageTopic");
////        System.out.println(topic);
////        System.out.println(messageTopic);
//
//        ConnectionFactory factory = new ConnectionFactory();
//        factory.setHost("localhost");
//        try (Connection connection = factory.newConnection();
//             Channel channel = connection.createChannel()) {
//
//            channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.TOPIC);
//
//            String routingKey = topic;
//            String message = messageTopic;
//
////            System.out.println(routingKey);
////            System.out.println(message);
//
//
//            channel.basicPublish(EXCHANGE_NAME, routingKey, null, message.getBytes("UTF-8"));
//            System.out.println(" [x] Sent '" + routingKey + "':'" + message + "'");
//
//            return "publish";
//        }
//
//    }
//
//    @RequestMapping(value = "/receiveTopic", method = RequestMethod.POST)
//    public @ResponseBody
//    String
//    receiveTopic(@RequestBody Map requestMap) throws Exception {
//        String EXCHANGE_NAME = "topic_logs";
//        String topicName = (String) requestMap.get("topicName");
//
//        ConnectionFactory factory = new ConnectionFactory();
//        factory.setHost("localhost");
//        Connection connection = factory.newConnection();
//        Channel channel = connection.createChannel();
//
//        channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.TOPIC);
//        String queueName = channel.queueDeclare().getQueue();
//
//        if (topicName.length() < 1) {
//            System.err.println("Usage: ReceiveLogsTopic [binding_key]...");
//            System.exit(1);
//        }
//
//        channel.queueBind(queueName, EXCHANGE_NAME, topicName);
//        System.out.println(" [*] Waiting for messages. To exit press CTRL+C");
//
//        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
//            String message = new String(delivery.getBody(), "UTF-8");
//            String routingKey = delivery.getEnvelope().getRoutingKey();
//            System.out.println(" [x] Received '" + delivery.getEnvelope().getRoutingKey() + "':'" + message + "'");
//
//            messagingTemplate.convertAndSend("/topic/greetings", message+"hello");
//        };
//        channel.basicConsume(queueName, true, deliverCallback, consumerTag -> { });
//
//        return "subscribe";
//    }
////
////    @MessageMapping("/hello")
////    @SendTo("/topic/greetings")
////    public String greeting(String message) throws Exception {
////        System.out.println("greeting");
////        return "Hello, " + message + "!";
////    }
//
//
//    @RequestMapping(value = "/publishTokenM", method = RequestMethod.POST)
//    public @ResponseBody
//    String
//    publishTokenMessageM(@RequestBody Map requestMap) throws Exception {
//
//        String BROKER_URL = "tcp://localhost:1883";
//        String TOPIC_NAME = "myTopic";
//        String MESSAGE = "Hello, RabbitMQ!";
//
//        String topic = (String) requestMap.get("topic");
//        String messageTopic = (String) requestMap.get("messageTopic");
//        //        System.out.println(topic);
//        //        System.out.println(messageTopic);
//
//        MqttClient mosquittoClient = new MqttClient(BROKER_URL, "MosquittoPublisher", new MemoryPersistence());
//        MqttConnectOptions connOpts = new MqttConnectOptions();
//        connOpts.setCleanSession(true);
//        mosquittoClient.connect(connOpts);
//        MqttMessage mqttMessage = new MqttMessage(MESSAGE.getBytes());
//        mqttMessage.setQos(0);
//        mosquittoClient.publish(TOPIC_NAME, mqttMessage);
//        mosquittoClient.disconnect();
//        System.out.println("Message sent to Mosquitto!");
//
//        return "publishMosquitto";
//
//
//    }
//
//
//
//    private static String getRouting(String[] strings) {
//        if (strings.length < 1)
//            return "anonymous.info";
//        return strings[0];
//    }
//
//    private static String getMessage(String[] strings) {
//        if (strings.length < 2)
//            return "Hello World!";
//        return joinStrings(strings, " ", 1);
//    }
//
//    private static String joinStrings(String[] strings, String delimiter, int startIndex) {
//        int length = strings.length;
//        if (length == 0) return "";
//        if (length < startIndex) return "";
//        StringBuilder words = new StringBuilder(strings[startIndex]);
//        for (int i = startIndex + 1; i < length; i++) {
//            words.append(delimiter).append(strings[i]);
//        }
//        return words.toString();
//    }
//
//}

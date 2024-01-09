package com.sinisiro.StartBoot.util.mosquitto;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

//모스키토 발송
public class MosquittoPublisher {

    private static final String BROKER_URL = "tcp://localhost:1883";
    private static final String TOPIC_NAME = "myTopic";
    private static final String MESSAGE = "Hello, RabbitMQ!";

    public static void main(String[] args) throws MqttException {
        MqttClient mosquittoClient = new MqttClient(BROKER_URL, "MosquittoPublisher", new MemoryPersistence());
        MqttConnectOptions connOpts = new MqttConnectOptions();
        connOpts.setCleanSession(true);
        mosquittoClient.connect(connOpts);
        MqttMessage mqttMessage = new MqttMessage(MESSAGE.getBytes());
        mqttMessage.setQos(0);
        mosquittoClient.publish(TOPIC_NAME, mqttMessage);
        mosquittoClient.disconnect();
        System.out.println("Message sent to Mosquitto!");
    }
}
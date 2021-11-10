package mqttDemo;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient; 
import org.eclipse.paho.client.mqttv3.MqttConnectOptions; 
import org.eclipse.paho.client.mqttv3.MqttException; 
import org.eclipse.paho.client.mqttv3.MqttMessage; 
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

public class App {
	public static void main( String[] args )
    {
        System.out.println( "MQTT Demo!" );
        //new App().doDemo(); 
        String subTopic = "/iot/63s"; 
		String pubTopic = "/iot/63s"; 
		String content = "Hello World"; 
		int qos = 1; 
		String broker = "tcp://broker.hivemq.com:1883"; 
		String clientId = "ID_OF_CLIENT"; 
		MemoryPersistence persistence = new MemoryPersistence(); 
		try { 
			MqttClient client = new MqttClient(broker, clientId, persistence); 
			// MQTT connection option
			MqttConnectOptions connOpts = new MqttConnectOptions(); 
			// retain session 
			connOpts.setCleanSession(true); 
			// set callback 
			client.setCallback(new App().new OnMessageCallback()); 
			// establish a connection 
			System.out.println("Connecting to broker: " + broker); 
			client.connect(connOpts); 
			System.out.println("Connected"); 
			// Subscribe 
			client.subscribe(subTopic); 
			// Required parameters for message publishing 
			MqttMessage message = new MqttMessage(content.getBytes()); 
			message.setQos(qos); 
			System.out.println("Publishing message: " + content); 
			client.publish(pubTopic, message); 
			System.out.println("Message published"); 
			client.disconnect(); 
			System.out.println("Disconnected"); 
			client.close(); 
			System.exit(0); 
		} 
		catch (MqttException me) { 
		     me.printStackTrace(); 
		} 
    }
    public void doDemo() {
    	String subTopic = "/iot/63s"; 
		String pubTopic = "/iot/63s"; 
		String content = "Hello World"; 
		int qos = 1; 
		String broker = "tcp://broker.hivemq.com:1883"; 
		String clientId = "ID_OF_CLIENT"; 
		MemoryPersistence persistence = new MemoryPersistence(); 
		try { 
			MqttClient client = new MqttClient(broker, clientId, persistence); 
			// MQTT connection option
			MqttConnectOptions connOpts = new MqttConnectOptions(); 
			// retain session 
			connOpts.setCleanSession(true); 
			// set callback 
			client.setCallback(new OnMessageCallback()); 
			// establish a connection 
			System.out.println("Connecting to broker: " + broker); 
			client.connect(connOpts); 
			System.out.println("Connected"); 
			// Subscribe 
			client.subscribe(subTopic); 
			// Required parameters for message publishing 
			MqttMessage message = new MqttMessage(content.getBytes()); 
			message.setQos(qos); 
			System.out.println("Publishing message: " + content); 
			client.publish(pubTopic, message); 
			System.out.println("Message published"); 
			client.disconnect(); 
			System.out.println("Disconnected"); 
			client.close(); 
			System.exit(0); 
		} 
		catch (MqttException me) { 
		     me.printStackTrace(); 
		} 
    }
    
    public class OnMessageCallback implements MqttCallback { 
    	public void connectionLost(Throwable cause) { 
	    	// After the connection is lost, it usually reconnects here 
	    	System.out.println("disconnect，you can reconnect"); 
    	} 
    	public void messageArrived(String topic, MqttMessage message) throws Exception { 
	    	// The messages obtained after subscribe will be executed here 
	    	System.out.println("Received message topic:" + topic); 
	    	System.out.println("Received message Qos:" + message.getQos()); 
	    	System.out.println("Received message content:" + new String(message.getPayload())); 
    	} 
    	public void deliveryComplete(IMqttDeliveryToken token) { 
    		System.out.println("deliveryComplete---------" + token.isComplete()); 
    	} 
    } 
}

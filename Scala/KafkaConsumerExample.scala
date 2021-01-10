import java.util.{Collections,Properties}
import java.util.regex.Pattern
import org.apache.kafka.clients.consumer.KafkaConsumer
import scala.collection.JavaConverters._

/**
    1. Ensure Kafka broker is running in 'bootstrap.servers' configured
    2. Kafka Consumers need following minimum information to consume from Topic
        a. Topic Name (List of Names in Case of Multi Topic Conusmers)
        b. BootStrap Server where Kafka Broker is running (local server in this case) if its list of servers then it should be given comma separated
            1. props.put("bootstrap.servers","localhost:9000,localhost:9001,localhost:9002")
        c. 'group.id' - A Unique id, If 2 consumers have same group id then both may not get data as expected
            i. Have some proper conventions for 'group.id' so that it will not have collision
        d. 'key.Deserialiser' -  This is  Deseraliser  Class to Deserialise message from Kafka which Kafka Producer Wrote for 'key'
            i.  If you use Wrong Deserialiser  result is  undesirable or in some case it may give Deserialise exceptions.
        d. 'value.Deserialiser' -  This is  Deseraliser  Class to Deserialise message from Kafka which Kafka Producer Wrote for 'value'

    Note:
        1. 'key' and 'value'  may have different Deserialisers  and should match with producer 'Serialize' schema
        2. Refer Kafka Documentation for other options and Default Values for each properties
        3. We can have kafkaConsumerProperties read from (Json configuration/Properties File) with key and values  so that application becomes fully configurable

    How to Run:
        1. Compile is code using 'scalac KafkaConsumerExample.scala'
        2. Run 'scala KafkaConsumerExample'
        3. Tis progrm should consume from Kafka Topics and print following informtion on console
            1. Topic Name
            2. Record Key
            3. Record Value
            4. Record offset in Kafka
            5. Record offset of partition in Kafka Topic
*/
object KafkaConsumerExample extens App {

    val kafkaConsumerProperties:Properties = new Properties();
    kafkaConsumerProperties.put("group.id", "kafkaConsumerExample")
    kafkaConsumerProperties.put("bootstrap.servers","localhost:9000")
    kafkaConsumerProperties.put("key.deserializer","org.apache.kafka.common.serialization.StringDeserializer") // Producer Serialised with 'StringSerializer' for 'key'
    kafkaConsumerProperties.put("value.deserializer","org.apache.kafka.common.serialization.StringDeserializer") // Producer Serialised with 'StringSerializer' for 'value'
    kafkaConsumerProperties.put("enable.auto.commit", "true")
    kafkaConsumerProperties.put("auto.commit.interval.ms", "1000")

    val consumer = new KafkaConsumer(kafkaConsumerProperties)
    val topics = List("topic_1","topic_2")
    try {
        consumer.subscribe(topics.asJava)
    while (true) {
      val records = consumer.poll(10)
      for (record <- records.asScala) {
        println("Topic: " + record.topic() +
                 ",Key: " + record.key() +
                 ",Value: " + record.value() +
                 ", Offset: " + record.offset() +
                 ", Partition: " + record.partition())
        }
    }
    }catch{
        case e:Exception => e.printStackTrace() //Deseraliser Exceptions
    }finally {
        consumer.close() // If program exits in any case
    }
}

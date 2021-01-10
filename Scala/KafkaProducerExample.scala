import java.util.Properties
import org.apache.kafka.clients.producer.{KafkaProducer, ProducerRecord}

/**
    1. Ensure Kafka broker is running in 'bootstrap.servers' configured
    2. Kafka Consumers need following minimum information to Write to a Topic
        a. Topic Name (List of Names in Case of Multi Topic Conusmers)
        b. BootStrap Server where Kafka Broker is running (local server in this case) if its list of servers then it should be given comma separated
            1. props.put("bootstrap.servers","localhost:9000,localhost:9001,localhost:9002")
        c. 'key.serializer' -  This is  Deseraliser  Class to Deserialise message from Kafka which Kafka Producer Wrote for 'key'
        d. 'value.serializer' -  This is  Deseraliser  Class to Deserialise message from Kafka which Kafka Producer Wrote for 'value'

    Note:
        1. 'key' and 'value'  may have different Serializers
        2. Refer Kafka Documentation for other options and Default Values for each properties
        3. We can have kafkaConsumerProperties read from (Json configuration/Properties File) with key and values  so that application becomes fully configurable

    How to Run:
        1. Compile is code using 'scalac KafkaProducerExample.scala'
        2. Run 'scala KafkaProducerExample'
        3. This will pritn record (key,value), Partition Number and Kafka offset

*/
object KafkaProducerExample extends App {
  val props:Properties = new Properties()
  props.put("bootstrap.servers","localhost:9000")
  props.put("key.serializer",
         "org.apache.kafka.common.serialization.StringSerializer")
  props.put("value.serializer",
         "org.apache.kafka.common.serialization.StringSerializer")
  props.put("acks","all")
  val producer = new KafkaProducer[String, String](props)
  val topic = "topic_1"
  try {
    for (record <- 0 to 15) {
      val record = new ProducerRecord[String, String](topic, record.toString, record)
      val metadata = producer.send(record)
      printf(s"sent record(key=%s value=%s) " +
        "meta(partition=%d, offset=%d)\n",
        record.key(), record.value(),
        metadata.get().partition(),
        metadata.get().offset())
    }
  }catch{
    case e:Exception => e.printStackTrace()
  }finally {
    producer.close()
  }
}

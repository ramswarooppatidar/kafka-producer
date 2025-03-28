package com.springboot.project.Kafka_consumer.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import com.springboot.project.Kafka_consumer.dto.Customer;

//import com.learning.kafka_producer.dto.Customer;

@EnableKafka
@Configuration
public class KafkaConsumerConfig {

    @Bean
    public ConsumerFactory<String, Customer> consumerFactory() {
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        props.put(ConsumerConfig.GROUP_ID_CONFIG, "group_id");
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
        
        JsonDeserializer<Customer> deserializer = new JsonDeserializer<>(Customer.class);
        deserializer.setRemoveTypeHeaders(false);
        deserializer.addTrustedPackages("*");
        deserializer.setUseTypeMapperForKey(true);

        return new DefaultKafkaConsumerFactory<>(props, new StringDeserializer(), deserializer);
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, Customer> kafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, Customer> factory =
                new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());
        return factory;
    }
}













//package com.springboot.project.Kafka_consumer.config;
//
//import java.util.HashMap;
//import java.util.Map;
//
//import org.apache.kafka.clients.consumer.ConsumerConfig;
//import org.apache.kafka.common.serialization.StringDeserializer;
//import org.springframework.aot.nativex.NativeConfigurationWriter;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
//import org.springframework.kafka.config.KafkaListenerContainerFactory;
//import org.springframework.kafka.core.ConsumerFactory;
//import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
//import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;
//import org.springframework.kafka.support.serializer.JsonDeserializer;
//
//@Configuration
//public class KafkaConsumerConfig {
//	@Bean
//	public Map<String, Object> consumerConfig(){
//		Map<String, Object> prop = new HashMap<>();
//		prop.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
//		prop.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
////	    prop.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserialize.class);
//	    prop.put("spring.json.trusted.packages", "com.learning.kafka_producer.dto");
//	    
//	    
////	    props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");  // Kafka server
//        prop.put(ConsumerConfig.GROUP_ID_CONFIG, "jt-group-1");  // Consumer group ID
//        prop.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");  // Start from the beginning if no offset
//        
//        // Deserializer configurations
////        prop.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
//        prop.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
//        
//        // JSON deserializer settings
//        prop.put("spring.json.trusted.packages", "com.learning.kafka_producer.dto");  
//        prop.put("spring.json.value.default.type", "*");  
//        prop.put("spring.json.use.type.headers", false);
//		return prop;
//	}
//	
//	@Bean
//	public ConsumerFactory<String, Object> consumerFactory(){
//		return new DefaultKafkaConsumerFactory<String, Object>(consumerConfig());
//	}
//	
//	@Bean
//	public ConcurrentKafkaListenerContainerFactory<String, Object> kafkaListenerContainerFactory() {
//	    ConcurrentKafkaListenerContainerFactory<String, Object> factory =
//	            new ConcurrentKafkaListenerContainerFactory<>();
//	    
//	    factory.setConsumerFactory(consumerFactory()); // Use correct method
//	    
//	    return factory;
//	}
//
//}

//package com.springboot.project.Kafka_consumer.config;
//
//import java.util.HashMap;
//import java.util.Map;
//
//import org.apache.kafka.clients.consumer.ConsumerConfig;
//import org.apache.kafka.common.serialization.StringDeserializer;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
//import org.springframework.kafka.core.ConsumerFactory;
//import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
//import org.springframework.kafka.support.serializer.JsonDeserializer;
//
//import com.springboot.project.Kafka_consumer.dto.Customer;
//
//
//@Configuration
//public class KafkaConsumerConfig {
//
//    @Bean
//    public Map<String, Object> consumerConfig() {
//        Map<String, Object> prop = new HashMap<>();
//        prop.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
//        prop.put(ConsumerConfig.GROUP_ID_CONFIG, "jt-group-1");
//        prop.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
//        
//        // Key and Value Deserializers
//        prop.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
//        prop.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
//        
//        // JSON Deserializer Settings
//        prop.put(JsonDeserializer.TRUSTED_PACKAGES, "*");  // Allow all packages
//        prop.put(JsonDeserializer.VALUE_DEFAULT_TYPE, "com.springboot.project.Kafka_consumer.dto.Customer");  
//
//        return prop;
//    }
//
////    @Bean
////    public ConsumerFactory<String, Customer> consumerFactory() {
////        return new DefaultKafkaConsumerFactory<>(
////                consumerConfig(),
////                new StringDeserializer(),
////                new JsonDeserializer<>(Customer.class)  // Initialize with Customer.class
////        );
////    }
//    @Bean
//    public ConsumerFactory<String, Customer> consumerFactory() {
//        JsonDeserializer<Customer> deserializer = new JsonDeserializer<>(Customer.class);
//        deserializer.setRemoveTypeHeaders(false);
//        deserializer.addTrustedPackages("com.learning.kafka_producer.dto"); // ✅ Ensure correct package
//        deserializer.setUseTypeMapperForKey(true);
//
//        return new DefaultKafkaConsumerFactory<>(
//            consumerConfig(),
//            new StringDeserializer(),
//            deserializer
//        );
//    }
//
//    @Bean
//    public ConcurrentKafkaListenerContainerFactory<String, Customer> kafkaListenerContainerFactory() {
//        ConcurrentKafkaListenerContainerFactory<String, Customer> factory =
//                new ConcurrentKafkaListenerContainerFactory<>();
//        factory.setConsumerFactory(consumerFactory());
//        return factory;
//    }
//}




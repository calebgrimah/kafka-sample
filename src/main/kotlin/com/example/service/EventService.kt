package com.example.service

import Event
import com.example.kafka.Consumer
import com.example.kafka.dispatch
import org.apache.kafka.clients.consumer.ConsumerRecords
import org.apache.kafka.clients.consumer.KafkaConsumer
import org.apache.kafka.clients.producer.KafkaProducer
import org.apache.kafka.clients.producer.ProducerRecord
import java.time.Duration

const val EVENTS_TOPIC = "events"

class EventService(private val kafkaProducer: KafkaProducer<Long, Event>,) {
    suspend fun sendEvent(event: Event) =
        kafkaProducer.dispatch(ProducerRecord<Long, Event>(EVENTS_TOPIC, 0, event))

}
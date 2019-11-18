package com.oracle.training.kafka

import com.oracle.training.model.Product
import org.springframework.stereotype.Component

import org.apache.kafka.clients.producer.KafkaProducer
import org.apache.kafka.clients.producer.ProducerRecord
import org.slf4j.LoggerFactory

import org.slf4j.Logger
import org.springframework.boot.configurationprocessor.json.JSONException
import org.springframework.boot.configurationprocessor.json.JSONObject

import java.util.Properties

@Component
class PriceChangePublisher {

    fun publishProductInfo(product: Product) {

        val producer = KafkaProducer<String, String>(setupKafkaProducer())

        val jsonObject = JSONObject()
        try {
            jsonObject.put("id", product.id)
            jsonObject.put("name", product.name)
            jsonObject.put("price", product.price)
        } catch (e: JSONException) {
            logger.error(e.message)
        }

        sendKafkaMessage(jsonObject.toString(), producer, "productInfo1")

    }

    fun publishPriceChange(product: Product, price: Double) {

        val producer = KafkaProducer<String, String>(setupKafkaProducer())

        val jsonObject = JSONObject()
        try {
            jsonObject.put("id", product.id)
            jsonObject.put("name", product.name)
            jsonObject.put("oldPrice", product.price)
            jsonObject.put("newPrice", price)

        } catch (e: JSONException) {
            logger.error(e.message)
        }

        sendKafkaMessage(jsonObject.toString(), producer, "productInfo1")

    }


    fun publishNameChange(product: Product, name: String) {


        val producer = KafkaProducer<String, String>(setupKafkaProducer())

        val jsonObject = JSONObject()
        try {
            jsonObject.put("id", product.id)
            jsonObject.put("oldName", product.name)
            jsonObject.put("price", product.price)
            jsonObject.put("newName", name)

        } catch (e: JSONException) {
            logger.error(e.message)
        }

        sendKafkaMessage(jsonObject.toString(), producer, "productInfo1")
    }

    private fun setupKafkaProducer(): Properties {

        /*
         * Defining producer properties.
         */
        val producerProperties = Properties()
        producerProperties["bootstrap.servers"] = "192.168.1.3:9092"
        producerProperties["acks"] = "all"
        producerProperties["retries"] = 0
        producerProperties["batch.size"] = 16384
        producerProperties["linger.ms"] = 1
        producerProperties["buffer.memory"] = 33554432
        producerProperties["key.serializer"] = "org.apache.kafka.common.serialization.StringSerializer"
        producerProperties["value.serializer"] = "org.apache.kafka.common.serialization.StringSerializer"
        return producerProperties
    }

    private fun sendKafkaMessage(payload: String,
                                 producer: KafkaProducer<String, String>,
                                 topic: String) {
        logger.info("Sending Kafka message: $payload")
        producer.send(ProducerRecord(topic, payload))
    }

    companion object {

        private val logger = LoggerFactory.getLogger(PriceChangePublisher::class.java)
    }

}

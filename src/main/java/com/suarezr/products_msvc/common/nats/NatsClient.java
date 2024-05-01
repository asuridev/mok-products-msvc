package com.suarezr.products_msvc.common.nats;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.nats.client.Connection;
import io.nats.client.Dispatcher;
import io.nats.client.Message;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class NatsClient {

  private final Connection connection;


  public Dispatcher createDispatcher(){
    return this.connection.createDispatcher();
  }

  public void reply(Message msg, Object data){
    try {
      String json = new ObjectMapper().writeValueAsString(data);
      NatsResponseDto response = new NatsResponseDto(true,0,null, json);
      this.publish(msg, response);
    } catch (JsonProcessingException e) {
      this.rpcException(msg, StatusCode.INTERNAL_SERVER_ERROR, "convert to json failed in auth-ms");
    }
  }

  public void rpcException(Message msg,int statusCode, String errorMessage){
    NatsResponseDto response = new NatsResponseDto(false, statusCode, errorMessage, null);
    this.publish(msg, response);
  }

  private void publish(Message msg, NatsResponseDto response){
    try {
      String responseAsJson = new ObjectMapper().writeValueAsString(response);
      this.connection.publish(msg.getReplyTo(), responseAsJson.getBytes());
    } catch (JsonProcessingException e) {
      this.rpcException(msg, StatusCode.INTERNAL_SERVER_ERROR, "convert to json failed in auth-ms");
    }
  }

  public <T> T toJava(Message msg, Class<T> valueType ){
    String json = new String(msg.getData());
    try {
      return new ObjectMapper().readValue(json, valueType);
    } catch (JsonProcessingException e) {
      this.rpcException(msg, StatusCode.INTERNAL_SERVER_ERROR, "convert to json failed in auth-ms");
    }
    return null;
  }




}

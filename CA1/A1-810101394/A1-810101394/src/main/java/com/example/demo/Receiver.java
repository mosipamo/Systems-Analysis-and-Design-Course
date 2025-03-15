package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.Map;

@Service
public class Receiver {

  @Autowired
  private JmsTemplate jmsTemplate;

  private final Map<String, Integer> portfolio = new HashMap<>();

  @JmsListener(destination = "INQ")
  public void receiveMessage(String message) {
    System.out.println("Received message: " + message);
    String[] parts = message.split(" ");

    if (parts.length == 0) return;

    String command = parts[0];
    String response = switch (command) {
        case "BUY", "SELL" -> handleBuyAndSellCommand(parts);
        case "ADD" -> handleAddCommand(parts);
        case "PORTFOLIO" -> handlePortfolioCommand();
        default -> "Unknown command";
    };
    sendToOutputQueue(response);
  }

  private String handleBuyAndSellCommand(String[] parts) {
    if (parts.length < 3) return "Invalid command";

    String security = parts[1];
    int amount = Integer.parseInt(parts[2]);

    if (!portfolio.containsKey(security)) {
      return "1 Unknown security";
    }

    if (parts[0].equals("BUY")) {
      portfolio.put(security, portfolio.getOrDefault(security, 0) + amount);
      return "0 Trade successful";
    }
    else {
      if (portfolio.get(security) < amount) {
        return "2 Not enough positions";
      }
      else {
        portfolio.put(security, portfolio.get(security) - amount);
        return "0 Trade successful";
      }
    }
  }

  private String handleAddCommand(String[] parts) {
    if (parts.length < 2) return "Invalid command";

    String security = parts[1];
    portfolio.put(security, 0);
    return "0 Success";
  }

  private String handlePortfolioCommand() {
    StringBuilder result = new StringBuilder("0");
    for (Map.Entry<String, Integer> mapElement : portfolio.entrySet()) {
      String key = mapElement.getKey();
      int value = mapElement.getValue();
      result.append(" ").append(key).append(" ").append(Integer.toString(value)).append(" |");
    }
    result.setLength(result.length() - 2);
    return result.toString();
  }

  private void sendToOutputQueue(String response) {
    jmsTemplate.convertAndSend("OUTQ", response);
    System.out.println("Sent response to OUTQ: " + response);
  }
}
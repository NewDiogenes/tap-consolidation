package com.example.consolodation.service.route;

import java.math.BigDecimal;

public class Route {

  private String startStopId;
  private String endStopId;
  private BigDecimal price;

  public Route(String startStopId, String endStopId, BigDecimal price) {
    this.startStopId = startStopId;
    this.endStopId = endStopId;
    this.price = price;
  }

  public String getStartStopId() {
    return startStopId;
  }

  public void setStartStopId(String startStopId) {
    this.startStopId = startStopId;
  }

  public String getEndStopId() {
    return endStopId;
  }

  public void setEndStopId(String endStopId) {
    this.endStopId = endStopId;
  }

  public BigDecimal getPrice() {
    return price;
  }

  public void setPrice(BigDecimal price) {
    this.price = price;
  }
}

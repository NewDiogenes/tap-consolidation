package com.example.consolodation.service.trip.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.StringJoiner;

public class TripRecord {

  private LocalDateTime started;
  private LocalDateTime finished;
  private Long durationSecs;
  private String fromStopId;
  private String toStopId;
  private BigDecimal chargeAmount;
  private String companyId;
  private String busId;
  private Long primaryAccountNumber;
  private TripStatus status;

  public LocalDateTime getStarted() {
    return started;
  }

  public void setStarted(LocalDateTime started) {
    this.started = started;
  }

  public LocalDateTime getFinished() {
    return finished;
  }

  public void setFinished(LocalDateTime finished) {
    this.finished = finished;
  }

  public Long getDurationSecs() {
    return durationSecs;
  }

  public void setDurationSecs(Long durationSecs) {
    this.durationSecs = durationSecs;
  }

  public String getFromStopId() {
    return fromStopId;
  }

  public void setFromStopId(String fromStopId) {
    this.fromStopId = fromStopId;
  }

  public String getToStopId() {
    return toStopId;
  }

  public void setToStopId(String toStopId) {
    this.toStopId = toStopId;
  }

  public BigDecimal getChargeAmount() {
    return chargeAmount;
  }

  public void setChargeAmount(BigDecimal chargeAmount) {
    this.chargeAmount = chargeAmount;
  }

  public String getCompanyId() {
    return companyId;
  }

  public void setCompanyId(String companyId) {
    this.companyId = companyId;
  }

  public String getBusId() {
    return busId;
  }

  public void setBusId(String busId) {
    this.busId = busId;
  }

  public Long getPrimaryAccountNumber() {
    return primaryAccountNumber;
  }

  public void setPrimaryAccountNumber(Long primaryAccountNumber) {
    this.primaryAccountNumber = primaryAccountNumber;
  }

  public TripStatus getStatus() {
    return status;
  }

  public void setStatus(TripStatus status) {
    this.status = status;
  }

  @Override
  public String toString() {
    return new StringJoiner(", ", TripRecord.class.getSimpleName() + "[", "]")
        .add("started=" + started)
        .add("finished=" + finished)
        .add("durationSecs=" + durationSecs)
        .add("fromStopId='" + fromStopId + "'")
        .add("toStopId='" + toStopId + "'")
        .add("chargeAmount=" + chargeAmount)
        .add("companyId='" + companyId + "'")
        .add("busId='" + busId + "'")
        .add("primaryAccountNumber=" + primaryAccountNumber)
        .add("status=" + status)
        .toString();
  }
}

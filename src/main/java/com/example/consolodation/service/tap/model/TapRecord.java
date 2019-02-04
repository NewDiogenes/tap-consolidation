package com.example.consolodation.service.tap.model;

import java.time.LocalDateTime;
import java.util.StringJoiner;

public class TapRecord {

  private Long tapRecordId;
  private LocalDateTime dateTime;
  private TapType tapType;
  private String stopId;
  private String companyId;
  private String busId;
  private Long primaryAccountNumber;

  public Long getTapRecordId() {
    return tapRecordId;
  }

  public void setTapRecordId(Long tapRecordId) {
    this.tapRecordId = tapRecordId;
  }

  public LocalDateTime getDateTime() {
    return dateTime;
  }

  public void setDateTime(LocalDateTime dateTime) {
    this.dateTime = dateTime;
  }

  public TapType getTapType() {
    return tapType;
  }

  public void setTapType(TapType tapType) {
    this.tapType = tapType;
  }

  public String getStopId() {
    return stopId;
  }

  public void setStopId(String stopId) {
    this.stopId = stopId;
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

  @Override
  public String toString() {
    return new StringJoiner(", ", TapRecord.class.getSimpleName() + "[", "]")
        .add("tapRecordId=" + tapRecordId)
        .add("dateTime=" + dateTime)
        .add("tapType=" + tapType)
        .add("stopId='" + stopId + "'")
        .add("companyId='" + companyId + "'")
        .add("busId='" + busId + "'")
        .add("primaryAccountNumber=" + primaryAccountNumber)
        .toString();
  }
}

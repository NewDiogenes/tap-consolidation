package com.example.consolodation.service.route;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class RouteService {

  private static final List<Route> routes = Arrays.asList(
      new Route("stop1", "stop2", BigDecimal.valueOf(3.25)),
      new Route("stop2", "stop3", BigDecimal.valueOf(5.50)),
      new Route("stop2", "stop1", BigDecimal.valueOf(7.30)),
      new Route("stop2", "stop1", BigDecimal.valueOf(3.25)),
      new Route("stop3", "stop2", BigDecimal.valueOf(5.50)),
      new Route("stop1", "stop3", BigDecimal.valueOf(7.30)));

  public Route maxPriceForStop(String stopId) {
    return routes.stream().filter(
        route -> stopId.toLowerCase().equals(route.getStartStopId()) || stopId.toLowerCase().equals(route.getEndStopId()))
        .max(Comparator.comparing(Route::getPrice))
        .get();
  }

  public BigDecimal priceBetweenStops(String startStop, String endStop) {
    return routes.stream().filter(route ->
        route.getStartStopId().equals(startStop.toLowerCase()))
        .filter(route -> route.getEndStopId().equals(endStop.toLowerCase()))
        .map(Route::getPrice)
        .findAny()
        .get();
  }
}

package com.example.consolodation.service.tap;

import com.example.consolodation.service.tap.model.TapRecord;
import com.example.consolodation.service.tap.model.TapType;
import io.reactivex.Observable;
import io.reactivex.functions.Predicate;
import io.reactivex.observables.GroupedObservable;

import java.util.stream.Stream;

public class TapConsolidationService {

  public Observable<GroupedObservable<Long, TapRecord>> consolidateTaps(Stream<TapRecord> tapRecordStream) {
    return Observable.fromIterable(tapRecordStream::iterator)
        .map(TapRecord.class::cast)
        .filter(filterForTapErrors())
        .groupBy(TapRecord::getPrimaryAccountNumber);
  }

  private Predicate<TapRecord> filterForTapErrors() {
    return tapRecord -> !TapType.ERROR.equals(tapRecord.getTapType());
  }
}

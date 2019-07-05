package cn.maaa.common.logback;

import ch.qos.logback.classic.pattern.ClassicConverter;
import ch.qos.logback.classic.spi.ILoggingEvent;

import cn.maaa.common.logback.cnostant.TrackConsts;
import org.slf4j.MDC;

public class TrackConverter extends ClassicConverter {

  @Override
  public String convert(ILoggingEvent event) {
    return MDC.get(TrackConsts.TRACK_KEY) == null ? TrackConsts.NON_TRACK_VALUE : MDC.get(TrackConsts.TRACK_KEY);
  }
}


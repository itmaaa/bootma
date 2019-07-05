package cn.maaa.common.logback;

import ch.qos.logback.classic.PatternLayout;
import cn.maaa.common.logback.cnostant.TrackConsts;


public class PatternLogbackLayout extends PatternLayout {
	static {
		defaultConverterMap.put(TrackConsts.TRACK_KEY, TrackConverter.class.getName());
	}
}

package cn.maaa.common.logback;

import ch.qos.logback.core.ConsoleAppender;
import ch.qos.logback.core.encoder.LayoutWrappingEncoder;

import java.nio.charset.Charset;

/**
 * MyConsoleAppender
 * @author mazh
 * @date 2019年06月14日 14:38 
 */
public class MyConsoleAppender extends ConsoleAppender {

	String pattern = "[%d{yyyy-MM-dd HH:mm:ss.SSS}] [%-5level] [%cyan(%traceId)] [%msg] [%logger{0}.%M.%L] \\n";


	String charsetStr = "utf-8";

	public PatternLogbackLayout getLayout(){
		PatternLogbackLayout patternLogbackLayout = new PatternLogbackLayout();
		patternLogbackLayout.setPattern(pattern);
		return  patternLogbackLayout;
	}


    public LayoutWrappingEncoder getLayoutWrappingEncoder (){
		LayoutWrappingEncoder layoutWrappingEncoder = new LayoutWrappingEncoder();
		layoutWrappingEncoder.setLayout(getLayout());

		layoutWrappingEncoder.setCharset(Charset.forName(charsetStr));
		return layoutWrappingEncoder;
	}

	public  MyConsoleAppender(){
		setEncoder(getLayoutWrappingEncoder ());
		super.start();
	}

}

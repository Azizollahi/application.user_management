package framework;

import java.time.Duration;
import java.time.LocalDateTime;

public class LocalDateTimeUtility {

	// hiding implicit public deceleration
	private LocalDateTimeUtility(){}

	private static LocalDateTime baseTime = LocalDateTime.of(1970,1,1,0,0,0);
	public static LocalDateTime fromTotalSecondsToLocalDateTime(long totalSeconds){
		return baseTime.plusSeconds(totalSeconds);
	}
	public static long fromLocalDateTimeToTotalSeconds(LocalDateTime dateTime){
		return Duration.between(baseTime, dateTime).getSeconds();
	}
}

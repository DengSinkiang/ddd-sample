package com.sinkiang.ddd.sample.common.util;

import org.apache.commons.lang3.StringUtils;
import java.lang.reflect.Field;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Period;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalAccessor;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;


/**
 * @author dengxj
 * @date 2022/7/27 15:25
 */

public class DateUtils {
    private static final String PATTERN_YYYY_MM_DD_HH_MM_SS_SSS = "yyyy-MM-dd HH:mm:ss.SSS";
    private static final String PATTERN_YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";
    private static final String PATTERN_YYYYMMDDHHMMSS = "yyyyMMddHHmmss";
    private static final String PATTERN_YYYYMMDD = "yyyyMMdd";
    private static final String PATTERN_YYYY_MM_DD = "yyyy-MM-dd";
    private static final String PATTERN_HHMMDD = "HHmmss";
    private static final String PATTERN_HH_MM_DD = "HH:mm:ss";
    private static final ZoneId ZONE = ZoneId.of("Asia/Shanghai");
    public static final LocalDate GREENWICH_MEAN_TIME = LocalDate.of(1970, 1, 1);
    public static String NULL_PARAM = "传入参数为空";
    public static String NULL_PATTERN_PARAM = "转换格式为空";

    public DateUtils() {
    }

    /**
     * 获取一个时间区间的日期
     *
     * @param start
     * @param end
     * @return
     */
    public static List<String> getBetweenDate(String start, String end) {
        List<String> list = new ArrayList<>();
        LocalDate startDate;
        LocalDate endDate;
        try {
            startDate = LocalDate.parse(start);
            endDate = LocalDate.parse(end);
        } catch (Exception e) {
            throw new RuntimeException("日期格式不正确");
        }

        if (start.equals(end)) {
            list.add(start);
            return list;
        }

        long distance = ChronoUnit.DAYS.between(startDate, endDate);
        if (distance < 1) {
            return list;
        }
        Stream.iterate(startDate, d -> d.plusDays(1)).limit(distance + 1).forEach(f -> {
            list.add(f.toString());
        });
        return list;
    }

    public static Date instantToDateConverter(Instant instant) {
        Optional.ofNullable(instant).orElseThrow(() -> new RuntimeException(NULL_PARAM));
        return Date.from(instant);
    }

    public static Date localDateTimeToDateConverter(LocalDateTime localDateTime) {
        Optional.ofNullable(localDateTime).orElseThrow(() -> new RuntimeException(NULL_PARAM));
        Instant instant = localDateTime.atZone(ZONE).toInstant();
        return instantToDateConverter(instant);
    }

    public static Date localDateToDateConverter(LocalDate localDate) {
        Optional.ofNullable(localDate).orElseThrow(() -> new RuntimeException(NULL_PARAM));
        Instant instant = localDate.atStartOfDay(ZONE).toInstant();
        return instantToDateConverter(instant);
    }

    public static LocalDateTime localDateToLocalDateTimeConverter(LocalDate localDate) {
        Optional.ofNullable(localDate).orElseThrow(() -> new RuntimeException(NULL_PARAM));
        return localDate.atStartOfDay();
    }

    public static LocalDateTime localTimeToLocalDateTimeConverter(LocalTime localTime) {
        Optional.ofNullable(localTime).orElseThrow(() -> new RuntimeException(NULL_PARAM));
        return localTime.atDate(GREENWICH_MEAN_TIME);
    }

    public static Instant dateToInstantConverter(Date date) {
        Optional.ofNullable(date).orElseThrow(() -> {
            return new RuntimeException(NULL_PARAM);
        });
        return date.toInstant();
    }

    public static LocalDateTime dateToLocalDateTimeConverter(Date date) {
        Optional.ofNullable(date).orElseThrow(() -> new RuntimeException(NULL_PARAM));
        return LocalDateTime.ofInstant(dateToInstantConverter(date), ZONE);
    }

    public static LocalDate dateToLocalDateConverter(Date date) {
        Optional.ofNullable(date).orElseThrow(() -> new RuntimeException(NULL_PARAM));
        return dateToLocalDateTimeConverter(date).toLocalDate();
    }

    public static LocalTime dateToLocalTimeConverter(Date date) {
        Optional.ofNullable(date).orElseThrow(() -> new RuntimeException(NULL_PARAM));
        return dateToLocalDateTimeConverter(date).toLocalTime();
    }

    public static Instant localDateTimeToInstantConverter(LocalDateTime localDateTime) {
        Optional.ofNullable(localDateTime).orElseThrow(() -> new RuntimeException(NULL_PARAM));
        return localDateTime.atZone(ZONE).toInstant();
    }

    public static LocalDateTime instantToLocalDateTimeConverter(Instant instant) {
        Optional.ofNullable(instant).orElseThrow(() -> new RuntimeException(NULL_PARAM));
        return instant.atZone(ZONE).toLocalDateTime();
    }

    public static LocalDate localDateTimeToLocalDateConverter(LocalDateTime localDateTime) {
        Optional.ofNullable(localDateTime).orElseThrow(() -> new RuntimeException(NULL_PARAM));
        return localDateTime.atZone(ZONE).toLocalDate();
    }

    public static LocalTime localDateTimeToLocalTimeConverter(LocalDateTime localDateTime) {
        Optional.ofNullable(localDateTime).orElseThrow(() -> new RuntimeException(NULL_PARAM));
        return localDateTime.atZone(ZONE).toLocalTime();
    }

    public static String format(Date date, DateTimeFormatter pattern) {
        Optional.ofNullable(date).orElseThrow(() -> new RuntimeException(NULL_PARAM));
        Optional.ofNullable(pattern).orElseThrow(() -> new RuntimeException(NULL_PATTERN_PARAM));
        return format(dateToLocalDateTimeConverter(date), pattern);
    }

    public static String format(Instant instant, DateTimeFormatter pattern) {
        Optional.ofNullable(instant).orElseThrow(() -> new RuntimeException(NULL_PARAM));
        Optional.ofNullable(pattern).orElseThrow(() -> new RuntimeException(NULL_PATTERN_PARAM));
        return pattern.format(instant);
    }

    public static String format(LocalDateTime localDateTime, DateTimeFormatter pattern) {
        Optional.ofNullable(localDateTime).orElseThrow(() -> new RuntimeException(NULL_PARAM));
        Optional.ofNullable(pattern).orElseThrow(() -> new RuntimeException(NULL_PATTERN_PARAM));
        return pattern.format(localDateTime);
    }

    public static String format(LocalDate localDate, DateTimeFormatter pattern) {
        Optional.ofNullable(localDate).orElseThrow(() -> new RuntimeException(NULL_PARAM));
        Optional.ofNullable(pattern).orElseThrow(() -> new RuntimeException(NULL_PATTERN_PARAM));
        return format(localDateToLocalDateTimeConverter(localDate), pattern);
    }

    public static String format(LocalTime localTime, DateTimeFormatter pattern) {
        Optional.ofNullable(localTime).orElseThrow(() -> new RuntimeException(NULL_PARAM));
        Optional.ofNullable(pattern).orElseThrow(() -> new RuntimeException(NULL_PATTERN_PARAM));
        return format(localTimeToLocalDateTimeConverter(localTime), pattern);
    }

    public static Date formatDate(String text, DateTimeFormatter pattern) {
        if (StringUtils.isEmpty(text)) {
            throw new RuntimeException("can not format String :" + text);
        } else {
            Optional.ofNullable(pattern).orElseThrow(() -> new RuntimeException(NULL_PATTERN_PARAM));
            return localDateTimeToDateConverter(formatLocalDateTime(text, pattern));
        }
    }

    public static Instant formatInstant(String text, DateTimeFormatter pattern) {
        if (StringUtils.isEmpty(text)) {
            throw new RuntimeException("can not format String :" + text);
        } else {
            Optional.ofNullable(pattern).orElseThrow(() -> {
                return new RuntimeException(NULL_PATTERN_PARAM);
            });
            return localDateTimeToInstantConverter(formatLocalDateTime(text, pattern));
        }
    }

    public static LocalDateTime formatLocalDateTime(String text, DateTimeFormatter pattern) {
        if (StringUtils.isEmpty(text)) {
            throw new RuntimeException("can not format String :" + text);
        } else {
            Optional.ofNullable(pattern).orElseThrow(() -> new RuntimeException(NULL_PATTERN_PARAM));
            TemporalAccessor temporalAccessor = pattern.parse(text);

            try {
                Field date = temporalAccessor.getClass().getDeclaredField("date");
                date.setAccessible(true);
                if (!Optional.ofNullable(date.get(temporalAccessor)).isPresent()) {
                    date.set(temporalAccessor, GREENWICH_MEAN_TIME);
                }

                Field time = temporalAccessor.getClass().getDeclaredField("time");
                time.setAccessible(true);
                if (!Optional.ofNullable(time.get(temporalAccessor)).isPresent()) {
                    time.set(temporalAccessor, LocalTime.MIN);
                }

                return LocalDateTime.from(temporalAccessor);
            } catch (ReflectiveOperationException var5) {
                throw new RuntimeException("format error :" + var5.getMessage());
            }
        }
    }

    public static LocalDate formatLocalDate(String text, DateTimeFormatter pattern) {
        if (StringUtils.isEmpty(text)) {
            throw new RuntimeException("can not format String :" + text);
        } else {
            Optional.ofNullable(pattern).orElseThrow(() -> {
                return new RuntimeException(NULL_PATTERN_PARAM);
            });

            try {
                return LocalDate.parse(text, pattern);
            } catch (DateTimeParseException var3) {
                throw new RuntimeException("why pattern does not have yyyymmdd? [" + pattern + "]");
            }
        }
    }

    public static LocalTime formatLocalTime(String text, DateTimeFormatter pattern) {
        if (StringUtils.isEmpty(text)) {
            throw new RuntimeException("can not format String :" + text);
        } else {
            Optional.ofNullable(pattern).orElseThrow(() -> new RuntimeException(NULL_PATTERN_PARAM));

            try {
                return LocalTime.parse(text, pattern);
            } catch (DateTimeParseException var3) {
                throw new RuntimeException("why pattern does not have hhmmdd? [" + pattern + "]");
            }
        }
    }

    public static int getYear(Date date) {
        Optional.ofNullable(date).orElseThrow(() -> new RuntimeException("date can not be null"));
        return getYear(dateToLocalDateTimeConverter(date));
    }

    public static int getYear(Instant instant) {
        Optional.ofNullable(instant).orElseThrow(() -> new RuntimeException("instant can not be null"));
        return getYear(instantToLocalDateTimeConverter(instant));
    }

    public static int getYear(LocalDateTime localDateTime) {
        Optional.ofNullable(localDateTime).orElseThrow(() -> new RuntimeException("localDateTime can not be null"));
        return localDateTime.getYear();
    }

    public static int getYear(LocalDate localDate) {
        Optional.ofNullable(localDate).orElseThrow(() -> new RuntimeException("localDate can not be null"));
        return localDate.getYear();
    }

    public static int getMonth(Date date) {
        Optional.ofNullable(date).orElseThrow(() -> new RuntimeException("date can not be null"));
        return getMonth(dateToLocalDateTimeConverter(date));
    }

    public static int getMonth(Instant instant) {
        Optional.ofNullable(instant).orElseThrow(() -> new RuntimeException("instant can not be null"));
        return getMonth(instantToLocalDateTimeConverter(instant));
    }

    public static int getMonth(LocalDateTime localDateTime) {
        Optional.ofNullable(localDateTime).orElseThrow(() -> new RuntimeException("localDateTime can not be null"));
        return localDateTime.getMonth().getValue();
    }

    public static int getMonth(LocalDate localDate) {
        Optional.ofNullable(localDate).orElseThrow(() -> new RuntimeException(NULL_PARAM));
        return localDate.getMonth().getValue();
    }

    public static int getDayOfMonth(Date date) {
        Optional.ofNullable(date).orElseThrow(() -> new RuntimeException(NULL_PARAM));
        return getDayOfMonth(dateToLocalDateTimeConverter(date));
    }

    public static int getDayOfMonth(Instant instant) {
        Optional.ofNullable(instant).orElseThrow(() -> new RuntimeException(NULL_PARAM));
        return getDayOfMonth(instantToLocalDateTimeConverter(instant));
    }

    public static int getDayOfMonth(LocalDateTime localDateTime) {
        Optional.ofNullable(localDateTime).orElseThrow(() -> new RuntimeException(NULL_PARAM));
        return localDateTime.getDayOfMonth();
    }

    public static int getDayOfMonth(LocalDate localDate) {
        Optional.ofNullable(localDate).orElseThrow(() -> new RuntimeException(NULL_PARAM));
        return localDate.getDayOfMonth();
    }

    public static int getHour(Date date) {
        Optional.ofNullable(date).orElseThrow(() -> new RuntimeException(NULL_PARAM));
        return getHour(dateToLocalDateTimeConverter(date));
    }

    public static int getHour(Instant instant) {
        Optional.ofNullable(instant).orElseThrow(() -> new RuntimeException(NULL_PARAM));
        return getHour(instantToLocalDateTimeConverter(instant));
    }

    public static int getHour(LocalDateTime localDateTime) {
        Optional.ofNullable(localDateTime).orElseThrow(() -> new RuntimeException(NULL_PARAM));
        return localDateTime.getHour();
    }

    public static int getHour(LocalTime localTime) {
        Optional.ofNullable(localTime).orElseThrow(() -> new RuntimeException(NULL_PARAM));
        return localTime.getHour();
    }

    public static int getMinute(Date date) {
        Optional.ofNullable(date).orElseThrow(() -> new RuntimeException(NULL_PARAM));
        return getMinute(dateToLocalDateTimeConverter(date));
    }

    public static int getMinute(Instant instant) {
        Optional.ofNullable(instant).orElseThrow(() -> new RuntimeException(NULL_PARAM));
        return getMinute(instantToLocalDateTimeConverter(instant));
    }

    public static int getMinute(LocalDateTime localDateTime) {
        Optional.ofNullable(localDateTime).orElseThrow(() -> new RuntimeException(NULL_PARAM));
        return localDateTime.getMinute();
    }

    public static int getMinute(LocalTime localTime) {
        Optional.ofNullable(localTime).orElseThrow(() -> new RuntimeException(NULL_PARAM));
        return localTime.getMinute();
    }

    public static int getSecond(Date date) {
        Optional.ofNullable(date).orElseThrow(() -> new RuntimeException(NULL_PARAM));
        return getMinute(dateToLocalDateTimeConverter(date));
    }

    public static int getSecond(Instant instant) {
        Optional.ofNullable(instant).orElseThrow(() -> new RuntimeException(NULL_PARAM));
        return getSecond(instantToLocalDateTimeConverter(instant));
    }

    public static int getSecond(LocalDateTime localDateTime) {
        Optional.ofNullable(localDateTime).orElseThrow(() -> new RuntimeException(NULL_PARAM));
        return localDateTime.getSecond();
    }

    public static int getSecond(LocalTime localTime) {
        Optional.ofNullable(localTime).orElseThrow(() -> new RuntimeException(NULL_PARAM));
        return localTime.getSecond();
    }

    public static long between(Date source, Date target, TimeUnit timeUnit) {
        Optional.ofNullable(source).orElseThrow(() -> new RuntimeException("source can not be null"));
        Optional.ofNullable(target).orElseThrow(() -> new RuntimeException("target can not be null"));
        Optional.ofNullable(timeUnit).orElseThrow(() -> {
            return new RuntimeException("timeUnit can not be null");
        });
        return doBetween(dateToLocalDateTimeConverter(source), dateToLocalDateTimeConverter(target), timeUnit);
    }

    public static long between(Instant source, Instant target, TimeUnit timeUnit) {
        Optional.ofNullable(source).orElseThrow(() -> new RuntimeException("source can not be null"));
        Optional.ofNullable(target).orElseThrow(() -> new RuntimeException("target can not be null"));
        Optional.ofNullable(timeUnit).orElseThrow(() -> new RuntimeException("timeUnit can not be null"));
        return doBetween(instantToLocalDateTimeConverter(source), instantToLocalDateTimeConverter(target), timeUnit);
    }

    public static long between(LocalDateTime source, LocalDateTime target, TimeUnit timeUnit) {
        Optional.ofNullable(source).orElseThrow(() -> new RuntimeException("source can not be null"));
        Optional.ofNullable(target).orElseThrow(() -> new RuntimeException("target can not be null"));
        Optional.ofNullable(timeUnit).orElseThrow(() -> new RuntimeException("timeUnit can not be null"));
        return doBetween(source, target, timeUnit);
    }

    public static long between(LocalDate source, LocalDate target, TimeUnit timeUnit) {
        Optional.ofNullable(source).orElseThrow(() -> new RuntimeException("source can not be null"));
        Optional.ofNullable(target).orElseThrow(() -> new RuntimeException("target can not be null"));
        Optional.ofNullable(timeUnit).orElseThrow(() -> new RuntimeException("timeUnit can not be null"));
        if (TimeUnit.DAYS != timeUnit) {
            throw new RuntimeException("LocalDate can not get between with :" + timeUnit.name());
        } else {
            return doBetween(localDateToLocalDateTimeConverter(source), localDateToLocalDateTimeConverter(target), timeUnit);
        }
    }

    public static long between(LocalTime source, LocalTime target, TimeUnit timeUnit) {
        Optional.ofNullable(source).orElseThrow(() -> new RuntimeException("source can not be null"));
        Optional.ofNullable(target).orElseThrow(() -> new RuntimeException("target can not be null"));
        Optional.ofNullable(timeUnit).orElseThrow(() -> new RuntimeException("timeUnit can not be null"));
        if (TimeUnit.DAYS == timeUnit) {
            throw new RuntimeException("LocalDate can not get between with :" + timeUnit.name());
        } else {
            return doBetween(localTimeToLocalDateTimeConverter(source), localTimeToLocalDateTimeConverter(target), timeUnit);
        }
    }

    public static long betweenNow(Date source, TimeUnit timeUnit) {
        Optional.ofNullable(source).orElseThrow(() -> new RuntimeException("source can not be null"));
        Optional.ofNullable(timeUnit).orElseThrow(() -> new RuntimeException("timeUnit can not be null"));
        return between(dateToLocalDateTimeConverter(source), LocalDateTime.now(), timeUnit);
    }

    public static long betweenNow(Instant source, TimeUnit timeUnit) {
        Optional.ofNullable(source).orElseThrow(() -> new RuntimeException("source can not be null"));
        Optional.ofNullable(timeUnit).orElseThrow(() -> new RuntimeException("timeUnit can not be null"));
        return between(source, Instant.now(), timeUnit);
    }

    public static long betweenNow(LocalDateTime source, TimeUnit timeUnit) {
        Optional.ofNullable(source).orElseThrow(() -> new RuntimeException("source can not be null"));
        Optional.ofNullable(timeUnit).orElseThrow(() -> new RuntimeException("timeUnit can not be null"));
        return between(source, LocalDateTime.now(), timeUnit);
    }

    public static long betweenNow(LocalDate source, TimeUnit timeUnit) {
        Optional.ofNullable(source).orElseThrow(() -> new RuntimeException("source can not be null"));
        Optional.ofNullable(timeUnit).orElseThrow(() -> new RuntimeException("timeUnit can not be null"));
        return between(source, LocalDate.now(), timeUnit);
    }

    public static long betweenNow(LocalTime source, TimeUnit timeUnit) {
        Optional.ofNullable(source).orElseThrow(() -> new RuntimeException("source can not be null"));
        Optional.ofNullable(timeUnit).orElseThrow(() -> new RuntimeException("timeUnit can not be null"));
        return between(source, LocalTime.now(), timeUnit);
    }

    private static long doBetween(LocalDateTime source, LocalDateTime target, TimeUnit timeUnit) {
        Duration duration = Duration.between(source, target);
        switch (timeUnit) {
            case DAYS:
                return (long) Period.between(source.toLocalDate(), target.toLocalDate()).getDays();
            case HOURS:
                return duration.toHours();
            case MINUTES:
                return duration.toMinutes();
            case MILLISECONDS:
                return duration.toMillis();
            case SECONDS:
                return duration.toMillis() / 1000L;
            case NANOSECONDS:
                return duration.toNanos();
            case MICROSECONDS:
                return duration.toMillis() * 1000L;
            default:
                throw new RuntimeException("unknown timeUnit:" + timeUnit.name());
        }
    }

    public static Date plus(Date source, long amount, TimeUnit timeUnit) {
        Optional.ofNullable(source).orElseThrow(() -> new RuntimeException("source can not be null"));
        Optional.ofNullable(amount).orElseThrow(() -> new RuntimeException("amount can not be null"));
        Optional.ofNullable(timeUnit).orElseThrow(() -> {
            return new RuntimeException("timeUnit can not be null");
        });
        LocalDateTime localDateTime = plus(dateToLocalDateTimeConverter(source), amount, timeUnit);
        return localDateTimeToDateConverter(localDateTime);
    }

    public static Instant plus(Instant source, long amount, TimeUnit timeUnit) {
        Optional.ofNullable(source).orElseThrow(() -> new RuntimeException("source can not be null"));
        Optional.ofNullable(amount).orElseThrow(() -> new RuntimeException("amount can not be null"));
        Optional.ofNullable(timeUnit).orElseThrow(() -> {
            return new RuntimeException("timeUnit can not be null");
        });
        return (Instant) doPlus(source, amount, timeUnit);
    }

    public static LocalDateTime plus(LocalDateTime source, long amount, TimeUnit timeUnit) {
        Optional.ofNullable(source).orElseThrow(() -> {
            return new RuntimeException("source can not be null");
        });
        Optional.ofNullable(amount).orElseThrow(() -> {
            return new RuntimeException("amount can not be null");
        });
        Optional.ofNullable(timeUnit).orElseThrow(() -> {
            return new RuntimeException("timeUnit can not be null");
        });
        return (LocalDateTime) doPlus(source, amount, timeUnit);
    }

    public static LocalDate plus(LocalDate source, long amount, TimeUnit timeUnit) {
        Optional.ofNullable(source).orElseThrow(() -> {
            return new RuntimeException("source can not be null");
        });
        Optional.ofNullable(amount).orElseThrow(() -> {
            return new RuntimeException("amount can not be null");
        });
        Optional.ofNullable(timeUnit).orElseThrow(() -> {
            return new RuntimeException("timeUnit can not be null");
        });
        if (TimeUnit.DAYS != timeUnit) {
            throw new RuntimeException("LocalDate can not plus with :" + timeUnit.name());
        } else {
            return (LocalDate) doPlus(source, amount, timeUnit);
        }
    }

    public static LocalTime plus(LocalTime source, long amount, TimeUnit timeUnit) {
        Optional.ofNullable(source).orElseThrow(() -> {
            return new RuntimeException("source can not be null");
        });
        Optional.ofNullable(amount).orElseThrow(() -> {
            return new RuntimeException("amount can not be null");
        });
        Optional.ofNullable(timeUnit).orElseThrow(() -> {
            return new RuntimeException("timeUnit can not be null");
        });
        if (TimeUnit.DAYS == timeUnit) {
            throw new RuntimeException("LocalDate can not plus with :" + timeUnit.name());
        } else {
            return (LocalTime) doPlus(source, amount, timeUnit);
        }
    }

    public static <T> T plusNow(long amount, TimeUnit timeUnit, Class<T> tClass) {
        Optional.ofNullable(timeUnit).orElseThrow(() -> new RuntimeException("timeUnit can not be null"));
        Optional.ofNullable(tClass).orElseThrow(() -> new RuntimeException("tClass can not be null"));
        if (tClass.getName().equals(Date.class.getName())) {
            return (T) plus(new Date(), amount, timeUnit);
        } else if (tClass.getName().equals(Instant.class.getName())) {
            return (T) plus(Instant.now(), amount, timeUnit);
        } else if (tClass.getName().equals(LocalDateTime.class.getName())) {
            return (T) plus(Instant.now(), amount, timeUnit);
        } else if (tClass.getName().equals(LocalDate.class.getName())) {
            return (T) plus(Instant.now(), amount, timeUnit);
        } else if (tClass.getName().equals(LocalTime.class.getName())) {
            return (T) plus(Instant.now(), amount, timeUnit);
        } else {
            throw new RuntimeException("can not plus now with class:" + tClass.getName());
        }
    }

    private static <T extends TemporalAccessor> Temporal doPlus(Temporal source, long amount, TimeUnit timeUnit) {
        switch (timeUnit) {
            case DAYS:
                return source.plus(amount, ChronoUnit.DAYS);
            case HOURS:
                return source.plus(amount, ChronoUnit.HOURS);
            case MINUTES:
                return source.plus(amount, ChronoUnit.MINUTES);
            case MILLISECONDS:
                return source.plus(amount, ChronoUnit.MILLIS);
            case SECONDS:
                return source.plus(amount * 1000L, ChronoUnit.MILLIS);
            case NANOSECONDS:
                return source.plus(amount, ChronoUnit.NANOS);
            case MICROSECONDS:
                return source.plus(amount / 1000L, ChronoUnit.MILLIS);
            default:
                throw new RuntimeException("unknown timeUnit:" + timeUnit.name());
        }
    }

    public static Date minus(Date source, long amount, TimeUnit timeUnit) {
        Optional.ofNullable(source).orElseThrow(() -> new RuntimeException("source can not be null"));
        Optional.ofNullable(timeUnit).orElseThrow(() -> new RuntimeException("timeUnit can not be null"));
        LocalDateTime localDateTime = minus(dateToLocalDateTimeConverter(source), amount, timeUnit);
        return localDateTimeToDateConverter(localDateTime);
    }

    public static Instant minus(Instant source, long amount, TimeUnit timeUnit) {
        Optional.ofNullable(source).orElseThrow(() -> new RuntimeException("source can not be null"));
        Optional.ofNullable(timeUnit).orElseThrow(() -> new RuntimeException("timeUnit can not be null"));
        return (Instant) doMinus(source, amount, timeUnit);
    }

    public static LocalDateTime minus(LocalDateTime source, long amount, TimeUnit timeUnit) {
        Optional.ofNullable(source).orElseThrow(() -> new RuntimeException("source can not be null"));
        Optional.ofNullable(timeUnit).orElseThrow(() -> new RuntimeException("timeUnit can not be null"));
        return (LocalDateTime) doMinus(source, amount, timeUnit);
    }

    public static LocalDate minus(LocalDate source, long amount, TimeUnit timeUnit) {
        Optional.ofNullable(source).orElseThrow(() -> new RuntimeException("source can not be null"));
        Optional.ofNullable(timeUnit).orElseThrow(() -> new RuntimeException("timeUnit can not be null"));
        if (TimeUnit.DAYS != timeUnit) {
            throw new RuntimeException("LocalDate can not minus with :" + timeUnit.name());
        } else {
            return (LocalDate) doMinus(source, amount, timeUnit);
        }
    }

    public static LocalTime minus(LocalTime source, long amount, TimeUnit timeUnit) {
        Optional.ofNullable(source).orElseThrow(() -> new RuntimeException("source can not be null"));
        Optional.ofNullable(timeUnit).orElseThrow(() -> new RuntimeException("timeUnit can not be null"));
        if (TimeUnit.DAYS == timeUnit) {
            throw new RuntimeException("LocalDate can not minus with :" + timeUnit.name());
        } else {
            return (LocalTime) doMinus(source, amount, timeUnit);
        }
    }

    public static <T> T minusNow(long amount, TimeUnit timeUnit, Class<T> tClass) {
        Optional.ofNullable(timeUnit).orElseThrow(() -> new RuntimeException("timeUnit can not be null"));
        Optional.ofNullable(tClass).orElseThrow(() -> new RuntimeException("tClass can not be null"));
        if (tClass.getName().equals(Date.class.getName())) {
            return (T) minus(new Date(), amount, timeUnit);
        } else if (tClass.getName().equals(Instant.class.getName())) {
            return (T) minus(Instant.now(), amount, timeUnit);
        } else if (tClass.getName().equals(LocalDateTime.class.getName())) {
            return (T) minus(Instant.now(), amount, timeUnit);
        } else if (tClass.getName().equals(LocalDate.class.getName())) {
            return (T) minus(Instant.now(), amount, timeUnit);
        } else if (tClass.getName().equals(LocalTime.class.getName())) {
            return (T) minus(Instant.now(), amount, timeUnit);
        } else {
            throw new RuntimeException("can not minus now with class:" + tClass.getName());
        }
    }

    private static <T extends TemporalAccessor> Temporal doMinus(Temporal source, long amount, TimeUnit timeUnit) {
        switch (timeUnit) {
            case DAYS:
                return source.minus(amount, ChronoUnit.DAYS);
            case HOURS:
                return source.minus(amount, ChronoUnit.HOURS);
            case MINUTES:
                return source.minus(amount, ChronoUnit.MINUTES);
            case MILLISECONDS:
                return source.minus(amount, ChronoUnit.MILLIS);
            case SECONDS:
                return source.minus(amount * 1000L, ChronoUnit.MILLIS);
            case NANOSECONDS:
                return source.minus(amount, ChronoUnit.NANOS);
            case MICROSECONDS:
                return source.minus(amount / 1000L, ChronoUnit.MILLIS);
            default:
                throw new RuntimeException("unknown timeUnit:" + timeUnit.name());
        }
    }
}


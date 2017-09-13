package domain.common;


import org.apache.commons.lang3.ArrayUtils;

import java.util.Iterator;

/**
 * Created by suslovai on 13.09.2017.
 */
public class Assert {

    private static final String DEFAULT_NOT_NULL_EX_MESSAGE_FORMAT_MESSAGE = "Argument %s must be not null";
    private static final String DEFAULT_NOT_NULL_EX_MESSAGE = "Argument must be not null";

    private static final String DEFAULT_NOT_CONTAINS_NULL_SIMPLE_FORMAT_MESSAGE = "Argument must not contains null at %s index";

    public static <T> void notNull(T argument) {
        notNull(argument, DEFAULT_NOT_NULL_EX_MESSAGE);
    }

    public static <T> void notNull(T argument, String argumentName) {
        notNull(argument, DEFAULT_NOT_NULL_EX_MESSAGE_FORMAT_MESSAGE, argumentName);
    }

    public static <T> void notNull(T argument, String messageFormat, Object... messageArgs) {
        if (argument == null) {
            throw new IllegalArgumentException(String.format(messageFormat, messageArgs));
        }
    }

    public static <T> void notContainsNullElements(Iterator<T> iterator) {
        notContainsNullElements(iterator, DEFAULT_NOT_CONTAINS_NULL_SIMPLE_FORMAT_MESSAGE, new Object[]{});
    }

    public static <T> void notContainsNullElements(Iterator<T> iterator, String messageFormat, Object... messageArgs) {
        int i = 0;
        while (iterator.hasNext()) {
            if (iterator.next() == null) {
                throw new IllegalArgumentException(String.format(messageFormat, ArrayUtils.addAll(messageArgs, (Object) Integer.valueOf(i))));
            }
            i++;
        }
    }
}

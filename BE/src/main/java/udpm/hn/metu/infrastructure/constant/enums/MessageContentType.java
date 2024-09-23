package udpm.hn.metu.infrastructure.constant.enums;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum MessageContentType {
    TEXT,
    ALERT;

    public static List<String> getAllContentType() {
        return Arrays.stream(MessageContentType.values())
                .map(Enum::name)
                .collect(Collectors.toList());
    }

    public static String getAllContentTypeString() {
        return Arrays.stream(MessageContentType.values())
                .map(Enum::name)
                .collect(Collectors.joining(", "));
    }
}

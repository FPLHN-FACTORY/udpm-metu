package udpm.hn.metu.infrastructure.constant.enums;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum ConversationType {

    LIVE_CHAT,
    FORM;

    public static List<String> getAllConversationType() {
        return Arrays.stream(ConversationType.values())
                .map(Enum::name)
                .collect(Collectors.toList());
    }

    public static String getAllConversationTypeString() {
        return Arrays.stream(ConversationType.values())
                .map(Enum::name)
                .collect(Collectors.joining(", "));
    }

}

package udpm.hn.metu.infrastructure.constant.enums;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum ConversationFormType {

    TEXT,
    PHONE,
    EMAIL;

    public static List<String> getAllConversationFormType() {
        return Arrays.stream(ConversationFormType.values())
                .map(Enum::name)
                .collect(Collectors.toList());
    }

    public static String getAllConversationFormTypeString() {
        return Arrays.stream(ConversationFormType.values())
                .map(Enum::name)
                .collect(Collectors.joining(", "));
    }
}

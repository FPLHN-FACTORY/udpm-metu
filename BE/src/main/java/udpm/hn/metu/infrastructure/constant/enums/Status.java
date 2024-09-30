package udpm.hn.metu.infrastructure.constant.enums;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum Status {

    ACTIVE, // hoạt động - value = 0

    INACTIVE; // ngừng hoạt động - value = 1

    public static List<String> getAllStatus() {
        return Arrays.stream(Status.values())
                .map(Enum::name)
                .collect(Collectors.toList());
    }

    public static String getAllStatusString() {
        return Arrays.stream(Status.values())
                .map(Enum::name)
                .collect(Collectors.joining(", "));
    }

}

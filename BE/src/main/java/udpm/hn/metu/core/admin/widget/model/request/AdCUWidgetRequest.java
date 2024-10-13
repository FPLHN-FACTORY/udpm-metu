package udpm.hn.metu.core.admin.widget.model.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class AdCUWidgetRequest {

    @NotNull(message = "Can't be null!")
    @NotBlank(message = "Can't be blank!")
    private String categoryId;

    @NotNull(message = "Can't be null!")
    @NotBlank(message = "Can't be blank!")
    private String code;

    @NotNull(message = "Can't be null!")
    @NotBlank(message = "Can't be blank!")
    private String name;

    @NotNull(message = "Can't be null!")
    @NotBlank(message = "Can't be blank!")
    private String type;

    @NotNull(message = "Can't be null!")
    @NotBlank(message = "Can't be blank!")
    private String iconUrl;

    @NotNull(message = "Can't be null!")
    @NotBlank(message = "Can't be blank!")
    private String description;
}

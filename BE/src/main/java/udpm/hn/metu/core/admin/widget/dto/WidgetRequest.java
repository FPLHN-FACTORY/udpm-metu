package udpm.hn.metu.core.admin.widget.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WidgetRequest {

    @NotBlank(message = "Cannot be left blank")
    @NotNull(message ="Cannot be left blank" )
    private String category;

    @NotBlank(message = "Cannot be left blank")
    @NotNull(message ="Cannot be left blank" )
    private String code;

    @NotBlank(message = "Cannot be left blank")
    @NotNull(message ="Cannot be left blank" )
    private String name;

    @NotBlank(message = "Cannot be left blank")
    @NotNull(message ="Cannot be left blank" )
    private String iconUrl;

    @NotBlank(message = "Cannot be left blank")
    @NotNull(message ="Cannot be left blank" )
    private String type;

    @NotBlank(message = "Cannot be left blank")
    @NotNull(message ="Cannot be left blank" )
    private String description;

    @NotBlank(message = "Cannot be left blank")
    @NotNull(message ="Cannot be left blank" )
    private Boolean top;
}

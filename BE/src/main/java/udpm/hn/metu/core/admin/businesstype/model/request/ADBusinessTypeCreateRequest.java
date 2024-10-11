package udpm.hn.metu.core.admin.businesstype.model.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ADBusinessTypeCreateRequest {

    @NotEmpty
    @Size(min = 5 , max = 250)
    private String businessTypeName;

    @Size(max = 500)
    private String description;
}

package udpm.hn.metu.core.admin.widget.dto;

import lombok.Getter;
import lombok.Setter;
import udpm.hn.metu.entity.Widget;

@Getter
@Setter

public class WidgetResponse {

    private String id;

    private String category;

    private String code;

    private String name;

    private String iconUrl;

    private String type;

    private String description;

    private Boolean top;

    public WidgetResponse(Widget widget) {
        this.id = widget.getId();
        this.code = widget.getCode();
        this.name = widget.getName();
        this.iconUrl = widget.getIconUrl();
        this.category = widget.getCategory().getName();
        this.type = widget.getType().toString();
        this.description = widget.getDescription();
        this.top = widget.getTop();
    }
}

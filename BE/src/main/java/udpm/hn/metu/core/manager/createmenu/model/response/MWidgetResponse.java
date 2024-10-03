package udpm.hn.metu.core.manager.createmenu.model.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import udpm.hn.metu.entity.Widget;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class MWidgetResponse {

    private String categoryName;

    private String categoryCode;

    private String categoryId;

    private List<Widget> widgets;

}

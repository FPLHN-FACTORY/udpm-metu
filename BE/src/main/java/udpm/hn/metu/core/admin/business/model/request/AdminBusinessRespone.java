package udpm.hn.metu.core.admin.business.model.request;

public interface AdminBusinessRespone {
    String getStt();
    String  getBusinessType();

    String getId();

    String getCode();

    String getName();

    String getDescription();

    String getStatus();

    String getCreateAt();
    String getCreateBy();

    String getUpdateAt();

    String getUpdateBy();

    String getDeleted();
}

package udpm.hn.metu.utils;


import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import udpm.hn.metu.infrastructure.constant.SessionConstant;

@Component
@RequiredArgsConstructor
class SessionHelper {

    private final HttpSession httpSession;

    public String getCurrentBusinessId() {
        return httpSession.getAttribute(SessionConstant.CURRENT_BUSINESS_ID).toString();
    }

    public String getCurrentBusinessName() {
        return httpSession.getAttribute(SessionConstant.CURRENT_BUSINESS_NAME).toString();
    }

    public String getCurrentBusinessAvatar() {
        return httpSession.getAttribute(SessionConstant.CURRENT_BUSINESS_AVATAR).toString();
    }

    public String getCurrentUserId() {
        return httpSession.getAttribute(SessionConstant.CURRENT_USER_ID).toString();
    }

    public String getCurrentUserEmail() {
        return httpSession.getAttribute(SessionConstant.CURRENT_USER_EMAIL).toString();
    }

    public String getCurrentUserAvatar() {
        return httpSession.getAttribute(SessionConstant.CURRENT_USER_AVATAR).toString();
    }


}

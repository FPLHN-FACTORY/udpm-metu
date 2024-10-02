export const ROUTES_CONSTANTS = {
    AUTHENTICATION: {
        path: "/auth",
        name: "authentication",
        children: {
            LOGIN: {
                path: "login",
                name: "login",
            },
            REGISTER: {
                path: "register",
                name: "register",
            },
            FORGOT_PASSWORD: {
                path: "forgot-password",
                name: "forgot-password",
            },
        },
    },
    ADMIN: {
        path: "/admin",
        name: "admin",
        children: {
            BUSINESS: {
                path: "business",
                name: "business",
            },
            BUSINESS_TYPE: {
                path: "business-type",
                name: "business-type",
            },
        },
    },
    MANAGER: {
        path: "/manager",
        name: "manager",
        children: {
            ANOTHER: {
                path: "another",
                name: "another",
            },
            SOMETHING: {
                path: "something",
                name: "something",
            },
        },
    },
    EMPLOYEE: {
        path: "/employee",
        name: "employee",
        children: {
            ANOTHER: {
                path: "another",
                name: "another",
            },
            SOMETHING: {
                path: "something",
                name: "something",
            },
        },
    },
    NOT_FOUND: {
        path: "/:pathMatch(.*)*",
        name: "NotFound",
    },
    REDIRECT: {
        path: "/redirect",
        name: "redirect",
    },
};

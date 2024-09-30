export const ROUTES_CONSTANTS = {
    LOGIN: {
        path: "auth/login",
        name: "login",
    },
    REGISTER: {
        path: "auth/register",
        name: "register",
    },
    ADMIN: {
        path: "/admin",
        name: "admin",
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

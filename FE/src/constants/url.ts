const {VITE_BASE_URL_SERVER} = import.meta.env || {};

const {VITE_BASE_URL_CLIENT} = import.meta.env || {};

// API URL
export const API_URL = `${VITE_BASE_URL_SERVER}/api/v1` as string;

// DOMAIN
export const DOMAIN_BACKEND = `${VITE_BASE_URL_SERVER}` as string;

export const DOMAIN_FRONTEND = `${VITE_BASE_URL_CLIENT}` as string;

export const URL_OAUTH2_GOOGLE =
    `${DOMAIN_BACKEND}/oauth2/authorize/google?redirect_uri=` as string;

export const URL_FRONTEND = `${DOMAIN_FRONTEND}/redirect`;

// COMMON API
export const PREFIX_API_COMMON = `${API_URL}/common` as string;

// AUTH API
export const PREFIX_API_AUTH = `${API_URL}/auth` as string;

export const PREFIX_API_LOGIN = PREFIX_API_AUTH + `/login` as string;
export const PREFIX_API_LOGOUT = PREFIX_API_AUTH + `/logout` as string;
export const PREFIX_API_REGISTER = PREFIX_API_AUTH + `/register` as string;
export const PREFIX_API_FORGOT_PASSWORD = PREFIX_API_AUTH + `/forgot-password` as string;
export const PREFIX_API_REFRESH = PREFIX_API_AUTH + `/refresh` as string;

// MANAGER API
export const PREFIX_API_MANAGER = `${API_URL}/manager` as string;

// AMIN API
export const PREFIX_API_ADMIN = `${API_URL}/admin` as string;

//
// export const PREFIX_API_DEPARTMENT_ADMIN =
//   `${API_URL}/admin/departments` as string;
//
// export const PREFIX_API_ROLE_ADMIN = `${API_URL}/admin/roles` as string;

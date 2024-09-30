import {DecodedToken, UserInformation} from "@/utils/types/auth.type.ts";
import {jwtDecode} from "jwt-decode";

export const getUserInformation = (token: string): UserInformation => {
    const decoded = jwtDecode<DecodedToken>(token);
    return {
        fullName: decoded.fullName,
        userId: decoded.userId,
        userCode: decoded.userCode,
        rolesNames: decoded.rolesName,
        rolesCodes: decoded.rolesCode,
        email: decoded.email,
        pictureUrl: decoded.pictureUrl,
    };
};

export const getRolesUser = (token: string): string => {
    const decoded = jwtDecode<DecodedToken>(token);
    return decoded.rolesCode;
};

export const getExpireTime = (token: string): number => {
    const decoded = jwtDecode<DecodedToken>(token);
    return decoded.exp;
};

export const isTokenExpired = (token: string): boolean => {
    const expireTime = getExpireTime(token);
    return Date.now() >= expireTime * 1000;
};

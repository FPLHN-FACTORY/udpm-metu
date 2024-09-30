export interface DecodedToken {
    fullName: string;
    userId: string;
    userCode: string;
    rolesName: string;
    rolesCode: string;
    exp: number;
    email: string;
    pictureUrl: string;
}

export interface UserInformation {
    fullName: string;
    userId: string;
    userCode: string;
    rolesNames: string;
    rolesCodes: string;
    email: string;
    pictureUrl: string;
}

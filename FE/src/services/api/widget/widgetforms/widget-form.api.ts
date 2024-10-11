import request from "@/services/request";
import {AxiosResponse} from "axios";
import {DefaultResponse} from "@/utils/types/api.common";
import {LIST_WIDGET_GROUP_BY_CATEGORY} from "@/constants/url";

export enum WidgetType {
    TEXT = 'TEXT',
    LINK = 'LINK',
    EMAIL = 'EMAIL',
    HOTLINE = 'HOTLINE',
    BOOK = 'BOOK',
    CALL_ME_BACK = 'CALL_ME_BACK',
    CALL_NOW = 'CALL_NOW',
    LIVE_CHAT = 'LIVE_CHAT',
    SMS = 'SMS',
}

export type Widget = {
    id: string;
    code: string;
    name: string;
    iconUrl: string;
    type: string;
    description: string;
    top: string;
}

export type field = {
    nameField: string,
    required: boolean,
  }

export const extractIconUrl = (iconUrl: string) => {
    if (!iconUrl) {
        return '';
    }

    const icons = iconUrl.split(',').map(icon => icon.trim()); // Xóa khoảng trắng thừa

    if (icons.length === 0) {
        return '';
    }

    return icons;
}


export interface WidgetsGroupByCategory {
    categoryName: string;
    categoryCode: string;
    categoryId: string;
    widget: Array<Widget>;
}

export const getListWidgetGroupByCategory = async () => {
    const res = (await request({
        url: `${LIST_WIDGET_GROUP_BY_CATEGORY}`,
        method: "GET",
    })) as AxiosResponse<
        DefaultResponse<WidgetsGroupByCategory>
    >;

    return res.data;
};

export const queryKey = {
  common: {},

  authentication: {
    login: "loginKey",
    logout: "logoutKey",
    register: "registerKey",
    getBusinessType: "authGetBusinessType",
  },

  admin: {
    businessType: {
      businesstypeList: "adBtBusinessListKey"
    },
    something: {},
  },

  manager: {
    another: {},
    widget: {
      WidgetGroupByCategory: "mWWidgetGroupByCategory"
    },
  },

};

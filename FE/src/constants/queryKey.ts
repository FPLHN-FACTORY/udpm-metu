export const queryKey = {
  common: {},
  authentication: {
    login: "loginKey",
    logout: "logoutKey",
    register: "registerKey",
    getBusinessType: "authGetBusinessType",
  },

  admin: {
    another: {},
    something: {},
    getBusiness: "getBusiness",
    updateBusiness: "updateBusiness",
    deleteBusiness: "deleteBusiness",

    businessType: {
      businesstypeList: "adBtBusinessListKey",
    },
  },

  manager: {
    another: {},
    widget: {
      WidgetGroupByCategory: "mWWidgetGroupByCategory",
    },
  },
};

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
    businessType: {
      businesstypeList: "adBtBusinessListKey"
    },
    planorder: {
      PlanOrder: "adPlanOrder"
    },
  },
  
  manager: {
    another: {},
    widget: {
      WidgetGroupByCategory: "mWWidgetGroupByCategory"
    },
  },

};

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
      planorder: {
        PlanOrder: "adPlanOrder"
      },
      businessType: {
        businesstypeList: "adBtBusinessListKey"
      },
      widget:{
          widgetAd: "adWidgetKey",
      }
  },
  
  manager: {
    another: {},
    widget: {
      WidgetGroupByCategory: "mWWidgetGroupByCategory"
    },
  },

};


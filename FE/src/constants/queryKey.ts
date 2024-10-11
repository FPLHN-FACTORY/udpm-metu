export const queryKey = {
  common: {

  },
  admin: {
    businessType: {
      businesstypeList: "adBtBusinessListKey"
    },
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
    },
    manager: {
      another: {},
      widget: {
        WidgetGroupByCategory: "mWWidgetGroupByCategory"
      },
    },
  }
};

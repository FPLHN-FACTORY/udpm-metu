<template>
  <div class="redirect-container">
    <p class="redirect-message">Redirecting...</p>
  </div>
</template>

<script setup lang="ts">
import {useAuthStore} from "@/stores/auth";
import {getUserInformation} from "@/utils/token.helper";
import {onMounted} from "vue";
import {useRoute, useRouter} from "vue-router";
import {ROLES} from "@/constants/role.ts";
import {ROUTES_CONSTANTS} from "@/constants/path.ts";

const route = useRoute();

const router = useRouter();

const authStore = useAuthStore();

const {state} = route.query;

onMounted(() => {
  if (state) {
    const decodedState = atob(state as string);

    const {accessToken, refreshToken} = JSON.parse(decodedState);

    const user = getUserInformation(accessToken);

    console.log(accessToken)

    authStore.login({
      user,
      accessToken,
      refreshToken,
    });

    const userRole = user.rolesCodes;

    switch (userRole) {
      case ROLES.ADMIN:
        router.push({name: ROUTES_CONSTANTS.ADMIN.name});
        break;
      case ROLES.MANAGER:
        router.push({name: ROUTES_CONSTANTS.MANAGER.name});
        break;
      case ROLES.EMPLOYEE:
        router.push({name: ROUTES_CONSTANTS.EMPLOYEE.name});
        break;
      default:
        router.push({name: ROUTES_CONSTANTS.AUTHENTICATION.name});
        break;
    }

    return;
  }
  router.push({name: ROUTES_CONSTANTS.AUTHENTICATION.name});
});
</script>

<style scoped>
.redirect-container {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100vh;
  background-color: #f0f0f0;
}

.redirect-message {
  font-size: 1.5rem;
  color: #333;
  font-weight: bold;
}
</style>

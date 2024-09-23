<template>
  <div>
    <h1>Login Page</h1>

    <form @submit.prevent="login">
      <input type="text" placeholder="username" v-model="username"> <br>
      <input type="password" placeholder="password" v-model="password"> <br>

      <button type="submit">Login</button>
    </form>
  </div>
</template>

<script lang="ts">
import { ref } from 'vue';
import { useRouter } from 'vue-router';

export default {
  name: 'Login',
  setup() {
    const router = useRouter();
    const username = ref('');
    const password = ref('');

    const users = ref([
      {
        username: "admin",
        password: "123",
        role: 1,
      },
      {
        username: "manager",
        password: "123",
        role: 2,
      },
      {
        username: "staff",
        password: "123",
        role: 3,
      },
    ]);

    const login = () => {

      const user = users.value.find(
        (u) => u.username === username.value && u.password === password.value
      );

      if (user) {
        localStorage.setItem('auth', 'true');
        switch (user.role) {
          case 1:
            router.push({ name: 'AdminPage' });
            break;
          default:
            router.push({ name: 'Dashboard' });
        }
      } else {
        alert('tài khoản không hợp lệ');
      }
      
    };

    return { username, password, login };
  },
};
</script>

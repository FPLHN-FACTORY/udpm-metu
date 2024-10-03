<template>
  <a-row type="flex">
    <a-col flex="600px" class="h-full p-10">
      <div class="flex justify-center items-center h-full font-normal">
        <div class="w-full max-w-md pb-20">
          <div class="text-center mb-12 no-underline">
            <div class="flex items-center justify-center">
              <img :src="logo" alt="Logo" class="mb-2 w-48"/>
              <img :src="logoUDPM" alt="Logo" class="mb-2 w-48"/>
            </div>
            <span class="text-2xl text-gray-500">Chào mừng đến với METO!</span>
          </div>
          <router-view/>
        </div>
      </div>
    </a-col>
    <a-col flex="auto" class="bg-authentication"></a-col>
  </a-row>
</template>

<script lang="ts" setup>
import logo from "@/assets/poly/logos/logo-fpoly-1.png";
import logoUDPM from "@/assets/poly/udpm/logo-udpm-3.png";
</script>

<style scoped>
.bg-authentication {
  background-image: url(@/assets/poly/background/bg-simple.jpg);
  background-size: cover;
  background-position: center;
  height: 100vh;
}
</style>
<template>
  <div>
    <h1>Danh sách doanh nghiệp</h1>
    <div class="flex justify-center items-center flex-col">
      <button @click="handleLoginGoogle" type="button"
        class="flex justify-center items-center py-3.5 border-2 rounded-2xl w-full text-xs text-gray-900"
        :disabled="isLoginProcessing">
        <v-icon name="fc-google" class="mr-2" />
        Google
      </button>
    </div>

  </div>
</template>

<script lang="ts">
export default {
  name: 'BusinessList',
};
</script>

<script lang="ts" setup>
import { useGetListUser } from "@/services/service/example/example.action";
import { ref } from "vue";

const { mutate: user } = useGetListUser();

const isLoginProcessing = ref(false);

const handleLoginGoogle = async () => {
  isLoginProcessing.value = true;
  try {
    const data = user();
    console.log(data);
  } catch (error) {
    console.error(error);
  } finally {
    isLoginProcessing.value = false;
  }
};

</script>
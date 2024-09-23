
import { defineConfig } from 'vite';
import vue from '@vitejs/plugin-vue';
import path from 'path';

export default defineConfig({
  // root: './src',         
  // base: '',     
  plugins: [vue()],      
  server: {
    port: 2024,          
    proxy: {
      '/api': {
        target: 'http://localhost:1919',
        changeOrigin: true,
        rewrite: (path) => path.replace(/^\/api/, ''),
      },
    },
  },
  resolve: {
    alias: {
      '@': path.resolve(__dirname, "./src"),
      '@views': path.resolve(__dirname, "./src/views"),
    }
  },
  // css: {
  //   preprocessorOptions: {
  //     scss: {
  //       additionalData: `@import "@/styles/variables.scss";`,
  //     },
  //   },
  // },
  build: {
    outDir: 'dist',
    sourcemap: true,
  },
});

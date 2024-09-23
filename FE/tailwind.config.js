/** @type {import('tailwindcss').Config} */
export default {
  content: [
    './src/**/*.{html,js,ts,vue}',
  ],
  theme: {
    extend: {
      colors: {
        primary: '#1da1f2',
        secondary: '#ffed4a',
      },
      spacing: {
        '128': '32rem',
      },
    },
  },
  plugins: [],
}

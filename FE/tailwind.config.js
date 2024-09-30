/** @type {import('tailwindcss').Config} */
export default {
    content: [
        "./index.html",
        "./src/**/*.{vue,js,ts,jsx,tsx,html}",
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

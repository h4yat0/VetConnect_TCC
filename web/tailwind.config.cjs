/** @type {import('tailwindcss').Config} */
module.exports = {
	content: ["./index.html", "./src/**/*.{js,ts,jsx,tsx}"],
	theme: {
		extend: {
			fontFamily: { inter: ["Inter"] },
			colors: {
				vetConnectPrimaryGreen: "#4ecca3",
				vetConnectSecundaryGreen: "#8be5bb",
				vetConnectGray: "#393e46",
				vetConnectWhite: "#eeeeee",
			},
		},
	},
	plugins: [],
};

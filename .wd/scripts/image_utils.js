const clamp         = (n, min = 0, max = 255) => Math.min(Math.max(n, min), max);
const clampAndShift = (n, d) => clamp(n) << d;
const scale         = (n, min, max) => (clamp(n, min, max) - min) / (max - min);

const argbToInt = (c) => {
	return 	clampAndShift(c.a, 24) +
		clampAndShift(c.r, 16) +
		clampAndShift(c.g, 8) +
		clampAndShift(c.b, 0);
};
const intToArgb = (n) => {
	return {a:(n >> 24) & 0xFF,
		r:(n >> 16) & 0xFF,
		g:(n >> 8)  & 0xFF,
		b:(n >> 0)  & 0xFF};
};

const checker   = (x, y, n = 64) => ((x % n) < n / 2 ^ (y % n) < n / 2);
const checker4  = (a, r, g, b, n = 64) => {
    f = (x, n) => (x % n) < (n / 4);
    return f(a,n) ^ f(r,n) ^ f(g,n) ^ f(b,n);
}
const average   = (a, b) => (a + b) / 2;
const merge 	= (c1, c2) => {
	return {a: average(clamp(c1.a), clamp(c2.a)),
		r: average(clamp(c1.r), clamp(c2.r)),
		g: average(clamp(c1.g), clamp(c2.g)),
		b: average(clamp(c1.b), clamp(c2.b))
	};
};

const clear = intToArgb(0x00FFFFFF);
const white = intToArgb(0xFFFFFFFF);
const grey  = intToArgb(0xFF777777);
const black = intToArgb(0xFF000000);

const red   = intToArgb(0xFFFF0000);
const green = intToArgb(0xFF00FF00);
const blue  = intToArgb(0xFF0000FF);
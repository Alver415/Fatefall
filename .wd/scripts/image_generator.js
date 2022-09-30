var width = 300;
var height = 416;
var data = [];

for (var y = 0 ; y < height ; y++) {
    for (var x = 0 ; x < width; x++) {
	var c = {a: a,
		 r: scale(x % y , 0,  width ) * r,
		 g: scale(y % x , 0,  height) * g,
		 b: scale((x+y) % 255 , 0, 255) * b};
	
        var color = checker4(x, y, 32) ? c : merge(c, merge(c, clear));
        data.push(argbToInt(color));
    }
}